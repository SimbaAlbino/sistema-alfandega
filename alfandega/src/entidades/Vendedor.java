package entidades;

import java.io.Serializable;
import java.util.Scanner;

public class Vendedor extends Utilizador implements Usuario<Vendedor>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private String nomeVendedor;
	private String emailVendedor;
	private String senha;
	private String caminhoVendedoresFile = "";

	public void cadastrarProduto(Cliente cliente) {
		DadosProduto produto = new DadosProduto();

	}

	public void atualizarProduto() {

	}

	public void arquivoVendedores() {

	}

	public void verifPagamento(Cliente cliente) {

	}

	@Override
	public void listarProdutos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		// TODO Auto-generated method stub
		
	}
	
	

}
