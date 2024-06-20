package com.inditex.prices.usecase;


import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.exception.ApplicablePriceNotFoundException;
import com.inditex.prices.domain.ports.persistence.PricePersistencePort;
import com.inditex.prices.domain.usecase.FindApplicablePriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class FindApplicablePriceUseCaseImpl implements FindApplicablePriceUseCase {
    
    private final PricePersistencePort pricePersistencePort;


    @Override
    public Price findApplicablePrice(final UUID brandId, final UUID productId, final LocalDateTime date, final Integer quantity) throws IllegalArgumentException {
        return this.pricePersistencePort.findApplicablePrice(brandId, productId, date).stream()
                .map(price -> {
                    if (quantity == null) {
                        return price;
                    }
                    if (quantity <= 0) {
                        throw new IllegalArgumentException("Price must be greater than zero");
                    }
                    
                    final BigDecimal finalPrice = price.price().multiply(BigDecimal.valueOf(quantity));
                    return price.builder()
                            .price(finalPrice)
                            .product(price.product())
                            .brand(price.brand())
                            .priceCode(price.priceCode())
                            .startDate(price.startDate())
                            .endDate(price.endDate())
                            .priority(price.priority())
                            .build();
                })
                .max(Comparator.comparingInt(Price::priority))
                .orElseThrow(ApplicablePriceNotFoundException::new);
    }
}
