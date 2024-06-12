package com.inditex.prices.domain.entity;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Price(Product product, 
                    Brand brand, 
                    Integer priceCode, 
                    LocalDateTime startDate, 
                    LocalDateTime endDate, 
                    BigDecimal price,
                    Integer priority) {

    

}
