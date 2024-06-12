package com.inditex.prices.persistence.specification;

import com.inditex.prices.persistence.entity.PriceEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PriceEntitySpecification {
    
   public Specification<PriceEntity> hasBrand(final UUID brandId) {
        return (root, query, criteriaBuilder) -> {
            if (brandId == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("brand").get("id"), brandId);
        };
    }

    public Specification<PriceEntity> hasProduct(final UUID productId) {
        return (root, query, criteriaBuilder) -> {
            if (productId == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("product").get("id"), productId);
        };
    }

    public Specification<PriceEntity> greaterThanOrEqualToStartDate(final LocalDateTime startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
        };
    }

    public Specification<PriceEntity> lessThanOrEqualToEndDate(final LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (endDate == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
        };
    }
}
