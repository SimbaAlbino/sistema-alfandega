package entidades;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cliente extends Utilizador implements Usuario, Serializable {
	
	protected String nomeCliente;
	protected String email;
	protected String cpf;

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
	public void listarProdutos() {
		for (DadosProduto produto : produtoUser) {
			System.out.println(produto);
		}
		
	}

	
	public void adicionarProduto(DadosProduto produto) {
		
	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		System.out.printf("O produto: %s está %s", produto.getProduto().getClass(), );
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
