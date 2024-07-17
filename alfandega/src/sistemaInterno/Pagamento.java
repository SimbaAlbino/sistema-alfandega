package sistemaInterno;

import entidades.DadosProduto;

public interface Pagamento {
	
	DadosProduto getDadosProduto();

	boolean dividaPendente();

	boolean liberarDivida();
	
	default double calcularDespesaPedidoTot(DadosProduto produto) {
		return ICMS.impostoProduto(getDadosProduto()) + IPI.impostoProduto(getDadosProduto()) + ImpostoFixo.impostoProduto(getDadosProduto());
	}

}
