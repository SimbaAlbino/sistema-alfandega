package tiposProduto;

public class Eletrodomesticos extends Produto {
	private String tipoEletrodomestico; 
	private float taxaUnica = 0.1f; 

	// Construtor da classe Eletrodomesticos que chama o construtor da classe base
	// Produto
	public Eletrodomesticos(double precoUnico, int quantidade, String tipoEletrodomestico) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.tipoEletrodomestico = tipoEletrodomestico; // Inicializa o tipo de eletrodoméstico com o valor recebido
	}

	// Método para obter a taxa única do eletrodoméstico
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única do eletrodoméstico
	}
}
