package com.inditex.prices.persistence.mapper;


import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class, ProductEntityMapper.class})
public interface PriceEntityMapper {

    @Mapping(source = "brandEntity", target = "brand")
    @Mapping(source = "productEntity", target = "product")
    List<Price> toDomain(List<PriceEntity> source);
    
}
