package com.inditex.prices.persistence.repository;

import com.inditex.prices.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, UUID>, JpaSpecificationExecutor<PriceEntity> {
    
}
