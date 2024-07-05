package entidades;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import reserva.Estoque;
import tiposProduto.Acessorios;
import tiposProduto.Automoveis;
import tiposProduto.Eletrodomesticos;
import tiposProduto.Ferramentas;
import tiposProduto.Informatica;
import tiposProduto.Mobilia;
import tiposProduto.Produto;
import tiposProduto.Roupa;

public class Fornecedor extends Utilizador<Fornecedor> implements Usuario<Fornecedor>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	static Scanner sc = new Scanner(System.in);

	private String nomeFornecedor;
	private String emailFornecedor;
	private String senha;
	private String cpf;
	//coloco cpf?
	private String caminhoFornecedoresFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFornecedores.txt";

	public Fornecedor(String nomeFornecedor, String emailFornecedor, String senha, String cpf) {
		this.nomeFornecedor = nomeFornecedor;
		this.emailFornecedor = emailFornecedor;
		this.senha = senha;
		this.cpf = cpf;
	}
	
	//Para login do fornecedor
	public Fornecedor(String emailFornecedor, String senha) {
		this.emailFornecedor = emailFornecedor;
		this.senha = senha;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public String getCaminhoFornecedoresFile() {
		return caminhoFornecedoresFile;
	}
	
	public void operadorFornecedor() {
		System.out.println("Operações de fornecedor: ");
		Short valor = null;
		do {
			System.out.println("1 - Cadastrar produto\n2 - Fazer pagamento\n3 - Listar produtos associados a conta\n4 - Sair da conta");
			 valor = sc.nextShort();
			switch (valor) {
			case 1:
				this.cadastrarProduto();
				break;
			case 2: 
				this.pagamento();
				break;
			case 3: 
				this.printarProdutos();
				break;
			case 4:
				break;
			}
		} while (valor > 0 || valor < 4);
				
	}

	public void cadastrarProduto() {
		Produto categoriaProduto = null;
		
		System.out.print("Digite o nome do Cliente: ");
		String nomeCliente = sc.nextLine();
		System.out.print("Digite o CPF");
		String cpfCliente = sc.next();
		System.out.println();
		System.out.print("Próxima etapa (Descrição do Produto): ");
		System.out.print("Tipo de produto: ");
		System.out.println("");
		short tipoProduto = sc.nextShort();
		System.out.print("Qual o preço da unidade? ");
		double preco = sc.nextDouble();
		System.out.print("Qual a quantidade? ");
		int quantidade = sc.nextInt();
		switch (tipoProduto) {
		case 1:
			categoriaProduto = new Acessorios(preco, quantidade);
			break;
		case 2:
			categoriaProduto = new Automoveis(preco, quantidade);
			break;
		case 3:
			categoriaProduto = new Eletrodomesticos(preco, quantidade);
			break;
		case 4:
			categoriaProduto = new Ferramentas(preco, quantidade);
			break;
		case 5:
			categoriaProduto = new Informatica(preco, quantidade);
			break;
		case 6:
			categoriaProduto = new Mobilia(preco, quantidade);
			break;
		case 7:
			categoriaProduto = new Roupa(preco, quantidade);
			break;
		default:
			break;
		}
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
		Estoque.addProduto(produto);
		System.out.println("Produto cadastrado no estoque");
	}
	
	public void printarProdutos() {
	}

	@Override
	public void cadastro() {
		condicaoCadastro(this, caminhoFornecedoresFile);
	}

	public void verifPagamento(Cliente cliente) {

	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean avisosCanal(DadosProduto produto) {
		return false;
		// emitirAviso()
	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		// TODO Auto-generated method stub
		// chamar os avisos por ultimos
	}


	@Override
	public void confirmarUser(String[] dadosEntrada) {
		// TODO Auto-generated method stub
		
	}
	
	
}
