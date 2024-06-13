package com.inditex.prices.domain.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import com.inditex.prices.domain.entity.Price;

public interface FindApplicablePriceUseCase {

  /**
   * Finds the applicable price for a given product from a specific brand at a particular date and time.
   *
   * @param brandId the UUID of the brand
   * @param productId the UUID of the product
   * @param date the date and time at which the price is applicable
   * @return the applicable Price
   */
  Price findApplicablePrice(UUID brandId, UUID productId, LocalDateTime date);
  
}
