package com.msvc.hexagonal.zara.application.service;

import com.msvc.hexagonal.zara.application.mapper.PriceResponseMapper;
import com.msvc.hexagonal.zara.application.ports.out.PriceRepositoryPort;
import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @Mock
    private PriceResponseMapper priceResponseMapper;
    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Price mockPrice = new Price();
        PriceDetailResponse mockResponse = new PriceDetailResponse();
        Mockito.when(priceRepositoryPort.getById(1L)).thenReturn(mockPrice);
        Mockito.when(priceResponseMapper.toPriceDetailResponse(mockPrice)).thenReturn(mockResponse);

        PriceDetailResponse result = priceService.getById(1L);

        assertNotNull(result);
        Mockito.verify(priceRepositoryPort).getById(1L);
        Mockito.verify(priceResponseMapper).toPriceDetailResponse(mockPrice);
    }

    @Test
    void testGetPrice() {

        PriceRequest request = new PriceRequest();
        request.setProductId(1L);
        request.setBrandId(1L);
        request.setInputDate(LocalDateTime.parse("2023-10-01T10:00:00"));

        Price mockPrice = new Price();
        mockPrice.setId(1L);
        mockPrice.setBrandId(1L);
        mockPrice.setStartDate(LocalDateTime.parse("2023-10-01T00:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2023-10-31T23:59:59"));
        mockPrice.setPriceList(1);
        mockPrice.setProductId(1L);
        mockPrice.setPriority(1);
        mockPrice.setPrice(100.0);
        mockPrice.setCurrency("EUR");

        PriceSummaryResponse mockResponse = new PriceSummaryResponse();
        mockResponse.setProductId(1L);
        mockResponse.setBrandId(1L);
        mockResponse.setStartDate(LocalDateTime.parse("2023-10-01T00:00:00"));
        mockResponse.setEndDate(LocalDateTime.parse("2023-10-31T23:59:59"));
        mockResponse.setPriceList(1);
        mockResponse.setPrice(100.0);
        mockResponse.setCurrency("EUR");

        Mockito.when(priceRepositoryPort.findByPriceRequest(any(PriceRequest.class))).thenReturn(mockPrice);
        Mockito.when(priceResponseMapper.toPriceSummaryResponse(mockPrice)).thenReturn(mockResponse);

        PriceSummaryResponse result = priceService.getPrice(request);

        assertNotNull(result);
        Mockito.verify(priceRepositoryPort).findByPriceRequest(request);
        Mockito.verify(priceResponseMapper).toPriceSummaryResponse(mockPrice);
    }

    @Test
    void testGetAll() {

        List<Price> mockPrices = Collections.singletonList(new Price());
        List<PriceDetailResponse> mockResponses = Collections.singletonList(new PriceDetailResponse());
        Mockito.when(priceRepositoryPort.getAll()).thenReturn(mockPrices);
        Mockito.when(priceResponseMapper.toListPriceDetailResponse(mockPrices)).thenReturn(mockResponses);


        List<PriceDetailResponse> result = priceService.getAll();


        assertNotNull(result);
        assertEquals(1, result.size());
        Mockito.verify(priceRepositoryPort).getAll();
        Mockito.verify(priceResponseMapper).toListPriceDetailResponse(mockPrices);
    }
}