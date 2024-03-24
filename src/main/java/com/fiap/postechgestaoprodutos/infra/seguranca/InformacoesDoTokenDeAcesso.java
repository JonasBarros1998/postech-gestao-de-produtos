package com.fiap.postechgestaoprodutos.infra.seguranca;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "buscar-dados-token", url = "${aplicacao.token.google-api.url}")
public interface InformacoesDoTokenDeAcesso {

	@GetMapping(value = "/tokeninfo", params = "id_token")
	ResponseEntity<Usuario> buscar(
		@RequestParam(name = "id_token") String id
	);
}
