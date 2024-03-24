package com.fiap.postechgestaoprodutos.dominio;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vendas")
public class Vendas {

	protected Vendas() {}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "pagamento_id", nullable = false)
	private UUID pagamento;

	@Column(name = "usuario_id", nullable = false)
	private UUID usuario;

	@OneToMany(mappedBy = "vendas")
	private List<InformacoesDaVenda> informacoesDaVenda;

	public Vendas(UUID pagamento, UUID usuario) {
		this.usuario = usuario;
		this.pagamento = pagamento;
	}

	public UUID getId() {
		return id;
	}

	public UUID getPagamento() {
		return pagamento;
	}

	public UUID getUsuario() {
		return usuario;
	}

	public void setInformacoesDaVenda(List<InformacoesDaVenda> informacoesDaVenda) {
		this.informacoesDaVenda = informacoesDaVenda;
	}
}
