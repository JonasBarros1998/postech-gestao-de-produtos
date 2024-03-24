package com.fiap.postechgestaoprodutos.aplicacao;

import com.fiap.postechgestaoprodutos.dominio.Produtos;
import com.fiap.postechgestaoprodutos.dominio.VerificarQuantidadeDoProduto;
import com.fiap.postechgestaoprodutos.infra.bancodedados.ProdutosRepository;
import com.fiap.postechgestaoprodutos.view.exceptions.ProdutoNaoExisteException;
import com.fiap.postechgestaoprodutos.view.exceptions.QuantidadeInvalidaException;
import com.fiap.postechgestaoprodutos.view.form.EditarQuantidadeDoProdutoForm;
import com.fiap.postechgestaoprodutos.view.form.ProdutosForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GerenciarProdutos {

	ProdutosRepository produtosRepository;

	@Autowired
	GerenciarProdutos(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}

	public ProdutosForm salvar(ProdutosForm produtosForm) {

		var produtos = new Produtos(
			produtosForm.nome(),
			produtosForm.valor(),
			produtosForm.categoria(),
			produtosForm.descricao(),
			produtosForm.quantidade());

		this.produtosRepository.save(produtos);
		return produtosForm;
	}

	public void excluir(UUID idDoProduto) {
		this.produtosRepository.deleteById(idDoProduto);
	}

	public ProdutosForm editar(UUID idDoProduto, ProdutosForm produtosForm) {
		var produto = this.produtosRepository.findById(idDoProduto)
			.orElseThrow(() -> new ProdutoNaoExisteException("Produto nao existe"));

		produto.setNome(produtosForm.nome());
		produto.setCategoria(produtosForm.categoria());
		produto.setDescricao(produtosForm.descricao());
		produto.setValor(produtosForm.valor());
		produto.setQuantidade(produtosForm.quantidade());

		this.produtosRepository.save(produto);

		return produtosForm;
	}

	public ProdutosForm editarQuantidade(UUID idDoProduto, EditarQuantidadeDoProdutoForm editarQuantidadeDoProdutoForm) {
		var produto = this.produtosRepository.findById(idDoProduto)
			.orElseThrow(() -> new ProdutoNaoExisteException("Produto nao existe"));

		/*Boolean verificarQuantidadeDoProduto =
			VerificarQuantidadeDoProduto
				.verificar(produto.getQuantidade(), editarQuantidadeDoProdutoForm.quantidade());

		if (Boolean.FALSE.equals(verificarQuantidadeDoProduto)) {
			throw new QuantidadeInvalidaException("Estoque insuficiente para o produto " + produto.getNome());
		}*/

		produto.setQuantidade(editarQuantidadeDoProdutoForm.quantidade());

		this.produtosRepository.save(produto);

		return new ProdutosForm(
			produto.getId(),
			produto.getNome(),
			produto.getValor(),
			produto.getDescricao(),
			produto.getCategoria(),
			produto.getQuantidade());

	}

	public List<ProdutosForm> consultar() {
		return this.produtosRepository.findAll().stream().map(produtoItem ->
			new ProdutosForm(
				produtoItem.getId(),
				produtoItem.getNome(),
				produtoItem.getValor(),
				produtoItem.getDescricao(),
				produtoItem.getCategoria(),
				produtoItem.getQuantidade())
		).toList();
	}
}
