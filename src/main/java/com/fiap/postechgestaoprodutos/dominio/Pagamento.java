package com.fiap.postechgestaoprodutos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class Pagamento {

	protected Pagamento() {}

	@Column(name = "pagamento_id", nullable = false)
	private UUID id;

	public Pagamento(UUID pagamento) {
		this.id = pagamento;
	}

	public UUID getPagamento() {
		return id;
	}
}
