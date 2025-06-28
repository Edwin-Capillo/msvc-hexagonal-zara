package com.msvc.hexagonal.zara.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private BigDecimal price;
    private String currency;
}
