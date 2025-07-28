package com.tech_challenge.fiap_produto_service.core.usecase;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;
import com.tech_challenge.fiap_produto_service.core.exception.ProductNotFoundException;
import com.tech_challenge.fiap_produto_service.core.gateway.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductImplementationTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductImplementation productImplementation;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void getAll_shouldReturnListOfProducts() {
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        when(productRepository.findAll()).thenReturn(produtos);

        List<Produto> result = productImplementation.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(produto1.getProductSKU(), result.get(0).getProductSKU());
        assertEquals(produto2.getProductSKU(), result.get(1).getProductSKU());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findByProductSKU_shouldReturnProduct_whenProductExists() {
        when(productRepository.findById("SKU001")).thenReturn(Optional.of(produto1));

        Produto result = productImplementation.findByProductSKU("SKU001");

        assertNotNull(result);
        assertEquals(produto1.getProductSKU(), result.getProductSKU());
        verify(productRepository, times(1)).findById("SKU001");
    }

    @Test
    void findByProductSKU_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        when(productRepository.findById("SKU003")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productImplementation.findByProductSKU("SKU003"));
        verify(productRepository, times(1)).findById("SKU003");
    }
}
