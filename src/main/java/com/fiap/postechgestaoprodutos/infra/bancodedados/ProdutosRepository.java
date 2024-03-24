package com.fiap.postechgestaoprodutos.infra.bancodedados;

import com.fiap.postechgestaoprodutos.dominio.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {
}
