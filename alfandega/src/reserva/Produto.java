package reserva;

public class Produto {

	private String nome;
	private int quantidade;
	private float taxaUnica;

	public Produto(String nome, int quantidade, float taxaUnica) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.taxaUnica = taxaUnica;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getTaxaUnica() {
		return taxaUnica;
	}

	public void setTaxaUnica(float taxaUnica) {
		this.taxaUnica = taxaUnica;
	}

	@Override
	public void listarProdutos() {
		return
		

	}
}
