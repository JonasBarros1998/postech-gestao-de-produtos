package com.fiap.postechgestaoprodutos.view.form;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.UUID;

import java.math.BigDecimal;

public record ProdutosForm(

	@Nullable
	UUID id,

	@NotEmpty(message = "campo nome e obrigatorio")
	String nome,

	@NotNull(message = "campo valor e obrigatorio")
	BigDecimal valor,

	@NotEmpty(message = "campo descricao e obrigatorio")
	@Length(max = 200)
	String descricao,

	@NotEmpty(message = "campo categoria e obrigatorio")
	String categoria,

	@NotNull(message = "campo quantidade e obrigatorio")
	Integer quantidade

) {}
