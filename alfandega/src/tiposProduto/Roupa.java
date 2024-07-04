package tiposProduto;

public class Roupa extends Produto {
	private static final float TAXA_UNICA = 0.20f; 

	public Roupa(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}