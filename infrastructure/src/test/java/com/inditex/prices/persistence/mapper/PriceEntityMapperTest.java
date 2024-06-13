package com.inditex.prices.persistence.mapper;

import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Product;
import com.inditex.prices.persistence.entity.BrandEntity;
import com.inditex.prices.persistence.entity.PriceEntity;
import com.inditex.prices.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PriceEntityMapperTest {

    @InjectMocks
    private PriceEntityMapper priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @Mock
    private ProductEntityMapper productEntityMapper;
    
    @Test
    void shoudMapPriceEntityToPriceDomain() {
        // Arrange
        final var brandEntity = new BrandEntity();
        brandEntity.setId(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"));
        brandEntity.setName("Zara");
        final var productEntity = new ProductEntity();
        productEntity.setId(UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"));
        
        final var priceEntity = new PriceEntity();
        priceEntity.setId(UUID.fromString("8b1fd405-c2ae-42e5-8b88-355a62acabf4"));
        priceEntity.setBrand(brandEntity);
        priceEntity.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        priceEntity.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        priceEntity.setPriceCode(1);
        priceEntity.setProduct(productEntity);
        priceEntity.setPriority(0);
        priceEntity.setPrice(BigDecimal.valueOf(35.50));
        priceEntity.setCurrency("EUR");

        final var brand = Brand.builder()
                .id(brandEntity.getId())
                .name("Zara")
                .build();
        final var product = Product.builder()
                .id(productEntity.getId())
                .build();

        when(this.brandEntityMapper.toDomain(brandEntity)).thenReturn(brand);
        when(this.productEntityMapper.toDomain(productEntity)).thenReturn(product);
        
        // Act
        final var result = this.priceEntityMapper.toDomain(listOf(priceEntity));
        
        // Assert
        assertEquals(priceEntity.getBrand().getId(), result.get(0).brand().id());
        assertEquals(priceEntity.getBrand().getName(), result.get(0).brand().name());
        assertEquals(priceEntity.getProduct().getId(), result.get(0).product().id());
        assertEquals(priceEntity.getStartDate(), result.get(0).startDate());
        assertEquals(priceEntity.getEndDate(), result.get(0).endDate());
        assertEquals(priceEntity.getPriceCode(), result.get(0).priceCode());
        assertEquals(priceEntity.getPriority(), result.get(0).priority());
        assertEquals(priceEntity.getPrice(), result.get(0).price());
    }
}
