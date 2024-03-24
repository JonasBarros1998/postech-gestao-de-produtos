package com.fiap.postechgestaoprodutos.infra.seguranca;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class Autenticar {

	private InformacoesDoToken informacoesDoToken;
	Autenticar(InformacoesDoToken informacoesDoToken) {
		this.informacoesDoToken = informacoesDoToken;
	}

	public Usuario usuario(HttpServletRequest requisicao) {
		var token = Token.recuperar(requisicao);
		return this.informacoesDoToken.buscar(token);
	}
}
