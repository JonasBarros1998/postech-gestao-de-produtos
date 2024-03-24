package com.fiap.postechgestaoprodutos.dominio;


import jakarta.persistence.*;

@Entity
@Table(name = "informacoes_da_venda")
public class InformacoesDaVenda {

	@EmbeddedId
	InformacoesDaVendaJuncao informacoesDaVendaJuncao;

	@ManyToOne
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	Produtos produtos;

	@ManyToOne
	@MapsId("vendaId")
	@JoinColumn(name = "venda_id")
	Vendas vendas;

	Integer quantidade;


	public InformacoesDaVenda(Produtos produtos, Vendas vendas, Integer quantidade, InformacoesDaVendaJuncao informacoesDaVendaJuncao) {
		this.produtos = produtos;
		this.vendas = vendas;
		this.quantidade = quantidade;
		this.informacoesDaVendaJuncao = informacoesDaVendaJuncao;
	}

	public InformacoesDaVenda() {}

	public InformacoesDaVendaJuncao getInformacoesDaVendaJuncao() {
		return informacoesDaVendaJuncao;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}
