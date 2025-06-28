package com.msvc.hexagonal.zara.application.ports.in;

import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;

import java.util.List;

public interface PriceUseCases {
    PriceDetailResponse getById(Long id);
    PriceSummaryResponse getPrice(PriceRequest priceRequest);
    List<PriceDetailResponse> getAll();
}
