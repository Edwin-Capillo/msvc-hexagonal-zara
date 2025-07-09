package com.msvc.hexagonal.zara.infrastructure.adapters;

import com.msvc.hexagonal.zara.application.ports.out.PriceRepositoryPort;
import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.adapters.exception.PriceException;
import com.msvc.hexagonal.zara.infrastructure.adapters.mapper.PriceMapper;
import com.msvc.hexagonal.zara.infrastructure.adapters.repository.SpringDatePriceRepository;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDatePriceRepository springDatePriceRepository;
    private final PriceMapper priceMapper;

    @Override
    public Price getById(Long id) {
        return springDatePriceRepository.findById(id)
                .map(priceMapper::toPrice)
                .orElseThrow(() -> new PriceException(HttpStatus.NOT_FOUND,
                        String.format(ConstantsUtils.NOTFOUND, id)));
    }

    @Override
    public Price findByPriceRequest(PriceRequest priceRequest) {
        try {
            if (Stream.of(priceRequest.getInputDate(), priceRequest.getProductId(), priceRequest.getBrandId())
                    .anyMatch(Objects::isNull)) {
                throw new PriceException(HttpStatus.BAD_REQUEST, ConstantsUtils.NOTNULLVARIABLE);
            }
            var priceEntity = this.springDatePriceRepository.getPriceEntity(
                    priceRequest.getProductId(),
                    priceRequest.getBrandId(),
                    priceRequest.getInputDate());

            return priceEntity.map(this.priceMapper::toPrice)
                    .orElseThrow(() -> new PriceException(HttpStatus.NOT_FOUND,
                            String.format(ConstantsUtils.NOTFOUND, priceRequest.getProductId())));

        } catch (DateTimeParseException ex) {
            throw new PriceException(HttpStatus.BAD_REQUEST, ConstantsUtils.UNEXPECTEDERROR);
        }
    }

    @Override
    public List<Price> getAll() {
        return this.priceMapper.toListPrice(
                this.springDatePriceRepository.findAll());
    }
}
