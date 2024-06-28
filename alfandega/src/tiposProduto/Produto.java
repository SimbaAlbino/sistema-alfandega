package tiposProduto;

public class Produto {

	private double precoUnico;
	private int quantidade;

	public Produto(double precoUnico, int quantidade) {
		super();
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

	public double getTotal() {
		return precoUnico * quantidade;
	}

	// Método abstrato para obter a taxa única
	public abstract float getTaxaUnica();
}
