package com.tech_challenge.fiap_produto_service.core.usecase;

import java.util.List;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;

public interface ProductUseCase {
    List<Produto> getAll();

    Produto findByProductSKU(String productSKU);
}
