package sistemaInterno;

import java.io.Serializable;
import entidades.DadosProduto;

// Classe abstrata que representa diferentes tipos de impostos
public abstract class Impostos implements Serializable {

	private static final long serialVersionUID = 1L; // Controle de versão da serialização
	protected ICMS icms; // Objeto ICMS
	protected IPI ipi; // Objeto IPI
	protected ImpostoFixo impostoFixo; // Objeto ImpostoFixo
	protected DadosProduto dadosProduto; // Produto relacionado ao imposto
	protected double precoUnico; // Preço único do produto

	// Construtor padrão
	public Impostos() {
	}

	// Construtor que inicializa o preço único do produto
	public Impostos(double precoUnico) {
		this.precoUnico = precoUnico;
	}

	// Método abstrato que cada subclasse deverá implementar para receber impostos
	public abstract void receberImpostos();

	// Método para calcular os impostos de um produto específico
	public static String[] calcularImpostos(DadosProduto produto, int code) {
		String[] historicoImposto;

		//criando iinstancias de impostos com o preco unico do produto
		ICMS icms = new ICMS(produto.getTipoProduto().getPrecoUnico());
		double valorICMS = icms.impostoProduto();

		IPI ipi = new IPI(produto.getTipoProduto().getPrecoUnico());
		double valorIPI = ipi.impostoProduto();

		ImpostoFixo impostoFixo = new ImpostoFixo(produto);
		double valorImpostoFixo = impostoFixo.impostoProduto();

		double valorTotal = valorICMS + valorIPI + valorImpostoFixo;

		// Se code == 0, retorna uma matriz de strings com os valores dos impostos
		if (code == 0) {
			historicoImposto = new String[] { produto.getCliente().getNome(), String.format("%.2f", valorICMS),
					String.format("%.2f", valorIPI), String.format("%.2f", valorImpostoFixo),
					String.format("%.2f", valorTotal) };
			return historicoImposto;
		}
		// Se code == 1, recebe os impostos e retorna null
		else if (code == 1) {
			icms.receberImpostos();
			ipi.receberImpostos();
			impostoFixo.receberImpostos();
			return null;
		}
		return null;
	}

	// Método para obter as taxas base dos impostos - position
	public static double[] getBaseImpostos() {
		double[] vetorImposto = new double[2];
		vetorImposto[0] = IPI.getTaxaIpi();
		vetorImposto[1] = ICMS.getTaxaIcms();
		return vetorImposto;
	}

	// Método para definir as taxas base dos impostos
	public static void setBaseImposto(double ipi, double icms) {
		IPI.setTaxaIpi(ipi);
		ICMS.setTaxaIcms(icms);
	}

	// Método getter para obter o produto relacionado aos impostos
	public DadosProduto getDadosProduto() {
		return dadosProduto;
	}

	// Método getter para obter o objeto ICMS
	public ICMS getIcms() {
		return icms;
	}

	// Método getter para obter o objeto ImpostoFixo
	public ImpostoFixo getImpostoFixo() {
		return impostoFixo;
	}

	// Método toString para representar a classe Impostos como uma string
	@Override
	public String toString() {
		return "Impostos [icms=" + icms + ", ipi=" + ipi + "]";
	}
}
