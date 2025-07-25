package com.fiuza.fiap.product.core.usecases;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.product.core.exceptions.InternalServerError;
import com.fiuza.fiap.product.core.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {

    private ProductGateway productGateway;
    private CreateProductUseCase useCase;

    @BeforeEach
    void setUp() {
        productGateway = mock(ProductGateway.class);
        useCase = new CreateProductUseCase(productGateway);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        Product product = new Product(1L, "Produto A", BigDecimal.valueOf(50));
        when(productGateway.create(product)).thenReturn(product);

        Product result = useCase.execute(product);

        assertEquals(product, result);

        verify(productGateway).create(product);
    }
}

