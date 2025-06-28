package com.msvc.hexagonal.zara.application.ports.out;

import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;

import java.util.List;

public interface PriceRepositoryPort {
    Price getById(Long id);
    Price findByPriceRequest(PriceRequest priceRequest);
    List<Price> getAll();
}
