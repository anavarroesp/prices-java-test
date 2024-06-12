package com.inditex.prices.persistence.mapper;

import com.inditex.prices.domain.entity.Product;
import com.inditex.prices.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toDomain(ProductEntity source);
}
