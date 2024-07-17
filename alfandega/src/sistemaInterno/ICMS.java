package sistemaInterno;

import entidades.DadosProduto;

public class ICMS extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TIPO_IMPOSTO = "ICMS";
	private static double taxaIcms;
	// private static double taxaIcms = 0.20;

	public ICMS() {

	}

	public ICMS(DadosProduto produto) {
		super(produto);
	}

	@Override
	public void receberImpostos() {
		adicionarImposto(getTIPO_IMPOSTO(), impostoProduto(getDadosProduto())); // adicionando para o valor total.
	}

	public static double impostoProduto(DadosProduto produto) {
		return produto.getTipoProduto().getPrecoUnico() * getTaxaIcms();

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

}
