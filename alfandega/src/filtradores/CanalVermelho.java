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
	//o cliente terá um tempo para retornar o produto. Ou seja, ele vai ser rejeitado, se no próximo atualizarEstoque, com o tempo passado, o funcionario não atualizar, ele vai ser despachado.
}
