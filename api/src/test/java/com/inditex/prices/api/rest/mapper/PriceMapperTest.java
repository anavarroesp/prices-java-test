package com.inditex.prices.api.rest.mapper;

import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceMapperTest {

    private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);
    
    @Test
    void shoudMapPriceEntityToPriceDetail() {
        // Arrange
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
                .priceCode(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .priority(0)
                .build();
        
        // Act
        final var result = this.priceMapper.toPriceDetail(price);
        
        // Assert
        assertEquals(price.brand().id(), result.getBrandId());
        assertEquals(price.product().id(), result.getProductId());
        assertEquals(price.priceCode(), result.getPriceCode());
        assertEquals(price.startDate(), result.getStartDate());
        assertEquals(price.endDate(), result.getEndDate());
        assertEquals(price.price().doubleValue(), result.getPrice());

    }
}
