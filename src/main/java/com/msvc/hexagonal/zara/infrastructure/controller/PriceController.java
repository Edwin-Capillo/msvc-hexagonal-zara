package com.msvc.hexagonal.zara.infrastructure.controller;

import com.msvc.hexagonal.zara.application.ports.in.PriceUseCases;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PriceController {
    private final PriceUseCases priceUseCases;

    @GetMapping("/price/{id}")
    public PriceDetailResponse getById(@PathVariable Long id) {
        return this.priceUseCases.getById(id);
    }

    @PostMapping("/price")
    public PriceSummaryResponse getPrice(@RequestBody PriceRequest priceRequest) {
        return this.priceUseCases.getPrice(priceRequest);
    }

    @GetMapping("/prices")
    public List<PriceDetailResponse> getAll() {
        return priceUseCases.getAll();
    }

}