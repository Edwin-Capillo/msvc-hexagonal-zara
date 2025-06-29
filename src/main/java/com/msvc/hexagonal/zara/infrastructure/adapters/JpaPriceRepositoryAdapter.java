package com.msvc.hexagonal.zara.infrastructure.adapters;

import com.msvc.hexagonal.zara.application.ports.out.PriceRepositoryPort;
import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.adapters.exception.PriceException;
import com.msvc.hexagonal.zara.infrastructure.adapters.mapper.PriceMapper;
import com.msvc.hexagonal.zara.infrastructure.adapters.repository.SpringDatePriceRepository;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeParseException;
import java.util.List;

@Repository
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDatePriceRepository springDatePriceRepository;
    private final PriceMapper priceMapper;

    public JpaPriceRepositoryAdapter(SpringDatePriceRepository springDatePriceRepository, PriceMapper priceMapper) {
        this.springDatePriceRepository = springDatePriceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public Price getById(Long id) {
        var priceEntity = this.springDatePriceRepository.findById(id);
        return this.priceMapper.toPrice(priceEntity.orElseThrow(() ->
                new PriceException(HttpStatus.NOT_FOUND, String.format(ConstantsUtils.NOTFOUND, id))));
    }

    @Override
    public Price findByPriceRequest(PriceRequest priceRequest) {
        try {
            if (priceRequest.getInputDate() == null
                    || priceRequest.getProductId() == null
                    || priceRequest.getBrandId() == null) {
                throw new PriceException(HttpStatus.BAD_REQUEST, ConstantsUtils.NOTNULLOREMPTY);
            }
            var priceEntity = this.springDatePriceRepository.getPriceEntity(priceRequest.getProductId(),
                    priceRequest.getBrandId(), priceRequest.getInputDate());
            if (priceEntity.isEmpty()) {
                throw new PriceException(HttpStatus.NOT_FOUND,
                        String.format(ConstantsUtils.NOTFOUND, priceRequest.getProductId()));
            }

            return this.priceMapper.toPrice(priceEntity.get());

        } catch (DateTimeParseException ex) {
            throw new PriceException(HttpStatus.BAD_REQUEST, ConstantsUtils.UNEXPECTEDERROR);
        }
    }

    @Override
    public List<Price> getAll() {
        try {
            return this.priceMapper.toListPrice(this.springDatePriceRepository.findAll());
        } catch (Exception e) {
            throw new PriceException(HttpStatus.INTERNAL_SERVER_ERROR, ConstantsUtils.UNEXPECTEDERROR);
        }
    }
}
