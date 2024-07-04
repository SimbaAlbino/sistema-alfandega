package entidades;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import tiposProduto.Acessorios;
import tiposProduto.Produto;

public class Fornecedor extends Utilizador implements Usuario<Fornecedor>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	static Scanner sc = new Scanner(System.in);

	private String nomeFornecedor;
	private String emailFornecedor;
	private String senha;
	private String caminhoFornecedoresFile = "";

	public Fornecedor(String nomeFornecedor, String emailFornecedor, String senha) {
		this.nomeFornecedor = nomeFornecedor;
		this.emailFornecedor = emailFornecedor;
		this.senha = senha;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public String getCaminhoFornecedoresFile() {
		return caminhoFornecedoresFile;
	}

	public void cadastrarProduto() {
		Produto categoriaProduto;
		
		System.out.print("Digite o nome do Cliente: ");
		String nomeCliente = sc.nextLine();
		System.out.print("Digite o CPF");
		String cpfCliente = sc.next();
		System.out.println();
		System.out.print("Próxima etapa (Descrição do Produto): ");
		System.out.print("Tipo de produto: ");
		String tipoProduto = sc.next();
		// printar os casos 7 tipos
		//switch (tipo) 
		// case
		System.out.print("Qual o preço da unidade? ");
		double preco = sc.nextDouble();
		System.out.print("Qual a quantidade? ");
		int quantidade = sc.nextInt();
		System.out.println();
		System.out.print("Você adicionou documentos ( S | N )? ");
		Boolean temDoc = (sc.next().toUpperCase().charAt(0) == 'S' | sc.next().toUpperCase().charAt(0) == 'N') ? true : false;
		System.out.println();
		System.out.println();
		System.out.print("Digite o cep: ");
		String cep = sc.next();
		System.out.println();
		System.out.println("Digite o número da residência: ");
		short residencia = sc.nextShort();
		
		//testando
		DadosProduto produto = new DadosProduto(new Cliente(nomeCliente, cpfCliente), this, categoriaProduto, temDoc, new Endereco(cep, residencia));
		
	}

	public ArrayList<Fornecedor> arquivoFornecedores() {
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
	public boolean avisosCanal(DadosProduto produto) {
		// emitirAviso()
	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		// TODO Auto-generated method stub
		// chamar os avisos por ultimos
	}

	@Override
	protected void cadastrarUser() {
		// TODO Auto-generated method stub
		
	}

}
