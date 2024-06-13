package com.inditex.prices.persistence;

import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.ports.persistence.PricePersistencePort;
import com.inditex.prices.persistence.entity.PriceEntity;
import com.inditex.prices.persistence.mapper.PriceEntityMapper;
import com.inditex.prices.persistence.repository.PriceEntityRepository;
import com.inditex.prices.persistence.specification.PriceEntitySpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Component
@AllArgsConstructor
public class PricePersistenceAdapter implements PricePersistencePort {
    
    private final PriceEntityRepository priceEntityRepository;
    private final PriceEntitySpecification priceEntitySpecification;
    private final PriceEntityMapper priceEntityMapper;
    
    @Override
    public List<Price> findApplicablePrice(final UUID brandId, final UUID productId, final LocalDateTime date) {

        final Specification<PriceEntity> spec = Specification.where(this.priceEntitySpecification.hasBrand(brandId))
                .and(this.priceEntitySpecification.hasProduct(productId))
                .and(this.priceEntitySpecification.greaterThanOrEqualToStartDate(date))
                .and(this.priceEntitySpecification.lessThanOrEqualToEndDate(date));
        
        return this.priceEntityMapper.toDomain(this.priceEntityRepository.findAll(spec));
        
    }
}
