package com.inditex.prices.domain.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import com.inditex.prices.domain.entity.Price;

public interface FindApplicablePriceUseCase {
  
  Price findApplicablePrice(UUID brandId, UUID productId, LocalDateTime date);
  
}
