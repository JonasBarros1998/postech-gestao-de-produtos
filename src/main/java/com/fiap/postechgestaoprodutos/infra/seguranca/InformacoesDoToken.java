package com.fiap.postechgestaoprodutos.infra.seguranca;

import com.fiap.postechgestaoprodutos.view.exceptions.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacoesDoToken {

	InformacoesDoTokenDeAcesso informacoesDoTokenDeAcesso;


	@Autowired
	InformacoesDoToken(InformacoesDoTokenDeAcesso informacoesDoTokenDeAcesso) {
		this.informacoesDoTokenDeAcesso = informacoesDoTokenDeAcesso;
	}

	public Usuario buscar(String token) {
		var resposta = this.informacoesDoTokenDeAcesso.buscar(token);
		if(resposta.getStatusCode().is2xxSuccessful()) {
			return resposta.getBody();
		}

		throw new TokenException("Token invalido");
	}
}
