package tiposProduto;

public class Mobilia extends Produto {
	private static final float TAXA_UNICA = 0.18f;

	public Mobilia(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}