package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import utilidade.ModelagemFile;

public class Cliente extends Utilizador implements Usuario<Cliente>, Serializable {

	// alterar os construtores usando this

	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private String email;
	private String senha;
	private String cpf;

	private static String caminhoClientesFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileClientes.txt";

	// construtor de cadastro
	public Cliente(String nomeCliente, String email, String senha, String cpf) {
		this.nomeCliente = nomeCliente;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	//para operações de login
	public Cliente(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	// gerando construtor de uso para o Vendedor e busca
	public Cliente(String cpf) {
		this.cpf = cpf;
	}

	public Cliente() {
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public static String getCaminhoClientesFile() {
		return caminhoClientesFile;
	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		for (DadosProduto dadoProduto : produtosFiltrados) {
			// se não estiver no estoque, estará no despache
			if (dadoProduto.getCliente().equals(this)) {
				System.out.println(dadoProduto);
			}
		}
	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		System.out.printf("O produto: %s está %s", produto.getTipoProduto(), produto.getStatus());
		// Se o produto tiver no estoque e com a coloção amarela, verificar com o
		// funcionario, mensagem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Cliente> cadastroAttUser(String caminho){
		ArrayList<Cliente> listaPessoas = null;
		try {
			listaPessoas = (ArrayList<Cliente>) ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listaPessoas.add(this);
		return listaPessoas;
	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

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

	
	// ao validar o cpf do cliente, apenas considerar os números, se vier uma letra
	// faz um while.
	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}


