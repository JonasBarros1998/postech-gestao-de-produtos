package com.fiap.postechgestaoprodutos.dominio;

import java.math.BigDecimal;
import java.util.UUID;

public class Carrinho {

	private UUID id;

	private BigDecimal valor;

	private Integer quantidade;

	public Carrinho(UUID id, BigDecimal valor, Integer quantidade) {
		this.id = id;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}
