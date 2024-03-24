package com.fiap.postechgestaoprodutos.infra.seguranca;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Autorizar {

	@Value("${aplicacao.administrador.email}")
	private String emailAdministrador;

	public void usuario(Usuario usuario) {

		List<GrantedAuthority> regrasDeAutorizacao = new ArrayList<>();

		if(usuario.email().equals(emailAdministrador)) {
			regrasDeAutorizacao.add(new SimpleGrantedAuthority("ROLE_USUARIO_ADMIN"));
		}

		UserDetails criarUsuario = new User(usuario.nome(), "", regrasDeAutorizacao);

		var authentication = new UsernamePasswordAuthenticationToken(usuario, null, criarUsuario.getAuthorities());

		SecurityContextHolder
			.getContext()
			.setAuthentication(authentication);
	}

}
