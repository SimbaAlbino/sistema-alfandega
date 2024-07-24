package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import aplicacao.AplicarMenu;
import reserva.Estoque;
import reserva.EstoqueDespache;
import sistemaInterno.Dividas;
import sistemaInterno.EstoqueDivida;
import tiposProduto.Acessorios;
import tiposProduto.Automoveis;
import tiposProduto.Eletrodomesticos;
import tiposProduto.Ferramentas;
import tiposProduto.Informatica;
import tiposProduto.Mobilia;
import tiposProduto.Produto;
import tiposProduto.Roupa;
import utilidade.ModelagemFile;
import utilidade.ValidarDados;

public class Fornecedor extends Utilizador<Fornecedor> implements Usuario<Fornecedor>, Serializable {

	private static final long serialVersionUID = 1L;

	static transient Scanner sc = new Scanner(System.in);

	private String nomeFornecedor;
	private String emailFornecedor;
	private String senha;

	private transient String caminhoFornecedoresFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFornecedores.txt";

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
		boolean fimOp = false, temDoc = false;
		String cpfCliente = null, cep = null, valorString = null;
		int quantidade = 0;
		double preco = 0;
		Integer tipoProduto = null;
		short residencia = 0;
		short iterador = 1;

		String[] perguntas = { "Digite o CPF do Cliente: ",
				"Próxima etapa (Descrição do Produto): \nTipo de produto: \n", "Qual o preço da unidade? ",
				"Qual a quantidade? ", "Você adicionou documentos ( S | N )? ", "Digite o cep: ",
				"Digite o número da residência: " };

		while (!fimOp) {
			try {
				for (; iterador <= perguntas.length; iterador++) {
					System.out.print(perguntas[iterador - 1]);
					switch (iterador) {
					case 1:
						cpfCliente = sc.next().trim().replace(".", "").replace("-", "");
						sc.nextLine();
						if (!ValidarDados.validarCPF(cpfCliente)) {
							throw new IllegalArgumentException("os dígitos de cpf ultrapassam 11");
						}
						break;
					case 2:
						tipoProduto = AplicarMenu.getRequest(7); // fazer um get com todos os tipos de produto
						break;
					case 3:
						valorString = sc.next();
						sc.nextLine();
                        // Substituir a vírgula por ponto
                        valorString = valorString.replace(',', '.');
                        try {
                            preco = Double.parseDouble(valorString);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Formato de preço inválido");
                        }
                        break;
					case 4:
						quantidade = sc.nextInt();
						sc.nextLine();
						break;
					case 5:
						String resposta = sc.next().toUpperCase();
						sc.nextLine();
						if (resposta.charAt(0) == 'S') {
							temDoc = true;
						} else if (resposta.charAt(0) == 'N') {
							temDoc = false;
						} else {
							throw new IllegalArgumentException("Digite (s/n) para Sim/Não");
						}
						break;
					case 6:
						cep = sc.next();
						sc.nextLine();
						if (!ValidarDados.validarCEP(cep)) {
							throw new IllegalArgumentException("O valor do cep deve ter 8 dígitos");
						}
						break;
					case 7:
						residencia = sc.nextShort();
						sc.nextLine();
						break;
					default:
						break;
					}
					System.out.println();
				}

				// talvez fazer um vetor para percorrer

				DadosProduto produto = new DadosProduto(new Cliente(cpfCliente), this, criarProduto(tipoProduto, preco, quantidade),
						temDoc, new Endereco(cep, residencia));
				produto.gerarIdRastreio();
				Estoque.addProduto(produto);
				fimOp = true;
			} catch (InputMismatchException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				sc.nextLine();
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(
						"Erro por argumento inválido, digite os dados do produto novamente: " + e.getMessage());
				sc.nextLine();
				sc.nextLine();
			}
		}

		// Adicionando cliente referente ao produto
		System.out.println("Produto cadastrado no estoque\n");
	}

	private static Produto criarProduto(int tipoProduto, double preco, int quantidade) throws IllegalArgumentException {
		Map<Integer, Produto> tipoProdutoMap = new HashMap<>();
		// armazenar entradas chave-valor como um dicionário de um valor, único.
		tipoProdutoMap.put(1, new Acessorios(preco, quantidade));
		tipoProdutoMap.put(2, new Automoveis(preco, quantidade));
		tipoProdutoMap.put(3, new Eletrodomesticos(preco, quantidade));
		tipoProdutoMap.put(4, new Ferramentas(preco, quantidade));
		tipoProdutoMap.put(5, new Informatica(preco, quantidade));
		tipoProdutoMap.put(6, new Mobilia(preco, quantidade));
		tipoProdutoMap.put(7, new Roupa(preco, quantidade));

		Produto produto = tipoProdutoMap.get(tipoProduto);
		if (produto == null) {
			throw new IllegalArgumentException("Tipo de produto inválido");
		}

		return produto;
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

			valor = AplicarMenu.getRequest(4);
			// Verifique se a entrada é um inteiro válido
			if (valor > 0 || valor < 6) {
				switch (valor) {
				case 1:
					// feito, mas precisa de throws
					// Fornecedor escolhe entre escolher com um determinado cliente e ele ou todos
					// os seus produtos fornecidos
					System.out.println("Listando produtos: ");
					
					printarProdutos(
							listarProdutos(Estoque.listaProdutosEstoque(), EstoqueDespache.listaProdutosDespache()));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 2:
					System.out.println("Cadastro de produto: \n");
					cadastrarProduto();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 3:
					// pagando
					System.out.println("Quadro de pagamento: \n");
					pagamento();
					break;
				case 4:
					// os seus produtos fornecidos
					System.out.println("Dívidas relacionadas ao fornecedor:\n");
					listarDividas();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 5:
					// chamar usar o listar produtos para identificar se tem avisos canal e passar
					// para a função.
					
					this.avisosCanal(
							listarProdutos(Estoque.listaProdutosEstoque(), EstoqueDespache.listaProdutosDespache()));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 6:
					System.out.println("Saindo da conta...");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Erro no sleep: " + e.getMessage());
					}
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
				}
			} else {
				System.out.println("Entrada inválida. Por favor, insira um número.");
				sc.nextLine(); // Consumir a entrada inválida
			}
			Estoque.atualizarSistema();
			AplicarMenu.clearScreen();
		} while (valor != 6);
	}

	@Override
	public ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque,
			ArrayList<DadosProduto> produtosDespache) {
		// listar do fornecedor ou fornecedor entre tal cliente
		List<DadosProduto> listaFiltrada = new ArrayList<>();
		System.out.println(
				"\nPara encontrar seus produtos, informe: \n1 - Listar por Fornecedor associado\n2 - Listar por associação Fornecedor-Cliente");
		short tipoListagem = 0;
		List<DadosProduto> listaEstoque = new ArrayList<>();
		List<DadosProduto> listaDespache = new ArrayList<>();
		if (Estoque.listaProdutosEstoque() != null) {
			listaEstoque = Estoque.listaProdutosEstoque();
		}
		if (EstoqueDespache.listaProdutosDespache() != null) {
			listaEstoque = EstoqueDespache.listaProdutosDespache();
		}
		try {
			do {
				System.out.print("\n->");
				tipoListagem = sc.nextShort();
				sc.nextLine();
				if (tipoListagem == 1) {
					listaEstoque = listaEstoque.stream().filter(x -> x.getFornecedor().equals(this))
							.collect(Collectors.toList());
					listaDespache = listaDespache.stream().filter(x -> x.getFornecedor().equals(this))
							.collect(Collectors.toList());
				} else if (tipoListagem == 2) {
					System.out.println("\nInforme o CPF do cliente associado: ");
					String cpfCliente = sc.next().trim().replace(".", "").replace("-", "");
					sc.nextLine();
					if (!ValidarDados.validarCPF(cpfCliente)) {
						throw new IllegalArgumentException("número de dígites deve ser 11");
					}
					Cliente cliente = new Cliente(cpfCliente);
					listaEstoque = listaEstoque.stream()
							.filter(x -> x.getFornecedor().equals(this) && x.getCliente().equals(cliente))
							.collect(Collectors.toList());
					listaEstoque = listaDespache.stream()
							.filter(x -> x.getFornecedor().equals(this) && x.getCliente().equals(cliente))
							.collect(Collectors.toList());
				} else {
					System.out.println("Opção inválida, tente novamente.");
				}
				listaFiltrada.addAll(listaDespache);
				listaFiltrada.addAll(listaDespache);
				// corrigir
			} while (tipoListagem != 1 && tipoListagem != 2);
		} catch (IllegalArgumentException e) {
			System.out.printf("Erro, opção inválida, %s, tente novamente, pressione enter.\n", e.getMessage());
			sc.nextLine();
		}
		AplicarMenu.clearScreen();
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
	public String toString() {
		return "[nomeFornecedor=" + nomeFornecedor + ", emailFornecedor=" + emailFornecedor + "]";
	}

	public void verifPagamento(Cliente cliente) {
		
	}
	
	@Override
	public void listarDividas() {
		try {
			List<Dividas> dividasUtiliziador = EstoqueDivida.listaDividas().stream().filter(x -> x.getDadosProduto().getFornecedor().equals(this)).collect(Collectors.toList());
			if (dividasUtiliziador != null)
				dividasUtiliziador.forEach(System.out::println);
			else
				System.out.println("Você não possui dívidas vinculadas.");
		} catch (Exception e) {
	        System.out.println("A sua listagem de dívidas não é acessível.");
	    }
		
	}

}
