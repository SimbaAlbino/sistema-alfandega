package filtradores;

import entidades.DadosProduto;

public class CanalCinza {
	
	private DadosProduto produto;

	public CanalCinza(DadosProduto produto) {
		this.produto = produto;
	}

	public DadosProduto getProduto() {
		return produto;
	}

	public void setProduto(DadosProduto produto) {
		this.produto = produto;
	}
	
	public void devolverTipo() {
		
	}
}
