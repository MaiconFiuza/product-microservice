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
        when(productGateway.hasProduct(product.getId())).thenReturn(false);
        when(productGateway.create(product)).thenReturn(product);

        Product result = useCase.execute(product);

        assertEquals(product, result);
        verify(productGateway).hasProduct(product.getId());
        verify(productGateway).create(product);
    }

    @Test
    void shouldThrowAlreadyExistExceptionWhenProductExists() {
        Product product = new Product(2L, "Produto B", BigDecimal.valueOf(30));
        when(productGateway.hasProduct(product.getId())).thenReturn(true);

        AlreadyExistException exception = assertThrows(
                AlreadyExistException.class,
                () -> useCase.execute(product)
        );

        assertEquals("Produto já está cadastrado", exception.getMessage());
        verify(productGateway).hasProduct(product.getId());
        verify(productGateway, never()).create(any());
    }

    @Test
    void shouldThrowInternalServerErrorOnUnexpectedException() {
        Product product = new Product(3L, "Produto C", BigDecimal.valueOf(40));
        when(productGateway.hasProduct(product.getId())).thenReturn(false);
        when(productGateway.create(product)).thenThrow(new RuntimeException("Erro inesperado"));

        InternalServerError exception = assertThrows(
                InternalServerError.class,
                () -> useCase.execute(product)
        );

        assertEquals("Erro inesperado", exception.getMessage());
        verify(productGateway).hasProduct(product.getId());
        verify(productGateway).create(product);
    }
}

