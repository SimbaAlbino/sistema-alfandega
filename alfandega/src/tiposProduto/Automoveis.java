package tiposProduto;

public class Automoveis extends Produto {

	private static final long serialVersionUID = 1L;
	private static final float TAXA_UNICA = 0.059f; // Taxa única de imposto para automóveis.

	public Automoveis(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}