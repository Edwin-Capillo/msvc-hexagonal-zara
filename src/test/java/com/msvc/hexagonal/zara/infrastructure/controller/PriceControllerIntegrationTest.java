package com.msvc.hexagonal.zara.infrastructure.controller;

import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceSummaryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use a specific profile for testing
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriceAt10AM14th() throws Exception {
        String requestBody = """
                {
                    "inputDate": "2020-10-14-10:00:00",
                    "brandId": 1,
                    "productId": 35455
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }

    @Test
    void testGetPriceAt4PM14th() throws Exception {
        String requestBody = """
                {
                    "inputDate": "2020-10-14-16:00:00",
                    "productId": 35455,
                    "brandId": 1
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }

    @Test
    void testGetPriceAt9PM14th() throws Exception {
        String requestBody = """
                {
                    "inputDate": "2020-10-14-21:00:00",
                    "productId": 35455,
                    "brandId": 1
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }

    @Test
    void testGetPriceAt10AM15th() throws Exception {
        String requestBody = """
                {
                    "inputDate": "2020-10-15-10:00:00",
                    "productId": 35455,
                    "brandId": 1
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }

    @Test
    void testGetPriceAt9PM16th() throws Exception {
        String requestBody = """
                {
                    "inputDate": "2020-10-16-21:00:00",
                    "productId": 35455,
                    "brandId": 1
                }
                """;

        mockMvc.perform(post("/api/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }
}