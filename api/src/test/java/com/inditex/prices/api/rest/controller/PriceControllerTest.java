package com.inditex.prices.api.rest.controller;

import com.inditex.prices.api.rest.mapper.PriceMapper;
import com.inditex.prices.api.rest.model.PriceDetail;
import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.entity.Product;
import com.inditex.prices.domain.usecase.FindPriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PriceControllerTest {
    
    @Mock
    private FindPriceUseCase findPriceUseCase;
    @Mock
    private PriceMapper priceMapper;
    
    @InjectMocks
    private PriceController priceController;
    
    @Test
    void findPriceMustReturn200Ok() {
        // Arrange
        final PriceDetail priceDetail = new PriceDetail();
        priceDetail.setPrice(25.45);
        priceDetail.setBrandId(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"));
        priceDetail.setProductId(UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"));
        priceDetail.setPriceList(1);
        priceDetail.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        priceDetail.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));

        final var brand = Brand.builder()
                .id(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"))
                .name("Zara")
                .build();
        final var product = Product.builder()
                .id(UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"))
                .build();

        final var price = Price.builder()
                .product(product)
                .brand(brand)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .priority(0)
                .build();
        
        when(this.findPriceUseCase.findPrice(any(), any(), any())).thenReturn(price);
        when(this.priceMapper.toPriceDetail(any())).thenReturn(priceDetail);
        
        // Act
        final var result = this.priceController.getPrice(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"),
                UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"), LocalDateTime.parse("2020-06-14T10:00:00"));
        
        // Assert
        assertEquals("272595b8-0a72-4782-83db-5d66bd293120", result.getBody().getBrandId().toString());
        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
    }
    
    @Test
    void findPriceMustReturn200OkWhenNoPrices() {
        // Arrange
        when(this.findPriceUseCase.findPrice(any(), any(), any())).thenReturn(null);
        
        // Act
        final var result = this.priceController.getPrice(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"),
                UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"), LocalDateTime.parse("2020-06-14T10:00:00"));
        
        // Assert
        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
        assertNull(result.getBody());
    }
    
    
}
