package entidades;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import tiposProduto.Roupa;

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
		System.out.print("Digite o nome do Cliente: ");
		String nomeCliente = sc.nextLine();
		System.out.print("Digite o CPF");
		String cpfCliente = sc.next();
		System.out.println();
		System.out.print("Próxima etapa (Descrição do Produto): ");
		System.out.print("Tipo de produto.");
		System.out.print("Qual o preço da unidade? ");
		System.out.print("Qual a quantidade? ");
		System.out.println();
		System.out.print("Você adicionou documentos ( S | N )? ");
		System.out.println();
		System.out.println("Endereço do Usuário: ");
		System.out.print("Digite o cep: ");
		System.out.println("Digite o número da residência: ");
		
		
		
		DadosProduto produto = new DadosProduto(new Cliente(nomeCliente, cpfCliente), this, new Roupa(), );
		
	}

	public ArrayList<Vendedor> arquivoVendedores() {
		//ArrayList<Vendedores> vendedoresFile = ModelagemFile.desserializar(getCaminhoVendedoresFile());
		/* 
		 * for (vendedoresFile)
		 * 
		 * */
		
	}

	public void verifPagamento(Cliente cliente) {

	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void cadastrarUser() {
		// TODO Auto-generated method stub
		
	}

}
