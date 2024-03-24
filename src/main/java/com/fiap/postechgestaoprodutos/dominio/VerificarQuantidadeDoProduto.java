package com.fiap.postechgestaoprodutos.dominio;

public class VerificarQuantidadeDoProduto {
	public static Boolean verificar(Integer quantidadeDoProdutoNoEstoque, Integer quantidadeQueDeveraSerComprada) {
		if(quantidadeDoProdutoNoEstoque < quantidadeQueDeveraSerComprada) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public static Integer calcularNovaQuantidade(Integer quantidadeDoProdutoNoEstoque, Integer quantidadeQueDeveraSerComprada) {
		return quantidadeDoProdutoNoEstoque - quantidadeQueDeveraSerComprada;
	}
}
