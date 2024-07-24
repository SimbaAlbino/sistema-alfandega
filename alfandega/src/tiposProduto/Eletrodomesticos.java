package tiposProduto;

public class Eletrodomesticos extends Produto {
	
	private static final long serialVersionUID = 1L;
	private static final float TAXA_UNICA = 0.40f;

	public Eletrodomesticos(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}