package com.fiap.postechgestaoprodutos.infra.seguranca;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenRequestFiltro extends OncePerRequestFilter {

	Autenticar autenticar;

	Autorizar autorizar;

	@Autowired
	TokenRequestFiltro(Autenticar autenticar, Autorizar autorizar) {
		this.autenticar = autenticar;
		this.autorizar = autorizar;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain filterChain) throws ServletException, IOException {
		Usuario usuario = this.autenticar.usuario(requisicao);
		this.autorizar.usuario(usuario);

		filterChain.doFilter(requisicao, resposta);
	}

}
