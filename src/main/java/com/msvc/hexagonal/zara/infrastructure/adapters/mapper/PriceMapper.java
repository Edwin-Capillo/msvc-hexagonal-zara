package com.msvc.hexagonal.zara.infrastructure.adapters.mapper;

import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.adapters.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "priceAmount", target = "priceAmount")
    @Mapping(source = "currency", target = "currency")
    Price toPrice(PriceEntity entity);

    List<Price> toListPrice(List<PriceEntity> entity);
}