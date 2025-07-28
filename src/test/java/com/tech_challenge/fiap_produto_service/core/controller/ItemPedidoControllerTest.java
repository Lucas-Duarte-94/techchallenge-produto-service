package com.tech_challenge.fiap_produto_service.core.controller;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;
import com.tech_challenge.fiap_produto_service.core.usecase.ProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemPedidoController.class)
public class ItemPedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductUseCase productUseCase;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        produto1 = Produto.builder()
                .productSKU("SKU001")
                .preco(new BigDecimal("10.00"))
                .description("Description 1")
                .build();

        produto2 = Produto.builder()
                .productSKU("SKU002")
                .preco(new BigDecimal("20.00"))
                .description("Description 2")
                .build();
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() throws Exception {
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        when(productUseCase.getAll()).thenReturn(produtos);

        mockMvc.perform(get("/produto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productSKU").value(produto1.getProductSKU()))
                .andExpect(jsonPath("$[1].productSKU").value(produto2.getProductSKU()));
    }

    @Test
    void getProdutoById_shouldReturnProduct_whenProductExists() throws Exception {
        when(productUseCase.findByProductSKU("SKU001")).thenReturn(produto1);

        mockMvc.perform(get("/produto/{product_sku}", "SKU001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productSKU").value(produto1.getProductSKU()));
    }
}
