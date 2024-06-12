package com.inditex.prices.usecase;


import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.ports.persistence.PricePersistencePort;
import com.inditex.prices.domain.usecase.FindPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindPriceUseCaseImpl implements FindPriceUseCase {
    
    private final PricePersistencePort pricePersistencePort;

    @Override
    public Price findPrice(final UUID brandId, final UUID productId, final LocalDateTime date) {
        return this.pricePersistencePort.findPrice(brandId, productId, date).stream()
                .max(Comparator.comparingInt(Price::priority)).orElse(null);
    }
}
