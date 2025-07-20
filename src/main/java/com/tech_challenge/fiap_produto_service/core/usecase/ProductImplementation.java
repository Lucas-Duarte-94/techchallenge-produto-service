package com.tech_challenge.fiap_produto_service.core.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;
import com.tech_challenge.fiap_produto_service.core.exception.ProductNotFoundException;
import com.tech_challenge.fiap_produto_service.core.gateway.ProductRepository;

@Service
public class ProductImplementation implements ProductUseCase {
    private ProductRepository productRepository;

    public ProductImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Produto> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Produto findByProductSKU(String productSKU) {
        return this.productRepository.findById(productSKU).orElseThrow(ProductNotFoundException::new);
    }
}
