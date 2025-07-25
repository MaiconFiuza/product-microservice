package com.fiuza.fiap.product.infra.mappers;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.infra.models.ProductModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void shouldConvertProductToProductModel() {
        Product product = new Product(1L, "Produto Teste", BigDecimal.valueOf(99.99));

        ProductModel model = ProductMapper.productToProductModel(product);

        assertEquals(product.getId(), model.getId());
        assertEquals(product.getName(), model.getName());
        assertEquals(product.getPrice(), model.getPrice());
    }

    @Test
    void shouldConvertProductModelToProduct() {
        ProductModel model = new ProductModel(1L, "Produto Modelo", BigDecimal.valueOf(49.90));

        Product product = ProductMapper.productModelToProduct(model);

        assertEquals(model.getId(), product.getId());
        assertEquals(model.getName(), product.getName());
        assertEquals(model.getPrice(), product.getPrice());
    }
}

