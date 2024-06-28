package tiposProduto;

public class Informatica extends Produto {
	private String descricaoPeca; 
	private float taxaUnica = 0.1f; 

	// Construtor da classe Informatica que chama o construtor da classe base
	// Produto
	public Informatica(double precoUnico, int quantidade, String descricaoPeca) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.descricaoPeca = descricaoPeca; // Inicializa a descrição da peça de informática com o valor recebido
	}

	// Método para obter a taxa única da peça de informática
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única da peça de informática
	}
}