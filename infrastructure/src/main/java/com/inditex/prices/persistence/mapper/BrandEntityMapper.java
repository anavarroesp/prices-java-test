package com.inditex.prices.persistence.mapper;


import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.persistence.entity.BrandEntity;
import com.inditex.prices.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    List<Brand> toDomain(List<BrandEntity> source);
}
