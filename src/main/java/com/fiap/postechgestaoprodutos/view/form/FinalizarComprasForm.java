package com.fiap.postechgestaoprodutos.view.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FinalizarComprasForm(

	@NotNull(message = "o campo produtos e obrigatorio")
	@Valid
	List<FinalizarCompraProdutoForm> produtos,

	@NotNull
	@Valid
	UsuarioForm usuario,

	@Valid
	@NotNull
	PagamentoForm pagamento

) { }
