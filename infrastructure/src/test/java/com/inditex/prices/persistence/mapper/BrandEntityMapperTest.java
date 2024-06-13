package com.inditex.prices.persistence.mapper;


import com.inditex.prices.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandEntityMapperTest {

    private final BrandEntityMapper brandEntityMapper = Mappers.getMapper(BrandEntityMapper.class);
    
    @Test
    void shoudMapBrandEntityToBrandDomain() {
        // Arrange
        final var brandEntity = new BrandEntity();
        brandEntity.setId(UUID.randomUUID());
        brandEntity.setName("Zara");
        
        // Act
        final var result = this.brandEntityMapper.toDomain(brandEntity);
        
        // Assert
        assertEquals(brandEntity.getId(), result.id());
        assertEquals(brandEntity.getName(), result.name());
    }
    
}
