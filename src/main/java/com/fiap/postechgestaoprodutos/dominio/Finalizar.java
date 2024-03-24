package com.fiap.postechgestaoprodutos.dominio;

import java.util.List;
import java.util.UUID;

public class Finalizar {

	private UUID usuarioID;

	private UUID pagamentoID;

	private UUID vendaID;

	private List<Carrinho> produtos;

	public Finalizar(UUID usuarioID, UUID pagamentoID, UUID vendaID, List<Carrinho> produtos) {
		this.usuarioID = usuarioID;
		this.pagamentoID = pagamentoID;
		this.vendaID = vendaID;
		this.produtos = produtos;
	}

	public UUID getUsuarioID() {
		return usuarioID;
	}

	public UUID getPagamentoID() {
		return pagamentoID;
	}

	public UUID getVendaID() {
		return vendaID;
	}

	public List<Carrinho> getProdutos() {
		return produtos;
	}
}
