package com.inditex.prices.persistence.mapper;

import com.inditex.prices.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductEntityMapperTest {

    private final ProductEntityMapper productEntityMapper = Mappers.getMapper(ProductEntityMapper.class);
    
    @Test
    void shoudMapProductEntityToProductDomain() {
        // Arrange
        final var productEntity = new ProductEntity();
        productEntity.setId(UUID.randomUUID());
        
        // Act
        final var result = this.productEntityMapper.toDomain(productEntity);
        
        // Assert
        assertEquals(productEntity.getId(), result.id());

    }
}
