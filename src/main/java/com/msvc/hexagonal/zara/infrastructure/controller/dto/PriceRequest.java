package com.msvc.hexagonal.zara.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msvc.hexagonal.zara.infrastructure.util.ConstantsUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PriceRequest implements Serializable {
    @JsonFormat(pattern = ConstantsUtils.FORMATDATETIME)
    private LocalDateTime inputDate;
    private Long productId;
    private Long brandId;
}
