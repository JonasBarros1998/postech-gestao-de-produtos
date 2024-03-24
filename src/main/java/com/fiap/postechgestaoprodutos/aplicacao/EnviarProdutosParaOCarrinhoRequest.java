package com.fiap.postechgestaoprodutos.aplicacao;

import com.fiap.postechgestaoprodutos.dominio.Finalizar;
import com.fiap.postechgestaoprodutos.view.form.FinalizarComprasForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "finalizar-compra", url = "${aplicacao.carrinho.base-url}")
public interface EnviarProdutosParaOCarrinhoRequest {

	@PostMapping("/api/carrinho/adicionar")
	ResponseEntity<FinalizarComprasForm> finalizarCompra(
		@RequestBody Finalizar finalizarCompras,
		@RequestHeader String Authorization
	);

}
