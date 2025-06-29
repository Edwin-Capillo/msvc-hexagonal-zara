package com.msvc.hexagonal.zara.infrastructure.adapters;

import com.msvc.hexagonal.zara.domain.model.Price;
import com.msvc.hexagonal.zara.infrastructure.adapters.entity.PriceEntity;
import com.msvc.hexagonal.zara.infrastructure.adapters.exception.PriceException;
import com.msvc.hexagonal.zara.infrastructure.adapters.mapper.PriceMapper;
import com.msvc.hexagonal.zara.infrastructure.adapters.repository.SpringDatePriceRepository;
import com.msvc.hexagonal.zara.infrastructure.controller.dto.PriceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class JpaPriceRepositoryAdapterTest {

    @Mock
    private SpringDatePriceRepository springDatePriceRepository;

    @Mock
    private PriceMapper priceMapper;
    @InjectMocks
    private JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter;

    @Test
    void testGetById_Success() {
        PriceEntity mockEntity = new PriceEntity();
        mockEntity.setId(1L);
        mockEntity.setBrandId(1L);
        mockEntity.setStartDate(LocalDateTime.now());
        mockEntity.setEndDate(LocalDateTime.now().plusDays(1));
        mockEntity.setPriceList(1);
        mockEntity.setProductId(1L);
        mockEntity.setPriority(1);
        mockEntity.setPrice(100.0);
        mockEntity.setCurrency("EUR");

        Price mockPrice = new Price();
        mockPrice.setId(1L);
        mockPrice.setBrandId(1L);
        mockPrice.setStartDate(mockEntity.getStartDate());
        mockPrice.setEndDate(mockEntity.getEndDate());
        mockPrice.setPriceList(1);
        mockPrice.setProductId(1L);
        mockPrice.setPriority(1);
        mockPrice.setPrice(100.0);
        mockPrice.setCurrency("EUR");

        Mockito.when(springDatePriceRepository.findById(1L)).thenReturn(Optional.of(mockEntity));
        Mockito.when(priceMapper.toPrice(mockEntity)).thenReturn(mockPrice);

        Price result = jpaPriceRepositoryAdapter.getById(1L);

        assertNotNull(result);
        Mockito.verify(springDatePriceRepository).findById(1L);
        Mockito.verify(priceMapper).toPrice(mockEntity);
    }

    @Test
    void testGetById_NotFound() {
        Mockito.when(springDatePriceRepository.findById(1L)).thenReturn(Optional.empty());

        PriceException exception = assertThrows(PriceException.class, () -> jpaPriceRepositoryAdapter.getById(1L));

        Mockito.verify(springDatePriceRepository).findById(1L);
    }

    @Test
    void testFindByPriceRequest_Success() {
        PriceRequest request = new PriceRequest();
        request.setInputDate(LocalDateTime.now());
        request.setProductId(1L);
        request.setBrandId(1L);

        PriceEntity mockEntity = new PriceEntity();
        Price mockPrice = new Price();
        Mockito.when(springDatePriceRepository.getPriceEntity(eq(1L), eq(1L), any(LocalDateTime.class)))
                .thenReturn(Optional.of(mockEntity));
        Mockito.when(priceMapper.toPrice(mockEntity)).thenReturn(mockPrice);

        Price result = jpaPriceRepositoryAdapter.findByPriceRequest(request);

        assertNotNull(result);
        Mockito.verify(springDatePriceRepository).getPriceEntity(eq(1L), eq(1L), any(LocalDateTime.class));
        Mockito.verify(priceMapper).toPrice(mockEntity);
    }

    @Test
    void testFindByPriceRequest_NotFound() {
        PriceRequest request = new PriceRequest();
        request.setInputDate(LocalDateTime.now());
        request.setProductId(1L);
        request.setBrandId(1L);

        Mockito.when(springDatePriceRepository.getPriceEntity(eq(1L), eq(1L), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        PriceException exception = assertThrows(PriceException.class, () -> jpaPriceRepositoryAdapter.findByPriceRequest(request));

        Mockito.verify(springDatePriceRepository).getPriceEntity(eq(1L), eq(1L), any(LocalDateTime.class));
    }

    @Test
    void testGetAll_Success() {
        PriceEntity mockEntity = new PriceEntity();
        Price mockPrice = new Price();
        Mockito.when(springDatePriceRepository.findAll()).thenReturn(Collections.singletonList(mockEntity));
        Mockito.when(priceMapper.toListPrice(Collections.singletonList(mockEntity))).thenReturn(Collections.singletonList(mockPrice));

        var result = jpaPriceRepositoryAdapter.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        Mockito.verify(springDatePriceRepository).findAll();
        Mockito.verify(priceMapper).toListPrice(Collections.singletonList(mockEntity));
    }
}