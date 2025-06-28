package com.msvc.hexagonal.zara.application.mapper;


import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "currency", target = "currency")
    PriceSummaryResponse toPriceSummaryResponse(Price price);

    PriceDetailResponse toPriceDetailResponse(Price price);
    List<PriceDetailResponse> toListPriceDetailResponse(List<Price> price);
}
