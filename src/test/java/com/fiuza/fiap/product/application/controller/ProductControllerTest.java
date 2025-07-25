package com.fiuza.fiap.product.application.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fiuza.fiap.product.application.controllers.ProductController;
import com.fiuza.fiap.product.core.dto.ProductRequest;
import com.fiuza.fiap.product.core.entities.Product;
import com.fiuza.fiap.product.core.usecases.CreateProductUseCase;
import com.fiuza.fiap.product.core.usecases.GetProductBySkuUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @MockBean
    private GetProductBySkuUseCase getProductBySkuUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        ProductRequest request = new ProductRequest("Coca-Cola", BigDecimal.valueOf(5.0));
        Product created = new Product(1L, "Coca-Cola", BigDecimal.valueOf(5.0));
        Mockito.when(createProductUseCase.execute(any(Product.class))).thenReturn(created);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Coca-Cola")))
                .andExpect(jsonPath("$.price", is(5.0)));
    }

    @Test
    void shouldGetProductBySkuSuccessfully() throws Exception {
        Product product = new Product(10L, "Pepsi", BigDecimal.valueOf(4.5));
        Mockito.when(getProductBySkuUseCase.execute(eq(10L))).thenReturn(product);

        mockMvc.perform(get("/product/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Pepsi")))
                .andExpect(jsonPath("$.price", is(4.5)));
    }
}