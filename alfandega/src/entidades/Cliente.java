package entidades;

import java.util.ArrayList;

public class Cliente implements Usuario {
	
	protected String nomeCliente;
	protected String email;
	protected String cpf;
	
	ArrayList<DadosProduto> produtoUser = new ArrayList<>();

	public Cliente(String nomeCliente, String email, String cpf) {
		this.nomeCliente = nomeCliente;
		this.email = email;
		this.cpf = cpf;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public boolean rastrearProdutos() {
		
		return ;
	}

	@Override
	public void listarProdutos() {
		for (DadosProduto produto : produtoUser) {
			System.out.println(produto);
		}
		
	}
}
