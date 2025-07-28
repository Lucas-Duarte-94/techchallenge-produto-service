package com.tech_challenge.fiap_produto_service.core.controller;

import com.tech_challenge.fiap_produto_service.core.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionControllerTest {

    private GlobalExceptionController globalExceptionController = new GlobalExceptionController();

    @Test
    void handleProductNotFound_shouldReturnNotFoundStatusAndExceptionMessage() {
        ProductNotFoundException ex = new ProductNotFoundException();

        ResponseEntity<String> response = globalExceptionController.handleProductNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Produto não encontrado!", response.getBody());
    }
}
