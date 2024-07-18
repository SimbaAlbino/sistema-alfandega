package sistemaInterno;

public class ICMS extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TIPO_IMPOSTO = "ICMS";
	private static double taxaIcms;
	private double precoUnico;
	// private static double taxaIcms = 0.20;

	public ICMS() {

	}

	public ICMS(double precoUnico) {
		super(precoUnico);
	}

	@Override
	public void receberImpostos() {
		Banco.adicionarImposto(getTIPO_IMPOSTO(), impostoProduto()); // adicionando para o valor total.
	}

	public double impostoProduto() {
		return getPrecoUnico() * getTaxaIcms();
	}

	public static String getTIPO_IMPOSTO() {
		return TIPO_IMPOSTO;
	}

	public static double getTaxaIcms() {
		return taxaIcms;
	}

	public static void setTaxaIcms(double taxaIcms) {
		ICMS.taxaIcms = taxaIcms;
	}
	
	public double getPrecoUnico() {
		return precoUnico;
	}
}
