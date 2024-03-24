package com.fiap.postechgestaoprodutos.infra.seguranca;

import com.fiap.postechgestaoprodutos.view.exceptions.TokenException;
import jakarta.servlet.http.HttpServletRequest;

public class Token {

	private static String tokenDoUsuario;

	public static String recuperar(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		if(token == null) {
			throw new TokenException("envie o token JWT nos headers da sua requisicao");
		}
		tokenDoUsuario = token.replace("Bearer ", "");
		return tokenDoUsuario;
	}

	public static String acessarToken() {
		return tokenDoUsuario;
	}
}
