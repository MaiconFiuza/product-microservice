package com.fiuza.fiap.product.infra.adapter;

import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.gateway.ProductGateway;
import com.fiuza.fiap.product.infra.mappers.ProductMapper;
import com.fiuza.fiap.product.infra.models.ProductModel;
import com.fiuza.fiap.product.infra.repository.ProductRepository;

import java.util.Optional;

public class ProductRepositoryImp implements ProductGateway {

    private final ProductRepository productRepository;

    public ProductRepositoryImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        ProductModel productModel = ProductMapper.productToProductModel(product);
        ProductModel productSaved = productRepository.save(productModel);
        return ProductMapper.productModelToProduct(productSaved);
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<ProductModel> productSaved = productRepository.findByName(name);
        return Optional.of(ProductMapper.productModelToProduct(productSaved.get()));
    }

    @Override
    public Optional<Product> findBySku(Long sku) {
        Optional<ProductModel> productSaved = productRepository.findById(sku);
        return Optional.of(ProductMapper.productModelToProduct(productSaved.get()));
    }

    @Override
    public boolean hasProduct(Long sku) {
        return productRepository.findById(sku).isPresent();
    }
}
