package com.fiap.postechgestaoprodutos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class InformacoesDaVendaJuncao implements Serializable {

	@Column(name = "produto_id", nullable = false)
	private UUID produtoId;

	@Column(name = "venda_id", nullable = false)
	private UUID vendaId;

	public InformacoesDaVendaJuncao() {}

	public InformacoesDaVendaJuncao(UUID vendaId, UUID produtoId) {
		this.vendaId = vendaId;
		this.produtoId = produtoId;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof InformacoesDaVendaJuncao that)) return false;
		return Objects.equals(produtoId, that.produtoId) && Objects.equals(vendaId, that.vendaId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(produtoId, vendaId);
	}

	public void setProdutoId(UUID produtoId) {
		this.produtoId = produtoId;
	}

	public void setVendaId(UUID vendaId) {
		this.vendaId = vendaId;
	}
}
