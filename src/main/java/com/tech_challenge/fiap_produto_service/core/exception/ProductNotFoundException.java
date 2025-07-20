package com.tech_challenge.fiap_produto_service.core.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Produto não encontrado!");
    }
}
