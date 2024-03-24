package com.fiap.postechgestaoprodutos.aplicacao;

import com.fiap.postechgestaoprodutos.dominio.*;
import com.fiap.postechgestaoprodutos.infra.bancodedados.InformacoesDeVendasRepository;
import com.fiap.postechgestaoprodutos.infra.bancodedados.ProdutosRepository;
import com.fiap.postechgestaoprodutos.infra.bancodedados.VendasRepository;
import com.fiap.postechgestaoprodutos.infra.seguranca.Token;
import com.fiap.postechgestaoprodutos.view.exceptions.QuantidadeInvalidaException;
import com.fiap.postechgestaoprodutos.view.form.FinalizarComprasForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class FinalizarCompras {

	EnviarProdutosParaOCarrinhoRequest enviarProdutosParaOCarrinho;

	InformacoesDeVendasRepository informacoesDeVendasRepository;

	ProdutosRepository produtosRepository;

	VendasRepository vendasRepository;

	ExecutorService executor = Executors.newSingleThreadExecutor();

	@Autowired
	FinalizarCompras(
		EnviarProdutosParaOCarrinhoRequest enviarProdutosParaOCarrinho,
		InformacoesDeVendasRepository informacoesDeVendasRepository,
		ProdutosRepository produtosRepository,
		VendasRepository vendasRepository) {
		this.enviarProdutosParaOCarrinho = enviarProdutosParaOCarrinho;
		this.informacoesDeVendasRepository = informacoesDeVendasRepository;
		this.produtosRepository = produtosRepository;
		this.vendasRepository = vendasRepository;
	}

	@Transactional
	public FinalizarComprasForm finalizar(FinalizarComprasForm finalizarComprasForm) throws InterruptedException, ExecutionException, TimeoutException {
		final List<InformacoesDaVenda> informacoesDaVenda = new ArrayList<>();
		final var vendas = new Vendas(finalizarComprasForm.pagamento().id(), finalizarComprasForm.usuario().id());

		Future<?> resultadoDaExecucao = executor.submit(() -> {

			finalizarComprasForm.produtos().forEach(produtoFormItem -> {

				var produto = this.produtosRepository.findById(produtoFormItem.id()).orElseThrow(null);

				if(Boolean.TRUE.equals(VerificarQuantidadeDoProduto.verificar(produto.getQuantidade(), produtoFormItem.quantidade()))) {

					var informacoesDaVendaJuncao = new InformacoesDaVendaJuncao(vendas.getId(), produto.getId());
					informacoesDaVenda.add(new InformacoesDaVenda(produto, vendas, produtoFormItem.quantidade(), informacoesDaVendaJuncao));

				} else {

					throw new QuantidadeInvalidaException("Estoque insuficiente para o produto %s".formatted(produto.getId()));

				}


			});

			var resultadoDeVenda = this.vendasRepository.save(vendas);

			this.informacoesDeVendasRepository.saveAll(informacoesDaVenda);

			this.enviarAoCarrinhoDeCompras(informacoesDaVenda, resultadoDeVenda);

			this.atualizarQuantidadeNoEstoque(informacoesDaVenda, finalizarComprasForm);
		});

		resultadoDaExecucao.get(1000, TimeUnit.SECONDS);

		return finalizarComprasForm;
	}

	private void enviarAoCarrinhoDeCompras(List<InformacoesDaVenda> informacoesDaVendas, Vendas vendas) {
		var carrinho = informacoesDaVendas.stream().map(venda ->
			 new Carrinho(venda.getProdutos().getId(), venda.getProdutos().getValor(), venda.getQuantidade())
		).toList();

		var finalizar = new Finalizar(vendas.getUsuario(), vendas.getPagamento(), vendas.getId(), carrinho);

		this.enviarProdutosParaOCarrinho.finalizarCompra(finalizar, "Bearer %s".formatted(Token.acessarToken()));
	}

	private void atualizarQuantidadeNoEstoque(List<InformacoesDaVenda> vendas, FinalizarComprasForm finalizarComprasForm) {

		finalizarComprasForm.produtos().forEach(produtoFormItem -> {

			vendas.forEach(venda -> {

				if(venda.getProdutos().getId().toString().equals(produtoFormItem.id().toString())) {

					Produtos produtos = this.produtosRepository.findById(venda.getProdutos().getId()).orElseThrow(null);

					produtos.setQuantidade(VerificarQuantidadeDoProduto.calcularNovaQuantidade(produtos.getQuantidade(), produtoFormItem.quantidade()));

					this.produtosRepository.save(produtos);
				}

			});
		});

	}
}
