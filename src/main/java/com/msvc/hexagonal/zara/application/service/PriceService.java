package com.msvc.hexagonal.zara.application.service;

import com.msvc.hexagonal.zara.application.mapper.PriceResponseMapper;
import com.msvc.hexagonal.zara.application.ports.in.PriceUseCases;
import com.msvc.hexagonal.zara.application.ports.out.PriceRepositoryPort;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceService implements PriceUseCases {
    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceResponseMapper priceResponseMapper;

    @Override
    public PriceDetailResponse getById(Long id) {
        return this.priceResponseMapper.toPriceDetailResponse(
                this.priceRepositoryPort.getById(id));
    }

    @Override
    public PriceSummaryResponse getPrice(PriceRequest priceRequest) {
        return this.priceResponseMapper.toPriceSummaryResponse(
                this.priceRepositoryPort.findByPriceRequest(priceRequest));
    }

    @Override
    public List<PriceDetailResponse> getAll() {
        return priceResponseMapper.toListPriceDetailResponse(
                priceRepositoryPort.getAll());
    }
}
