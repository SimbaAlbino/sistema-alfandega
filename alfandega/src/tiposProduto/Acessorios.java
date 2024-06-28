package tiposProduto;

public class Acessorios extends Produto {
	private String nomeAcessorio;
	private float taxaUnica = 0.1f; //

	// Construtor da classe Acessorios que chama o construtor da classe base Produto
	public Acessorios(double precoUnico, int quantidade, String nomeAcessorio) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.nomeAcessorio = nomeAcessorio; // Inicializa o nome do acessório com o valor recebido
	}

	// Método para obter a taxa única do acessório utilitário
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única do acessório utilitário
	}
}