package com.fiap.postechgestaoprodutos.view.exceptions;

public class ProdutoNaoExisteException extends RuntimeException {
	public ProdutoNaoExisteException(String message) {
		super(message);
	}
}
