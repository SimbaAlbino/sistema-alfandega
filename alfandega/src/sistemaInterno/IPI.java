package sistemaInterno;

public class IPI extends Impostos {
	private static final String TIPO_IMPOSTO = "IPI";
	private static final double TAXA_IPI = 0.11;

	public IPI(double valorImposto) {
		super(TIPO_IMPOSTO, valorImposto);
	}

	@Override
	public double calcularImpostoTotal() {
		return getValorImposto() * TAXA_IPI;
	}
}
