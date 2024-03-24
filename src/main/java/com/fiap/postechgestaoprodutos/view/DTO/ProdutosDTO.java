package com.fiap.postechgestaoprodutos.view.DTO;

import java.math.BigDecimal;

public class ProdutosDTO {
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String categoria;

	public ProdutosDTO(String nome, String descricao, BigDecimal valor, String categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getCategoria() {
		return categoria;
	}

}
