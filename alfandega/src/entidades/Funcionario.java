package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import aplicacao.AplicarMenu;
import reserva.Estoque;
import reserva.EstoqueDespache;
import reserva.Local;
import sistemaInterno.Banco;
import utilidade.ModelagemFile;
import utilidade.ValidarDados;

public class Funcionario implements Usuario<Funcionario>, Serializable {

	private static final long serialVersionUID = 1L;

	static transient Scanner sc = new Scanner(System.in);

	private String nomeAdm;
	private String email;
	private String senha;
	private String cpf;

	// Caminho do arquivo onde os dados dos funcionários são armazenados
	private transient String caminhoFuncionariosFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFuncionarios.txt";

	// Construtor para o menu
	public Funcionario() {
	}
	// Construtor para criar um novo funcionário com nome, email, senha e CPF
	public Funcionario(String nomeAdm, String email, String senha, String cpf) {
		this.nomeAdm = nomeAdm;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}
	
	// Construtor para login do funcionário
	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	// Construtor para identificar funcionário por CPF
	public Funcionario(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getNome() {
		return nomeAdm;
	}

	public String getCaminhoFileUser() {
		return caminhoFuncionariosFile;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	private String getSenha() {
		return senha;
	}

	// Método para cadastrar um novo funcionário
	public void cadastrarFuncionario() {
		boolean cadastro = false;
		do {
			try {

				System.out.println("Informe as condições de cadastro do funcionario: ");
				System.out.print("Nome: ");
				String nome = sc.nextLine().trim();
				if (!ValidarDados.validarNome(nome)) {
					throw new OperacaoException("Erro, nome inválido");
				}
				System.out.print("CPF: ");
				String cpf = sc.next().trim().replace(".", "").replace("-", "");
				if (!ValidarDados.validarCPF(cpf)) {
					throw new OperacaoException("Erro, cpf não possui 11 dígitos");
				}
				System.out.print("E-mail: ");
				String email = sc.nextLine().trim();
				if (!ValidarDados.validarEmail(email)) {
					throw new OperacaoException("Erro, email não possui @");
				}
				System.out.print("Senha: ");
				String senha = sc.next();
				sc.nextLine();
				if (!ValidarDados.validarSenha(senha)) {
					throw new OperacaoException("Erro, senha menor que 6 dígitos");
				}
				Funcionario funcionario = new Funcionario(nome, email, senha, cpf);
				funcionario.condicaoCadastro(funcionario, getCaminhoFileUser());
				cadastro = true;
			} catch (OperacaoException e) {
				System.out.println("Erro na operação de cadastro: " + e.getMessage());
			}
		} while (!cadastro);

		// instanciamos o funcionario com o construtor

	}

	// Método auxiliar para comparar email e senha
	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha);
	}

	@Override
	public Funcionario confirmarUser(String[] dadosEntrada) {
		ArrayList<Funcionario> funcionarios = listarUsuarios(getCaminhoFileUser());
		if (funcionarios == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Funcionario funcionario = new Funcionario(dadosEntrada[0], dadosEntrada[1]);
			for (Funcionario pessoa : funcionarios) {
				if (pessoa.equalsByEmailAndSenha(funcionario.getEmail(), funcionario.getSenha())) {
					return pessoa;
				}
			}
		}
		return null;
	}

	
	// Método para obter uma solicitação do usuário
	public static byte shortQuests(int vetorAtual) {
		String[] vetor = getOptionsForVector(vetorAtual);
		if (vetor.length == 0) {
			throw new IllegalArgumentException("Opção inválida, tente novamente");
		}

		boolean entrada = false;
		int request = 4;
		short contador;
		// Exibe opções de solicitação

		do { // Insere a solicitação de usuário
			contador = 1;
			for (String opcao : vetor) {
				System.out.printf("%d - %s\n", contador++, opcao);
			}
			try {
				System.out.print("\n-> ");
				String digito = sc.nextLine().trim();
				if (digito.isEmpty()) {
					throw new IllegalArgumentException("Valor nulo lançado");
				}
				request = Integer.parseInt(digito);
				AplicarMenu.clearScreen();
				if (request < 1 || request > vetor.length) {
					System.out.println("Valor fora dos parâmetros, digite entre 1 e " + vetor.length);
				} else {
					entrada = true;
				}
			} catch (InputMismatchException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				sc.nextLine();
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida, por favor insira um número. Digite enter para tentar novamente.");
				sc.nextLine();
			}
		} while (!entrada);
		AplicarMenu.clearScreen();
		return (byte) request; // Retorna o valor enum da opção

	}

	// Método para obter opções de menu com base no vetor atual
	private static String[] getOptionsForVector(int vetorAtual) {
		switch (vetorAtual) {
		case 1:
			return new String[] { "Listar", "Apagar dados", "Voltar" };
		case 2:
			return new String[] { "Cliente", "Fornecedor", "Voltar" };
		case 3:
			return new String[] { "Listar funcionarios", "Remover funcionario", "Voltar" };
		case 4:
			return new String[] { "Buscar item de Cliente", "Buscar item de Fornecedor", "Voltar" };
		default:
			return new String[0]; // Retorna um array vazio como padrão
		}
	}

	@Override
	public void operacoesUser() {
		System.out.println();
		int valor;
		boolean fimOpCase = false;
		do {
			valor = AplicarMenu.getRequest(5);
			try {
				if (valor >= 0 && valor <= 8) {
					byte desejo;
					switch (valor) {
					case 1:
						System.out.println("Edição de produto do estoque: \n");
						System.out.println("Selecione o id do produto para edição: ");
						int code = sc.nextInt();
						sc.nextLine(); // Consumir a nova linha pendente
						if (!ValidarDados.validarIdProduto(code)) {
							throw new OperacaoException("Id do produto inválida, deve conter 6 dígitos");
						}
						DadosProduto produtoEditar = Estoque.buscarIDBinarySearch(code);
						System.out.println("-Opções de edição-");
						produtoEditar.editarRemessa(AplicarMenu.getRequest(6));
						System.out.println("\nPressione Enter para voltar");
						sc.nextLine(); // Consumir a nova linha pendente
						break;
					case 2:
						System.out.println("Dados do Cliente/Fornecedor do registro: \n");
						do {
							desejo = shortQuests(1);
							byte subDesejo = shortQuests(2);
							if (desejo == 1) {
								if (subDesejo == 1) {
									Cliente cl = new Cliente();
									cl.listarUsuarios(cl.getCaminhoFileUser()).forEach(System.out::println);
								} else if (subDesejo == 2) {
									Fornecedor forn = new Fornecedor();
									forn.listarUsuarios(forn.getCaminhoFileUser()).forEach(System.out::println);
								}
								System.out.println("\nPressione Enter para voltar");
								sc.nextLine();
							} else if (desejo == 2) {
								if (subDesejo == 1) {
									System.out.println("Digite o CPF do cliente: ");
									String cpf = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									if (!ValidarDados.validarCPF(cpf)) {
										throw new OperacaoException("Erro, cpf não possui 11 dígitos");
									}
									Cliente cliente = new Cliente(cpf);
									System.out.println("Cliente removido com sucesso!");
									cliente.removerUser(cliente);
								} else if (subDesejo == 2) {
									System.out.println("Digite o email do fornecedor: ");
									String email = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									if (!ValidarDados.validarEmail(email)) {
										throw new OperacaoException("Erro, email não possui @");
									}
									Fornecedor fornecedor = new Fornecedor(email);
									fornecedor.removerUser(fornecedor);
									System.out.println("\nFornecedor removido com sucesso!\n");

								}
							}
						} while (desejo != 3);
						break;
					case 3:
						System.out.println("Dados de Funcionario do registro:");
						System.out.println("Selecione uma opção: ");
						while (!fimOpCase) {
							desejo = shortQuests(3);
							if (desejo == 1) {
								System.out.println("Listar funcionarios");
								Funcionario funcionario = new Funcionario();
								funcionario.listarUsuarios(getCaminhoFileUser()).forEach(System.out::println);
								fimOpCase = true;
							} else if (desejo == 2) {
								System.out.println("Apagar funcionario do registro");
								System.out.println("Digite o cpf do funcionario: ");
								String cpf = sc.next();
								sc.nextLine(); // Consumir a nova linha pendente
								if (!ValidarDados.validarCPF(cpf)) {
									throw new IllegalArgumentException();
								}
								Funcionario funcionario = new Funcionario(cpf);
								System.out.printf(
										"Tem certeza que deseja deletar o funcionario a seguir (s/n)?\n%s - %s - %s",
										funcionario.getNome(), funcionario.getEmail(), funcionario.getCpf());
								short contador = 0;
								do {
									contador++;
									System.out.println("Digite a sua senha para confirmar operação: ");
								} while (!sc.next().equals(getSenha()) && contador < 4);
								sc.nextLine(); // Consumir a nova linha pendente
								if (contador < 4) {
									System.out.println("Funcionario removido");
								} else {
									System.out.println("Operação cancelada, excedeu tentativas.");
								}
								fimOpCase = true;
							} else if (desejo != 3) {
								System.out.println("Opcao inválida");
							}
						}
						System.out.println("Pressione Enter para voltar");
						sc.nextLine(); // Consumir a nova linha pendente
						break;
					case 4:
						System.out.println("Operações de Banco e Pagamentos: ");
						Banco banco = new Banco();
						banco.operacaoFuncionario();
						System.out.println("Pressione Enter para voltar");
						sc.nextLine(); // Consumir a nova linha pendente
						break;
					case 5:
						System.out.println("Operações de registro de funcionario");
						cadastrarFuncionario();
						System.out.println("Pressione Enter para voltar");
						sc.nextLine(); // Consumir a nova linha pendente
						break;
					case 6:
						System.out.println("Listando Estoque\n");
						if (Estoque.listaProdutosEstoque() == null || Estoque.listaProdutosEstoque().isEmpty()) {
							System.out.println("O estoque está vazio!\n");
							
						} else {
							
							for (DadosProduto produto : Estoque.listaProdutosEstoque()) {
								System.out.println(produto);
							}
							System.out.println();
							do {
								desejo = shortQuests(4);
								if (desejo == 1) {
									System.out.println("Listando produtos do cliente");
									System.out.println("Informe o cpf do cliente: ");
									String cpfCliente = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									Cliente cl = new Cliente(cpfCliente);
									List<DadosProduto> produtosEstqCliente = Estoque.buscarClientEquals(cl).stream()
											.filter(x -> x.getArmazenamentoAtual().equals(Local.ESTOQUE))
											.collect(Collectors.toList());
									produtosEstqCliente.forEach(System.out::println);
								} else if (desejo == 2) {
									System.out.println("Listando produtos do fornecedor");
									System.out.println("Informe o e-mail do fornecedor");
									String emailFornecedor = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									Fornecedor fornecedor = new Fornecedor(emailFornecedor);
									List<DadosProduto> produtosEstqForn = Estoque.listaProdutosEstoque().stream()
											.filter(x -> x.getFornecedor().equals(fornecedor)
													&& x.getArmazenamentoAtual().equals(Local.ESTOQUE))
											.collect(Collectors.toList());
									produtosEstqForn.forEach(System.out::println);
								}
							} while (desejo < 1 || desejo > 3);
						}
						System.out.println("Pressione Enter para voltar");
						sc.nextLine();
						break;
					case 7:
						System.out.println("Listando produtos despachados\n");
						if (Estoque.listaProdutosEstoque() == null || Estoque.listaProdutosEstoque().isEmpty()) {
							System.out.println("O estoque de despache está vazio!\n");
						} else {
							for (DadosProduto produto : EstoqueDespache.listaProdutosDespache()) {
								System.out.println(produto);
							}
							System.out.println();
							do {
								desejo = shortQuests(4);
								if (desejo == 1) {
									System.out.println("Listando produtos do cliente");
									System.out.println("Informe o cpf do cliente: ");
									String cpfCliente = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									Cliente cl = new Cliente(cpfCliente);
									List<DadosProduto> produtosEstqCliente = Estoque.buscarClientEquals(cl).stream()
											.filter(x -> x.getArmazenamentoAtual().equals(Local.DESPACHE))
											.collect(Collectors.toList());
									produtosEstqCliente.forEach(System.out::println);
								} else if (desejo == 2) {
									System.out.println("Listando produtos do fornecedor");
									System.out.println("Informe o e-mail do fornecedor");
									String emailFornecedor = sc.next();
									sc.nextLine(); // Consumir a nova linha pendente
									Fornecedor fornecedor = new Fornecedor(emailFornecedor);
									List<DadosProduto> produtosEstqForn = EstoqueDespache.listaProdutosDespache().stream()
											.filter(x -> x.getFornecedor().equals(fornecedor)
													&& x.getArmazenamentoAtual().equals(Local.DESPACHE))
											.collect(Collectors.toList());
									produtosEstqForn.forEach(System.out::println);
								}
							} while (desejo != 3);
						}
						System.out.println("Pressione Enter para voltar");
						sc.nextLine();
						break;
					case 8:
						System.out.println("Fim das operações de usuário.");
						Thread.sleep(2000);
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.");
						break;
					}
				} else {
					System.out.println("Entrada inválida. Por favor, insira um número entre 0 e 8.");
					sc.nextLine(); // Consumir a entrada inválida
				}
				AplicarMenu.clearScreen();
				Estoque.atualizarSistema();
				System.out.println();
			} catch (InterruptedException e) {
				System.out.println("Erro no sleep thread.");
				sc.nextLine(); // Consumir a nova linha pendente
			} catch (OperacaoException e) {
				System.out.println(
						"Erro na operação de usuário: " + e.getMessage() + ". Digite enter para tentar novamente.");
				sc.nextLine(); // Consumir a nova linha pendente
			} catch (InputMismatchException e) {
				System.out.println("Erro de input, o valor digitado não foi aceito, " + e.getMessage()
						+ "Pressione enter para tentar novamente");
				sc.nextLine(); // Consumir a nova linha pendente
			} catch (IllegalArgumentException e) {
				System.out.println(
						"Erro argumento indevido, " + e.getMessage() + "Pressione enter para tentar novamente");
				sc.nextLine(); // Consumir a nova linha pendente
			}
		} while (valor != 8);
	}

	@Override
	public ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque,
			ArrayList<DadosProduto> produtosDespache) {
		return null;
	}

	@Override
	public void removerUser(Funcionario pessoa) {
		ArrayList<Funcionario> funcionarios = listarUsuarios(getCaminhoFileUser());
		try {
			funcionarios.removeIf(user -> user.equals(pessoa));
		} catch (NullPointerException e) {
			System.out.println("Erro ao listar o arquivo: " + e.getMessage());
		}
		ModelagemFile.serializar(getCaminhoFileUser(), funcionarios);
	}

	@Override
	public String toString() {
		return "Funcionario: " + nomeAdm + ", CPF:" + cpf;
	}

	// criar metodo cadastrar funcionario, pois ele só podera ser criado a partir de
	// outro funcionario.

}
