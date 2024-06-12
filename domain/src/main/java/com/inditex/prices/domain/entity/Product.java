package com.inditex.prices.domain.entity;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Product(UUID id) {
}
