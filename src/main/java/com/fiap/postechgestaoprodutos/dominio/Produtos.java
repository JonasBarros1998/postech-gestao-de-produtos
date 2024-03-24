package com.fiap.postechgestaoprodutos.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Embeddable
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column
	private BigDecimal valor;

	@Column(length = 30, nullable = false)
	private String categoria;

	@Column(length = 200, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Integer quantidade;

	@OneToMany(mappedBy = "produtos")
	private List<InformacoesDaVenda> informacoesDaVenda;


	protected Produtos() {}

	public Produtos(String nome, BigDecimal valor, String categoria, String descricao, Integer quantidade) {
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
