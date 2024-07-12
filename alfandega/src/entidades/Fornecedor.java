package entidades;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import reserva.Estoque;
import reserva.EstoqueDespache;
import tiposProduto.Acessorios;
import tiposProduto.Automoveis;
import tiposProduto.Eletrodomesticos;
import tiposProduto.Ferramentas;
import tiposProduto.Informatica;
import tiposProduto.Mobilia;
import tiposProduto.Produto;
import tiposProduto.Roupa;
import utilidade.ModelagemFile;

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

	// aplicar nome email e senha como parâmetros em utilizador?
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
	
	
	// passando para o Funcionario apagar a conta
	public Fornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}

	@Override
	public String getNome() {
		return nomeFornecedor;
	}

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

	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.emailFornecedor.equals(email) && this.senha.equals(senha);
	}

	@Override
	public void operacoesUser() {
		System.out.println();
		int valor = 0;
		do {
			System.out.print("Escolha uma opção: \n");

			valor = sc.nextInt();
			// Verifique se a entrada é um inteiro válido
			if (valor > 0 || valor < 6) {
				switch (valor) {
				case 1:
					//feito, mas precisa de throws
					// Fornecedor escolhe entre escolher com um determinado cliente e ele ou todos
					// os seus produtos fornecidos
					System.out.println("Listando produtos: ");
					this.printarProdutos(listarProdutos(Estoque.listaProdutosEstoque(), EstoqueDespache.listaProdutosDespache()));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 2:
					// pagando
					this.pagamento();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 3:
					// this.listarDividas(null);
					// Fornecedor escolhe entre escolher com um determinado cliente e ele ou todos
					// os seus produtos fornecidos
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 4:
					// chamar usar o listar produtos para identificar se tem avisos canal e passar
					// para a função.

					// this.avisosCanal();
					this.avisosCanal(listarProdutos(Estoque.listaProdutosEstoque(), EstoqueDespache.listaProdutosDespache()));
					//pegando aviso dos produtos em estoque e despachados
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 5:
					System.out.println("Saindo da conta...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
				}
			} else {
				System.out.println("Entrada inválida. Por favor, insira um número.");
				sc.nextLine(); // Consumir a entrada inválida
			}
		} while (valor != 5);
		System.out.println("Fim das operações de usuário.");
	}

	// seria interessante uma função que aceitasse um predicado no estoque
	//recebendo 2 arrays, do estoque e do despache
	@Override
	public ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque, ArrayList<DadosProduto> produtosDespache) {
		//listar do fornecedor ou fornecedor entre tal cliente
		List<DadosProduto> listaFiltrada = new ArrayList<>();
		System.out.println("Para encontrar seus produtos, informe: \n1 - Listar por Fornecedor associado\n2 - Listar por Associação Fornecedor-Cliente");
		short tipoListagem = 0;
		
		List<DadosProduto> listaEstoque = Estoque.listaProdutosEstoque();
		List<DadosProduto> listaDespache = EstoqueDespache.listaProdutosDespache();
		
		while (tipoListagem != 1 && tipoListagem != 2) {
			tipoListagem = sc.nextShort();
			if (tipoListagem == 1) {
				listaEstoque = listaEstoque.stream().filter(x -> x.getFornecedor().equals(this)).collect(Collectors.toList());
				listaDespache = listaDespache.stream().filter(x -> x.getFornecedor().equals(this)).collect(Collectors.toList());
			} else if (tipoListagem == 2) {
				System.out.println("Informe o CPF do cliente associado: ");
				String cpfCliente = sc.nextLine();
				Cliente cliente = new Cliente(cpfCliente);
				listaEstoque = listaEstoque.stream().filter(x -> x.getFornecedor().equals(this) && x.getCliente().equals(cliente)).collect(Collectors.toList());
				listaEstoque = listaDespache.stream().filter(x -> x.getFornecedor().equals(this) && x.getCliente().equals(cliente)).collect(Collectors.toList());
			} else {
				System.out.println("Opção inválida, tente novamente.");
				System.out.println("1 - Listar por Fornecedor associado\\n2 - Listar por Associação Fornecedor-Cliente");
			}
			listaFiltrada.addAll(listaDespache);
			listaFiltrada.addAll(listaDespache);
			//corrigir
		}
		return (ArrayList<DadosProduto>) listaFiltrada;
	}
	
	@Override
	public void removerUser(Fornecedor pessoa) {
		ArrayList<Fornecedor> fornecedores = listarUsuarios(getCaminhoFileUser());
        try {
            fornecedores.removeIf(user -> user.equals(pessoa));
        } catch (NullPointerException e) {
            System.out.println("Erro ao listar o arquivo: " + e.getMessage());
        }
        ModelagemFile.serializar(getCaminhoFileUser(), fornecedores);
    }

	@Override
	public int hashCode() {
		return Objects.hash(emailFornecedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(emailFornecedor, other.emailFornecedor);
	}

	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}

	public void verifPagamento(Cliente cliente) {

	}

	@Override
	public void listarDividas() {
		// TODO Auto-generated method stub

	}

}
