package com.msvc.hexagonal.zara.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PriceDetailResponse {
    private Long id;
    private Long brandId;
    @JsonFormat(pattern = ConstantsUtils.FORMATDATETIME)
    private LocalDateTime startDate;
    @JsonFormat(pattern = ConstantsUtils.FORMATDATETIME)
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;
}
