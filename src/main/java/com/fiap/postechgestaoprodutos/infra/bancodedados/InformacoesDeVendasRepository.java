package com.fiap.postechgestaoprodutos.infra.bancodedados;

import com.fiap.postechgestaoprodutos.dominio.InformacoesDaVenda;
import com.fiap.postechgestaoprodutos.dominio.InformacoesDaVendaJuncao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacoesDeVendasRepository extends JpaRepository<InformacoesDaVenda, InformacoesDaVendaJuncao>  {
}
