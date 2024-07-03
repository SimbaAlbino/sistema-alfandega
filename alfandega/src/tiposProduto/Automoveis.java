package tiposProduto;

public class Automoveis extends Produto {
	private String tipoVeiculo;
	private String marca;
	private float taxaUnica = 0.1f; // Taxa única padrão para automóveis

	// Construtor da classe Automovel que chama o construtor da classe base Produto
	public Automoveis(double precoUnico, int quantidade, String tipoVeiculo, String marca) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.tipoVeiculo = tipoVeiculo; // Inicializa o tipo de veículo com o valor recebido
		this.marca = marca; // Inicializa a marca do automóvel com o valor recebido
	}

	// Método para obter a taxa única do automóvel
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única do automóvel
	}
}