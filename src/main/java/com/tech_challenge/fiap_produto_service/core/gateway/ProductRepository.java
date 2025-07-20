package com.tech_challenge.fiap_produto_service.core.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech_challenge.fiap_produto_service.core.domain.entity.Produto;

@Repository
public interface ProductRepository extends JpaRepository<Produto, String> {

}
