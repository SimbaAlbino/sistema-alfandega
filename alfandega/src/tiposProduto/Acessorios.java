package tiposProduto;

public class Acessorios extends Produto {

	private static final long serialVersionUID = 1L; // Identificador para a serialização.
	private static final float TAXA_UNICA = 0.12f; // Taxa única de imposto para acessórios.

	// Construtor da classe Acessorios que chama o construtor da classe pai
	// (Produto)
	// para inicializar os atributos preço único e quantidade.
	public Acessorios(double precoUnico, int quantidade) {
		super(precoUnico, quantidade);
	}

	// Implementação do método abstrato getTaxaUnica da classe pai (Produto).
	// Retorna a taxa única de imposto para acessórios
	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}
