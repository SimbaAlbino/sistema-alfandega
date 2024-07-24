package tiposProduto;

import java.io.Serializable;

public abstract class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L; // Identificador para a serialização.
	private double precoUnico; // Preço unitário do produto.
	private int quantidade; // Quantidade do produto.

	// Construtor da classe Produto para inicializar os atributos precoUnico e quantidade.
	public Produto(double precoUnico, int quantidade) { 
		this.precoUnico = precoUnico;
		this.quantidade = quantidade;
	}

	// Getter para o preço unitário do produto.
	public double getPrecoUnico() {
		return precoUnico;
	}
	
	// Setter para o preço unitário do produto.
	public void setPrecoUnico(double precoUnico) {
		this.precoUnico = precoUnico;
	}
	// Getter para a quantidade do produto.
	public int getQuantidade() {
		return quantidade;
	}
    //Setter par  quantiddade do produto.
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	// Método abstrato que deve ser implementado pelas subclasses para retornar a taxa de imposto específica.
	public abstract double getTaxaUnica();

	// Método para calcular o custo total do produto, incluindo a taxa de imposto.
	public double getTotal() {
		double taxa = getTaxaUnica();
		return (this.precoUnico * this.quantidade) * (1 + taxa);
	}
}
