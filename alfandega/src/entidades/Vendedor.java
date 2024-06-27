package entidades;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Vendedor extends Utilizador implements Usuario<Vendedor>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	static Scanner sc = new Scanner(System.in);

	private String nomeVendedor;
	private String emailVendedor;
	private String senha;
	private String caminhoVendedoresFile = "";

	public Vendedor(String nomeVendedor, String emailVendedor, String senha) {
		this.nomeVendedor = nomeVendedor;
		this.emailVendedor = emailVendedor;
		this.senha = senha;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public String getCaminhoVendedoresFile() {
		return caminhoVendedoresFile;
	}

	public void cadastrarProduto() {
		System.out.println("Digite o nome do Cliente: ");
		System.out.println("Digite o ");
		DadosProduto produto = new DadosProduto(new Cliente());
		
	}

	public void arquivoVendedores() {
		//ArrayList<Vendedores> desserializar = ModelagemFile.desserializar();
		
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
