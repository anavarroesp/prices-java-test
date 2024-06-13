package com.inditex.prices.domain.ports.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.inditex.prices.domain.entity.Price;

public interface PricePersistencePort {

  /**
   * Finds the applicable prices for a given product from a specific brand at a particular date and time.
   *
   * @param brandId the UUID of the brand
   * @param productId the UUID of the product
   * @param date the date and time at which the prices are applicable
   * @return a list of applicable Prices
   */
  List<Price> findApplicablePrice(UUID brandId, UUID productId, LocalDateTime date);

}
