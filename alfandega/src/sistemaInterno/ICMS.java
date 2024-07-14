package sistemaInterno;

public class ICMS extends Impostos {
	private static final String TIPO_IMPOSTO = "ICMS";
	private static final double TAXA_ICMS = 0.20;

	public ICMS(double valorImposto) {
		super(TIPO_IMPOSTO, valorImposto);
	}

	@Override
	public double calcularImpostoTotal() {
		double total = getValorImposto() * TAXA_ICMS;
		adicionarValorRecolhido(total);
		return total;
	}
}