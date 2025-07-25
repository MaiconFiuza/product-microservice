package com.fiuza.fiap.product.core.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price
) {
}
