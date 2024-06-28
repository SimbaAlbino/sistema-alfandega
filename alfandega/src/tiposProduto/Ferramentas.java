package tiposProduto;

public class Ferramentas extends Produto {
	private String peca; 
	private float taxaUnica = 0.1f; 

	// Construtor da classe Ferramenta que chama o construtor da classe base Produto
	public Ferramentas (double precoUnico, int quantidade, String peca) {
		super(precoUnico, quantidade); // Chama o construtor da classe base Produto
		this.peca = peca; // Inicializa a descrição da ferramenta com o valor recebido
	}

	// Método para obter a taxa única da ferramenta
	@Override
	public float getTaxaUnica() {
		return taxaUnica; // Retorna a taxa única da ferramenta
	}
}