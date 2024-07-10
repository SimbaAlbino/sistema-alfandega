package filtradores;

import entidades.DadosProduto;

public class CanalAmarelo {
	private DadosProduto produto;

	public CanalAmarelo(DadosProduto produto) {
		this.produto = produto;
	}

	public DadosProduto getProduto() {
		return produto;
	}

	public void setProduto(DadosProduto produto) {
		this.produto = produto;
	}
	
	public void mudarStatusAgPagamento() {
		
	}
}
