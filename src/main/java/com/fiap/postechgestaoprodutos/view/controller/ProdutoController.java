package com.fiap.postechgestaoprodutos.view.controller;

import com.fiap.postechgestaoprodutos.aplicacao.FinalizarCompras;
import com.fiap.postechgestaoprodutos.aplicacao.GerenciarProdutos;
import com.fiap.postechgestaoprodutos.view.form.EditarQuantidadeDoProdutoForm;
import com.fiap.postechgestaoprodutos.view.form.FinalizarComprasForm;
import com.fiap.postechgestaoprodutos.view.form.ProdutosForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	GerenciarProdutos gerenciarProdutos;

	FinalizarCompras finalizarCompras;

	@Autowired
	ProdutoController(GerenciarProdutos gerenciarProdutos, FinalizarCompras finalizarCompras) {
		this.gerenciarProdutos = gerenciarProdutos;
		this.finalizarCompras = finalizarCompras;
	}

	@PostMapping
	public ResponseEntity<ProdutosForm> salvar(@RequestBody @Valid ProdutosForm produtosForm) {
		ProdutosForm produtos = gerenciarProdutos.salvar(produtosForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
	}

	@PutMapping("/{idDoProduto}")
	public ResponseEntity<ProdutosForm> editar(@RequestBody @Valid ProdutosForm produtosForm, @PathVariable UUID idDoProduto) {
		ProdutosForm produtos = gerenciarProdutos.editar(idDoProduto, produtosForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
	}

	@DeleteMapping("/{idDoProduto}")
	public ResponseEntity<Void> excluir(@PathVariable UUID idDoProduto) {
		gerenciarProdutos.excluir(idDoProduto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/quantidade/{idDoProduto}")
	public ResponseEntity<ProdutosForm> editarQuantidade(
		@Valid @RequestBody EditarQuantidadeDoProdutoForm editarQuantidadeDoProdutoForm,
		@PathVariable UUID idDoProduto) {
		ProdutosForm produtos = gerenciarProdutos.editarQuantidade(idDoProduto, editarQuantidadeDoProdutoForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
	}

	@PostMapping("/finalizar")
	public ResponseEntity<FinalizarComprasForm> finalizarCompras(@RequestBody @Valid FinalizarComprasForm finalizarComprasForm) throws ExecutionException, InterruptedException, TimeoutException {
		var compras = this.finalizarCompras.finalizar(finalizarComprasForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(compras);
	}

	@GetMapping("/consultar")
	public ResponseEntity<List<ProdutosForm>> consultarTudo() {
		return ResponseEntity.status(HttpStatus.OK).body(this.gerenciarProdutos.consultar());
	}
}
