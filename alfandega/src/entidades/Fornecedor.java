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
	// coloco cpf?
	private String caminhoFornecedoresFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFornecedores.txt";

	// Construtor vazio para o menu
	public Fornecedor() {
	}

	public Fornecedor(String nomeFornecedor, String emailFornecedor, String senha) {
		this.nomeFornecedor = nomeFornecedor;
		this.emailFornecedor = emailFornecedor;
		this.senha = senha;
	}

	// Para login do fornecedor
	public Fornecedor(String emailFornecedor, String senha) {
		this.emailFornecedor = emailFornecedor;
		this.senha = senha;
	}

	@Override
	public String getNome() {
		return nomeFornecedor;
	}

	@Override
	public String getCaminhoFileUser() {
		return caminhoFornecedoresFile;
	}

	public String getEmailFornecedor() {
		return emailFornecedor;
	}

	public String getSenha() {
		return senha;
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
		Boolean temDoc = (sc.next().toUpperCase().charAt(0) == 'S' | sc.next().toUpperCase().charAt(0) == 'N') ? true
				: false;
		System.out.println();
		System.out.println();
		System.out.print("Digite o cep: ");
		String cep = sc.next();
		System.out.println();
		System.out.println("Digite o número da residência: ");
		short residencia = sc.nextShort();

		// Adicionando cliente referente ao produto
		DadosProduto produto = new DadosProduto(new Cliente(nomeCliente, cpfCliente), this, categoriaProduto, temDoc,
				new Endereco(cep, residencia));
		Estoque.addProduto(produto);
		System.out.println("Produto cadastrado no estoque");
	}

	@Override
	public void cadastro() {
		condicaoCadastro(this, caminhoFornecedoresFile);
	}

	@Override
	public boolean confirmarUser(String[] dadosEntrada) {
		ArrayList<Fornecedor> fornecedores = listarUsuarios(getCaminhoFileUser());
		if (fornecedores == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Fornecedor fornecedor = new Fornecedor(dadosEntrada[0], dadosEntrada[1]);
			for (Fornecedor pessoa : fornecedores) {
				if (pessoa.equalsByEmailAndSenha(fornecedor.getEmailFornecedor(), fornecedor.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

	public void printarProdutos() {
		listarProdutos(null);
	}

	@Override
	public ArrayList<DadosProduto> avisosCanal(DadosProduto produto) {
		return false;
		// emitirAviso()
	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		// TODO Auto-generated method stub
		// chamar os avisos por ultimos
	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}

	public void verifPagamento(Cliente cliente) {

	}

	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.emailFornecedor.equals(email) && this.senha.equals(senha);
	}

	@Override
	public void operacoesUser() {
		System.out.println("Operações de fornecedor: ");
		Short valor = null;
		do {
			System.out.println(
					"1 - Cadastrar produto\n2 - Fazer pagamento\n3 - Listar produtos associados a conta\n4 - Sair da conta");
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

	@Override
	public void listarDividas() {
		// TODO Auto-generated method stub
		
	}

}
