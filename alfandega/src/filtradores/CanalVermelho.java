package filtradores;

import entidades.DadosProduto;

public class CanalVermelho {
	
	private DadosProduto produto;

	public CanalVermelho(DadosProduto produto) {
		this.produto = produto;
	}

	public DadosProduto getProduto() {
		return produto;
	}

	public void setProduto(DadosProduto produto) {
		this.produto = produto;
	}
	
	public void devolverDemanda() {
		
	}
}
