package com.fiuza.fiap.product.infra.adapter;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.infra.models.ProductModel;
import com.fiuza.fiap.product.infra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryImpTest {

    private ProductRepository productRepository;
    private ProductRepositoryImp productRepositoryImp;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productRepositoryImp = new ProductRepositoryImp(productRepository);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        Product product = new Product(1L, "Produto", BigDecimal.valueOf(29.90));
        ProductModel model = new ProductModel(1L, "Produto", BigDecimal.valueOf(29.90));
        when(productRepository.save(any(ProductModel.class))).thenReturn(model);

        Product created = productRepositoryImp.create(product);

        assertEquals(product.getId(), created.getId());
        assertEquals(product.getName(), created.getName());
        assertEquals(product.getPrice(), created.getPrice());
        verify(productRepository).save(any(ProductModel.class));
    }

    @Test
    void shouldFindByName() {
        String name = "Coca-Cola";
        ProductModel model = new ProductModel(1L, name, BigDecimal.valueOf(5.50));
        when(productRepository.findByName(name)).thenReturn(Optional.of(model));

        Optional<Product> result = productRepositoryImp.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
        assertEquals(model.getId(), result.get().getId());
    }

    @Test
    void shouldFindBySku() {
        Long sku = 123L;
        ProductModel model = new ProductModel(sku, "Produto", BigDecimal.valueOf(10));
        when(productRepository.findById(sku)).thenReturn(Optional.of(model));

        Optional<Product> result = productRepositoryImp.findBySku(sku);

        assertTrue(result.isPresent());
        assertEquals(sku, result.get().getId());
        assertEquals(model.getName(), result.get().getName());
    }

    @Test
    void shouldCheckIfProductExists() {
        Long sku = 456L;
        when(productRepository.findById(sku)).thenReturn(Optional.of(new ProductModel()));

        boolean exists = productRepositoryImp.hasProduct(sku);

        assertTrue(exists);
        verify(productRepository).findById(sku);
    }

    @Test
    void shouldReturnFalseIfProductDoesNotExist() {
        Long sku = 789L;
        when(productRepository.findById(sku)).thenReturn(Optional.empty());

        boolean exists = productRepositoryImp.hasProduct(sku);

        assertFalse(exists);
    }
}
