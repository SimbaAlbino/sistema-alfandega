package tiposProduto;

public class Ferramentas extends Produto {
	
	private static final long serialVersionUID = 1L;
	private static final float TAXA_UNICA = 0.060f; // Taxa única de imposto para ferramentas.

	public Ferramentas(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}