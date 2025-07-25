package com.fiuza.fiap.product.infra.mappers;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.infra.models.ProductModel;

public class ProductMapper {

    public static ProductModel productToProductModel(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getPrice()
        ) ;
    }

    public static Product productModelToProduct(ProductModel productModel) {
        return new Product(
                productModel.getId(),
                productModel.getName(),
                productModel.getPrice()
        ) ;
    }
}
