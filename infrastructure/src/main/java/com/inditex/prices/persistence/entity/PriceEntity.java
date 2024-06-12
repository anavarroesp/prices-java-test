package com.inditex.prices.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "prices")
public class PriceEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "price_id", updatable = false, nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "price_code", nullable = false)
    private Integer priceCode;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(name = "priority", nullable = false)   
    private Integer priority;
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
    @Column(name = "curr", length = 3, nullable = false)
    private String currency;


}
