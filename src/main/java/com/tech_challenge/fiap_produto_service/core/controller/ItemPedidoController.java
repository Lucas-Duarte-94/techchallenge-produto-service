package com.tech_challenge.fiap_produto_service.core.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;
import com.tech_challenge.fiap_produto_service.core.usecase.ProductUseCase;

@RestController
@RequestMapping("/produto")
public class ItemPedidoController {
    private ProductUseCase productUseCase;

    private final Logger logger = LoggerFactory.getLogger(ItemPedidoController.class);

    public ItemPedidoController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProducts() {
        logger.debug("Requisiçao no 8082/produto");
        var products = this.productUseCase.getAll();

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{product_sku}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("product_sku") String productSKU) {
        var product = this.productUseCase.findByProductSKU(productSKU);
        return ResponseEntity.ok().body(product);
    }
}
