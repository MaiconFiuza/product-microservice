package com.fiuza.fiap.product.core.usecases;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.product.core.exceptions.InternalServerError;
import com.fiuza.fiap.product.core.gateway.ProductGateway;

public class CreateProductUseCase {
    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(Product product) {
        try {

            Product createdProduct = productGateway.create(product);

            return createdProduct;
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

}
