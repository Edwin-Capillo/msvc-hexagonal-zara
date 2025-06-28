package com.msvc.hexagonal.zara.application.service;

import com.msvc.hexagonal.zara.application.mapper.PriceResponseMapper;
import com.msvc.hexagonal.zara.application.ports.in.PriceUseCases;
import com.msvc.hexagonal.zara.application.ports.out.PriceRepositoryPort;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceService implements PriceUseCases {
    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceResponseMapper priceResponseMapper;

    public PriceService(PriceRepositoryPort priceRepositoryPort, PriceResponseMapper priceResponseMapper) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceResponseMapper = priceResponseMapper;
    }

    @Override
    public PriceDetailResponse getById(Long id) {
        var price = this.priceRepositoryPort.getById(id);
        return this.priceResponseMapper.toPriceDetailResponse(price);
    }

    @Override
    public PriceSummaryResponse getPrice(PriceRequest priceRequest) {
        var price = this.priceRepositoryPort.findByPriceRequest(priceRequest);
        return this.priceResponseMapper.toPriceSummaryResponse(price);
    }

    @Override
    public List<PriceDetailResponse> getAll() {
        var prices = this.priceRepositoryPort.getAll();
        return this.priceResponseMapper.toListPriceDetailResponse(prices);
    }
}
