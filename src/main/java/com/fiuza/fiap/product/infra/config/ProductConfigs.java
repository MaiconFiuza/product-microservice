package com.fiuza.fiap.product.infra.config;

import com.fiuza.fiap.product.core.gateway.ProductGateway;
import com.fiuza.fiap.product.core.usecases.CreateProductUseCase;
import com.fiuza.fiap.product.core.usecases.GetProductBySkuUseCase;
import com.fiuza.fiap.product.infra.adapter.ProductRepositoryImp;
import com.fiuza.fiap.product.infra.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfigs {

    @Bean
    public ProductGateway productGateway(ProductRepository productRepository) {
        return new ProductRepositoryImp(productRepository);
    }
    @Bean
    public CreateProductUseCase createProductUseCase(ProductGateway productGateway) {
        return new CreateProductUseCase(productGateway);
    }

    @Bean
    public GetProductBySkuUseCase getProductBySkuUseCase(ProductGateway productGateway) {
        return new GetProductBySkuUseCase(productGateway);
    }
}
