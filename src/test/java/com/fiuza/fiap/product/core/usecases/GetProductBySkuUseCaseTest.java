package com.fiuza.fiap.product.core.usecases;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.exceptions.NotFoundException;
import com.fiuza.fiap.product.core.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProductBySkuUseCaseTest {

    private ProductGateway productGateway;
    private GetProductBySkuUseCase useCase;

    @BeforeEach
    void setUp() {
        productGateway = mock(ProductGateway.class);
        useCase = new GetProductBySkuUseCase(productGateway);
    }

    @Test
    void shouldReturnProductWhenFound() {
        Long sku = 1L;
        Product expectedProduct = new Product(sku, "Produto Teste", BigDecimal.valueOf(100));
        when(productGateway.findBySku(sku)).thenReturn(Optional.of(expectedProduct));

        Product result = useCase.execute(sku);

        assertEquals(expectedProduct, result);
        verify(productGateway).findBySku(sku);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProductNotFound() {
        Long sku = 2L;
        when(productGateway.findBySku(sku)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> useCase.execute(sku)
        );

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(productGateway).findBySku(sku);
    }
}

