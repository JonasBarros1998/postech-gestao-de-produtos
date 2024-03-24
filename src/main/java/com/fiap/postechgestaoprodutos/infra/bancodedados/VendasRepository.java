package com.fiap.postechgestaoprodutos.infra.bancodedados;

import com.fiap.postechgestaoprodutos.dominio.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendasRepository extends JpaRepository<Vendas, UUID> {
}
