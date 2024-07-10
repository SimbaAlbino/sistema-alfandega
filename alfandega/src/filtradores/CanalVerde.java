package filtradores;

import entidades.DadosProduto;

public class CanalVerde {
	private DadosProduto produto;

	public CanalVerde(DadosProduto produto) {
		this.produto = produto;
	}

	public DadosProduto getProduto() {
		return produto;
	}

	public void setProduto(DadosProduto produto) {
		this.produto = produto;
	}
}
