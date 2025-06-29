package com.msvc.hexagonal.zara.infrastructure.controller;

import com.msvc.hexagonal.zara.application.ports.in.PriceUseCases;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceDetailResponse;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceUseCases priceUseCases;

    @InjectMocks
    private PriceController priceController;

    private void setupMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }
    private void mockPriceResponse(PriceSummaryResponse response) {
        Mockito.when(priceUseCases.getPrice(any(PriceRequest.class))).thenReturn(response);
    }

    @Test
    void testGetById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();

        PriceDetailResponse response = new PriceDetailResponse();
        response.setBrandId(1L);
        response.setStartDate(LocalDateTime.parse("2023-10-05T15:30:45"));
        response.setEndDate(LocalDateTime.parse("2023-10-05T16:30:45"));
        response.setPriceList(2);
        response.setProductId(1L);
        response.setPriority(1);
        response.setPrice(100.0);
        response.setCurrency("EUR");

        Mockito.when(priceUseCases.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/price/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.startDate").value("2023-10-05-15:30:45"))
                .andExpect(jsonPath("$.endDate").value("2023-10-05-16:30:45"))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testGetPrice() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPriceList(1);
        response.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        response.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        response.setPrice(35.50);
        response.setCurrency("EUR");

        Mockito.when(priceUseCases.getPrice(any(PriceRequest.class))).thenReturn(response);

        String requestBody = """
                {
                    "inputDate": "2020-06-14-10:00:00",
                    "productId": 35455,
                    "brandId": 1
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23:59:59"))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
/*
* Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
* */
    @Test
    void Test_1() throws Exception {
        setupMockMvc();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPrice(35.50);
        response.setCurrency("EUR");

        mockPriceResponse(response);

        String requestBody = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-14T10:00:00"
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void Test_2() throws Exception {
        setupMockMvc();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPrice(25.45);
        response.setCurrency("EUR");

        mockPriceResponse(response);

        String requestBody = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-14T16:00:00"
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void Test_3() throws Exception {
        setupMockMvc();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPrice(35.50);
        response.setCurrency("EUR");

        mockPriceResponse(response);

        String requestBody = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-14T21:00:00"
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void Test_4() throws Exception {
        setupMockMvc();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPrice(30.50);
        response.setCurrency("EUR");

        mockPriceResponse(response);

        String requestBody = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-15T10:00:00"
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void Test_5() throws Exception {
        setupMockMvc();

        PriceSummaryResponse response = new PriceSummaryResponse();
        response.setProductId(35455L);
        response.setBrandId(1L);
        response.setPrice(38.95);
        response.setCurrency("EUR");

        mockPriceResponse(response);

        String requestBody = """
                {
                    "productId": 35455,
                    "brandId": 1,
                    "applicationDate": "2020-06-16T21:00:00"
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    void testGetAll() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();

        PriceDetailResponse response = new PriceDetailResponse();
        response.setBrandId(1L);
        response.setStartDate(LocalDateTime.parse("2023-10-05T15:30:45"));
        response.setEndDate(LocalDateTime.parse("2023-10-05T16:30:45"));
        response.setPriceList(2);
        response.setProductId(1L);
        response.setPriority(1);
        response.setPrice(100.0);
        response.setCurrency("EUR");

        List<PriceDetailResponse> responseList = Collections.singletonList(response);

        Mockito.when(priceUseCases.getAll()).thenReturn(responseList);

        mockMvc.perform(get("/api/prices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]..brandId").value(1))
                .andExpect(jsonPath("$[0]..startDate").value("2023-10-05-15:30:45"))
                .andExpect(jsonPath("$[0]..endDate").value("2023-10-05-16:30:45"))
                .andExpect(jsonPath("$[0]..priceList").value(2))
                .andExpect(jsonPath("$[0]..productId").value(1))
                .andExpect(jsonPath("$[0]..priority").value(1))
                .andExpect(jsonPath("$[0]..price").value(100.0))
                .andExpect(jsonPath("$[0]..currency").value("EUR"));
    }
}