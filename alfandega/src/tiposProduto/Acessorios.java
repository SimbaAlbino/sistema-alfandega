package tiposProduto;

import entidades.Cliente;

public class Acessorios extends Produto {
	private static final float TAXA_UNICA = 0.12f;

	public Acessorios(double precoUnico, int quantidade, Cliente cliente) {
		super(precoUnico, quantidade, cliente);
	}

	@Override
	public double getTaxaUnica() {
		return TAXA_UNICA;
	}
}
