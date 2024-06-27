package entidades;

import java.io.Serializable;
import java.util.Scanner;

public class Vendedor extends Utilizador implements Usuario, Serializable {
	private Cliente cliente;
	private String nomeVendedor;
	private String emailVendedor;
	
	public boolean verifPagamento(Cliente cliente) {
		
	}
	
	public void cadastrarProduto(Cliente cliente) {
		DadosProduto produto = new DadosProduto();
		
	}
	
	public void atualizarProduto() {
		
	}
	
	public void arquivoVendedores() {
		
	}

	@Override
	public boolean rastrearProdutos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void listarProdutos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagarUser() {
				
	}

	@Override
	public void adicionarUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avisosCanal() {
		// TODO Auto-generated method stub
		
	}

}
