package entidades;

import java.io.Serializable;
import java.util.ArrayList;

import utilidade.ModelagemFile;

public class Cliente extends Utilizador implements Usuario<Cliente>, Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private String email;
	private String senha;
	private String cpf;
	

	private String caminhoClientesFile = "";
	
	
	//construtor de cadastro
	public Cliente(String nomeCliente, String email, String senha, String cpf) {

	public Cliente(String nomeCliente, String email, String cpf) {
		this.nomeCliente = nomeCliente;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	//gerando construtor de uso para o Vendedor
	public Cliente(String nomeCliente, String cpf) {
		this.nomeCliente = nomeCliente;
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

	public String getCaminhoClientesFile() {
		return caminhoClientesFile;
	}
	
	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void listarProdutos() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = (ArrayList<DadosProduto>) ModelagemFile.desserializar(getCaminhoClientesFile()); // CASTING
																												// DO
																												// CURINGA
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (DadosProduto dadoProduto : listaProdutos) {
			// se não estiver no estoque, estará no despache
			if (dadoProduto.getCliente().equals(this)) {
				System.out.println(dadoProduto);
			}
		}
	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		System.out.printf("O produto: %s está %s", produto.getTipoItem(), produto.getStatus().name());
		//Se o produto tiver no estoque e com a coloção amarela, verificar com o funcionario, mensagem;
		System.out.printf("O produto: %s está %s", produto.getProduto().getClass(), produto.getStatus().name());
		// usar enum
	}

	@Override
	public void avisosCanal() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}
	// ao validar o cpf do cliente, apenas considerar os números, se vier uma letra, faz um while.
	//usar o equals e hashCode de acordo com a necessidade no futuro. em listar produtos precisamos encontrar por Cliente

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
