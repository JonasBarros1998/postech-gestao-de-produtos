package com.fiap.postechgestaoprodutos.view.form;

import jakarta.validation.constraints.NotNull;

public record EditarQuantidadeDoProdutoForm(

	@NotNull(message = "A quantidade do produto e obrigatoria")
	Integer quantidade
) {
}
