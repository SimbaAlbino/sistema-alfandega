package sistemaInterno;

import entidades.DadosProduto;

public class ImpostoFixo extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TIPO_IMPOSTO = "Imposto Fixo";
	private static double taxaImpostoFixo;

	public ImpostoFixo() {

	}

	public ImpostoFixo(DadosProduto produto) {
		super(produto); //
	}

	@Override
	public void receberImpostos() {
		adicionarImposto(getTipoImposto(), impostoProduto(getDadosProduto())); //
	}

	public static double impostoProduto(DadosProduto produto) {

		return produto.getTipoProduto().getPrecoUnico() * getTaxaImpostoFixo();

	}

	public static double getTaxaImpostoFixo() {
		return taxaImpostoFixo;
	}

	public static String getTipoImposto() {
		return TIPO_IMPOSTO;
	}

	public static void setTaxaImpostoFixo(double taxaImpostoFixo) {
		ImpostoFixo.taxaImpostoFixo = taxaImpostoFixo;
	}

}