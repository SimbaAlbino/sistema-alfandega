package sistemaInterno;

public class IPI extends Impostos {
	/**
	 * Serial version UID para controle de versão da serialização
	 */
	private static final long serialVersionUID = 1L;

	// Tipo do imposto, específico para IPI
	public static String TIPO_IMPOSTO = "IPI";

	// Taxa do imposto IPI
	private static double taxaIpi;

	// Preço unitário do produto ao qual o imposto será aplicado
	private double precoUnico;

	// Construtor vazio
	public IPI() {
		
	}

	// Construtor que aceita o preço único do produto
	public IPI(double precoUnico) {
		super(precoUnico); // Chama o construtor da classe pai (Impostos)
	}

	// Método que recebe e adiciona o imposto ao banco
	@Override
	public void receberImpostos() {
		// Adiciona o valor do imposto calculado ao mapa de impostos no banco
		Banco.adicionarImposto(getTIPO_IMPOSTO(), impostoProduto());
	}

	// Método que calcula o valor do imposto do produto
	public double impostoProduto() {
		// Calcula o imposto multiplicando o preço único pela taxa de IPI
		return getPrecoUnico() * getTaxaIpi();
	}

	// Método getter para obter o tipo de imposto
	public static String getTIPO_IMPOSTO() {
		return TIPO_IMPOSTO;
	}

	// Método getter para obter a taxa de IPI
	public static double getTaxaIpi() {
		return taxaIpi;
	}

	// Método setter para definir a taxa de IPI
	public static void setTaxaIpi(double taxaIpi) {
		IPI.taxaIpi = taxaIpi;
	}

	// Método getter para obter o preço  único
	public double getPrecoUnico() {
		return precoUnico;
	}
}
