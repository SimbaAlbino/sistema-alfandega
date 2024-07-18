package sistemaInterno;

public class IPI extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TIPO_IMPOSTO = "IPI";
	private static double taxaIpi;
	private double precoUnico;
	// private static double taxaIpi = 0.11;

	public IPI() { // CONST. VAZIO CTRL + SPACE

	}

	public IPI(double precoUnico) {
		super(precoUnico);
	}

	@Override
	public void receberImpostos() {
		Banco.adicionarImposto(getTIPO_IMPOSTO(), impostoProduto()); // !!!!! USO DO GET
	} 

	public double impostoProduto() {
		return getPrecoUnico() * getTaxaIpi();
	}

	public static String getTIPO_IMPOSTO() {
		return TIPO_IMPOSTO;
	}

	public static double getTaxaIpi() {
		return taxaIpi;
	}

	public static void setTaxaIpi(double taxaIpi) {
		IPI.taxaIpi = taxaIpi;
	}
	
	public double getPrecoUnico() {
		return precoUnico;
	}

}
