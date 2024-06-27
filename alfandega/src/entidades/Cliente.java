package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import utilidade.ModelagemFile;

public class Cliente extends Utilizador implements Usuario<Cliente>, Serializable {

	private static final long serialVersionUID = 1L;
	protected String nomeCliente;
	protected String email;
	protected String cpf;

	private String caminhoClientesFile = "";
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

	public String getCaminhoClientesFile() {
		return caminhoClientesFile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void listarProdutos() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = (ArrayList<DadosProduto>) ModelagemFile.desserializar(getCaminhoClientesFile()); // CASTING DO CURINGA
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (DadosProduto dadoProduto : listaProdutos) {
			// se não estiver no estoque, estará no despache
			if (dadoProduto.getClient().equals(this)) {
				System.out.println(dadoProduto);
			}
		}
	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		System.out.printf("O produto: %s está %s", produto.getProduto().getClass(), produto.getStatus().name());
		//usar enum
	}

	@Override
	public void avisosCanal() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}
	
	//usar o equals e hashCode de acordo com a necessidade no futuro. em listar produtos precisamos encontrar por Cliente
}
