package com.inditex.prices.domain.entity;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Price(Product product, 
                    Brand brand, 
                    Integer priceList, 
                    LocalDateTime startDate, 
                    LocalDateTime endDate, 
                    BigDecimal price,
                    Integer priority) {

    

}
