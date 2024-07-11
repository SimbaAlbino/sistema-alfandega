package tiposProduto;

import entidades.Cliente;

public abstract class Produto {
	private double precoUnico;
	private int quantidade;
	private Cliente cliente; // cliente associado

	public Produto(double precoUnico, int quantidade, Cliente cliente) {
		this.precoUnico = precoUnico;
		this.quantidade = quantidade;
		this.cliente = cliente;
	}

	public double getPrecoUnico() {
		return precoUnico;
	}

	public void setPrecoUnico(double precoUnico) {
		this.precoUnico = precoUnico;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public abstract double getTaxaUnica();

	public double getTotal() {
		double taxa = getTaxaUnica();
		return (this.precoUnico * this.quantidade) * (1 + taxa);
	}
}
