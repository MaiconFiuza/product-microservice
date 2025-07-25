package com.fiuza.fiap.product.core.gateway;

import com.fiuza.fiap.product.core.entities.Product;

import java.util.Optional;

public interface ProductGateway {
    Product create(Product product);

    Optional<Product> findByName(String name);

    Optional<Product> findBySku(Long sku);

    boolean hasProduct(Long sku);
}
