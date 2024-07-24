package tiposProduto;

public class Informatica extends Produto {
	
	private static final long serialVersionUID = 1L;
	private static final float TAXA_UNICA = 0.15f;

	public Informatica(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}