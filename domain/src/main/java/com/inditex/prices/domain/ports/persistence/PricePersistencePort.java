package com.inditex.prices.domain.ports.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.inditex.prices.domain.entity.Price;

public interface PricePersistencePort {

  List<Price> findApplicablePrice(UUID brandId, UUID productId, LocalDateTime date);

}
