package com.inditex.prices.api.rest.mapper;

import com.inditex.prices.api.rest.model.PriceDetail;
import com.inditex.prices.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "brandId", source = "source.brand.id")
    @Mapping(target = "productId", source = "source.product.id")
    PriceDetail toPriceDetail(Price source);
}
