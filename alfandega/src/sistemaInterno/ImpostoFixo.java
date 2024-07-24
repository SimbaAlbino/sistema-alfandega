package sistemaInterno;

import entidades.DadosProduto;

public class ImpostoFixo extends Impostos {
	
	public static final String TIPO_IMPOSTO = "Imposto Fixo";
	private double taxaImpostoFixo;
	private DadosProduto produto;

	public ImpostoFixo(DadosProduto produto) {
		this.produto = produto;
		this.taxaImpostoFixo = produto.getTipoProduto().getTaxaUnica();
	}

	@Override
	public void receberImpostos(int qnt) {
		Banco.adicionarImposto(getTipoImposto(), impostoProduto() * qnt); //
	}

	public double impostoProduto() {
		return getProduto().getTipoProduto().getPrecoUnico() * getTaxaImpostoFixo();
	}

	public double getTaxaImpostoFixo() {
		return taxaImpostoFixo;
	}

	public static String getTipoImposto() {
		return TIPO_IMPOSTO;
	}

	public void setTaxaImpostoFixo(DadosProduto produto) {
		this.taxaImpostoFixo = produto.getTipoProduto().getTaxaUnica();
	}

	public DadosProduto getProduto() {
		return produto;
	}

	// depende do produto

}