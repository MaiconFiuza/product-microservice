package com.fiuza.fiap.product.core.usecases;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.exceptions.InternalServerError;
import com.fiuza.fiap.product.core.exceptions.NotFoundException;
import com.fiuza.fiap.product.core.gateway.ProductGateway;

public class GetProductBySkuUseCase {
    private final ProductGateway productGateway;

    public GetProductBySkuUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(Long sku) {
        try {
            return productGateway.findBySku(sku).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado. Tente novamente");
        }
    }
}
