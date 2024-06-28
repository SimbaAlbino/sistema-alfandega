package tiposProduto;

public class Roupa extends Produto {
	private char tamanho; 
	private String peca; 
	private float taxaUnica = 0.1f; // Taxa única padrão para peças de roupa

	// Construtor da classe Roupa que chama o construtor da classe base Produto
	public Roupa(double precoUnico, int quantidade, char tamanho, String peca) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.tamanho = tamanho; // Inicializa o tamanho da roupa com o valor recebido
		this.peca = peca; // Inicializa a descrição da peça de roupa com o valor recebido
	}

	// Método para obter a taxa única da peça de roupa
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única da peça de roupa
	}
}