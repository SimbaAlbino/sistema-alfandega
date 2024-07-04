package tiposProduto;

public class Automoveis extends Produto {
	private static final float TAXA_UNICA = 0.90f;

	public Automoveis(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}