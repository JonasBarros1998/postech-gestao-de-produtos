package com.fiap.postechgestaoprodutos.infra.seguranca;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Usuario(
	String email,

	@JsonProperty("name")
	String nome,

	@JsonProperty("given_name")
	String nomeDeIdentificacao,

	@JsonProperty("family_name")
	String sobrenome,

	@JsonProperty("exp")
	String expirar,

	@JsonProperty("alg")
	String algoritmo,

	@JsonProperty("kid")
	String id
) {}
