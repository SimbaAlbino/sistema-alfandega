package tiposProduto;

public class Mobilia extends Produto {
	private String nomeMobilia; // Nome da mobília
	private float taxaUnica = 0.1f; 

	// Construtor da classe Mobilia que chama o construtor da classe base Produto
	public Mobilia(double precoUnico, int quantidade, String nomeMobilia) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.nomeMobilia = nomeMobilia; // Inicializa o nome da mobília com o valor recebido
	}

	// Método para obter a taxa única da mobília
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única da mobília
	}
}
