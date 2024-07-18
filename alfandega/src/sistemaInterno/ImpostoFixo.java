package sistemaInterno;

import entidades.DadosProduto;

public class ImpostoFixo extends Impostos {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TIPO_IMPOSTO = "Imposto Fixo";
	private static double taxaImpostoFixo;
	private DadosProduto produto;

	public ImpostoFixo() {

	}

	public ImpostoFixo(DadosProduto produto) {
		this.produto = produto;
	}

	@Override
	public void receberImpostos() {
		Banco.adicionarImposto(getTipoImposto(), impostoProduto()); //
	}

	public double impostoProduto() {
		return getProduto().getTipoProduto().getPrecoUnico() * getTaxaImpostoFixo();
	}

	public static double getTaxaImpostoFixo() {
		return taxaImpostoFixo;
	}

	public static String getTipoImposto() {
		return TIPO_IMPOSTO;
	}

	public static void setTaxaImpostoFixo(DadosProduto produto) {
		ImpostoFixo.taxaImpostoFixo = produto.getTipoProduto().getTaxaUnica();
	}

	public DadosProduto getProduto() {
		return produto;
	}

	// depende do produto

}