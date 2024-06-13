package com.inditex.prices.usecase;


import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.exception.ApplicablePriceNotFoundException;
import com.inditex.prices.domain.ports.persistence.PricePersistencePort;
import com.inditex.prices.domain.usecase.FindApplicablePriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Price findApplicablePrice(final UUID brandId, final UUID productId, final LocalDateTime date) {
        return this.pricePersistencePort.findApplicablePrice(brandId, productId, date).stream()
                .max(Comparator.comparingInt(Price::priority)).orElseThrow(ApplicablePriceNotFoundException::new);
    }
}
