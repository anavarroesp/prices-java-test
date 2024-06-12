package com.inditex.prices.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "brands")
public class BrandEntity implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "brand_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "brand_name", nullable = false)
    private String name;
    
}
