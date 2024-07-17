package sistemaInterno;

import entidades.DadosProduto;

public class IPI extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TIPO_IMPOSTO = "IPI";
	private static double taxaIpi;
	// private static double taxaIpi = 0.11;

	public IPI() { // CONST. VAZIO CTRL + SPACE

	}

	public IPI(DadosProduto produto) {
		super(produto);
	}

	@Override
	public void receberImpostos() {

		adicionarImposto(getTIPO_IMPOSTO(), impostoProduto(getDadosProduto())); // !!!!! USO DO GET

	} 

	public static double impostoProduto(DadosProduto produto) {

		return produto.getTipoProduto().getPrecoUnico() * getTaxaIpi();

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

}
