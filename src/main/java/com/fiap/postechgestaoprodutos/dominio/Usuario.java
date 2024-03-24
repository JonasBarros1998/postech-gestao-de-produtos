package com.fiap.postechgestaoprodutos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class Usuario {

	public Usuario() {}

	@Column(name = "usuario_id", nullable = false)
	private UUID id;

	public Usuario(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

}
