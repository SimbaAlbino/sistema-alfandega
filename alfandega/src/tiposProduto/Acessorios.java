package tiposProduto;

public class Acessorios extends Produto {
	private static final float TAXA_UNICA = 0.12f;

	public Acessorios(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}
