package tiposProduto;

public abstract class Produto {

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

    // metodo abstrato da taxa unica
	public abstract double getTaxaUnica();

	public double getTotal() {    // metodo para calcular o total dos produtos
		double taxa = getTaxaUnica();
		return (this.precoUnico * this.quantidade) * (1 + taxa);
	}
}