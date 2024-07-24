package tiposProduto;

import java.io.Serializable;

public abstract class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private double precoUnico;
	private int quantidade;

	public Produto(double precoUnico, int quantidade) {
		this.precoUnico = precoUnico;
		this.quantidade = quantidade;
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

	public abstract double getTaxaUnica();

	public double getTotal() {
		double taxa = getTaxaUnica();
		return (this.precoUnico * this.quantidade) * (1 + taxa);
	}
}
