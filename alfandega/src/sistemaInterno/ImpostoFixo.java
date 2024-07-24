package sistemaInterno;

import entidades.DadosProduto;

// Classe ImpostoFixo que estende a classe abstrata Impostos
public class ImpostoFixo extends Impostos {
	/**
	 * Serial version UID para controle de versão da serialização
	 */
	private static final long serialVersionUID = 1L;

	// Tipo de imposto específico para Imposto Fixo
	public static final String TIPO_IMPOSTO = "Imposto Fixo";

	// Taxa fixa do imposto
	private static double taxaImpostoFixo;

	// Produto relacionado ao imposto
	private DadosProduto produto;

	// Construtor padrão
	public ImpostoFixo() {
	}

	// Construtor que inicializa o produto relacionado ao imposto
	public ImpostoFixo(DadosProduto produto) {
		this.produto = produto;
	}

	// Implementação do método abstrato da classe pai para receber impostos
	@Override
	public void receberImpostos() {
		// Adiciona o valor do imposto calculado ao mapa de impostos no banco
		Banco.adicionarImposto(getTipoImposto(), impostoProduto());
	}

	// Método para calcular o valor do imposto sobre o produto
	public double impostoProduto() {
		return getProduto().getTipoProduto().getPrecoUnico() * getTaxaImpostoFixo();
		// calcula o valor do imposto sobre um produto específico, utilizando a taxa de imposto fixa definida para o tipo de produto
	}

	// Método getter para obter a taxa de imposto fixo
	public static double getTaxaImpostoFixo() {
		return taxaImpostoFixo;
	}

	// Método getter para obter o tipo de imposto
	public static String getTipoImposto() {
		return TIPO_IMPOSTO;
	}

	// Método setter para definir a taxa de imposto fixo baseada no produto
	public static void setTaxaImpostoFixo(DadosProduto produto) {
		ImpostoFixo.taxaImpostoFixo = produto.getTipoProduto().getTaxaUnica();
	}

	// Método getter para obter o produto relacionado ao imposto
	public DadosProduto getProduto() {
		return produto;
	}
}
