package com.fiuza.fiap.product.application.controllers;

import com.fiuza.fiap.product.core.dto.ProductRequest;
import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.usecases.CreateProductUseCase;
import com.fiuza.fiap.product.core.usecases.GetProductBySkuUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static  final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final CreateProductUseCase createProductUseCase;
    private final GetProductBySkuUseCase getProductBySkuUseCase;

    public ProductController(
       CreateProductUseCase createProductUseCase,
       GetProductBySkuUseCase getProductBySkuUseCase
    ) {
        this.createProductUseCase = createProductUseCase;
        this.getProductBySkuUseCase = getProductBySkuUseCase;
    }

    @Operation(
            description = "Criar um produto",
            summary = "Endpoint responsável por criar um produto",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest) {
        logger.info("POST");
        Product productInput = new Product(productRequest.name(), productRequest.price());
        Product result = createProductUseCase.execute(productInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            description = "Busca um produto",
            summary = "Endpoint responsável por buscar um produto",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{sku}")
    public ResponseEntity<Product> find(@PathVariable Long sku) {
        logger.info("GET /{}", sku);
        Product result = getProductBySkuUseCase.execute(sku);
        return ResponseEntity.ok(result);
    }

}
