package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import aplicacao.AplicarMenu;
import reserva.Estoque;
import reserva.EstoqueDespache;
import utilidade.ModelagemFile;

public class Funcionario implements Usuario<Funcionario>, Serializable {

	private static final long serialVersionUID = 1L;

	static Scanner sc = new Scanner(System.in);

	private String nomeAdm;
	private String email;
	private String senha;
	private String cpf;
	
	//Pensar em criar um sistema de pagamento para o funcionário.

	private String caminhoFuncionariosFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFuncionarios.txt";

	// Construtor para o menu
	public Funcionario() {
	}

	public Funcionario(String nomeAdm, String email, String senha, String cpf) {
		this.nomeAdm = nomeAdm;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
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

	public void cadastrarFuncionario() {
		// instanciamos o funcionario com o construtor
		System.out.println("Informe as condições de cadastro do funcionario: ");
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("CPF: ");
		String cpf = sc.next();
		System.out.println("E-mail: ");
		String email = sc.nextLine();
		System.out.println("Senha: ");
		String senha = sc.next();
		
		Funcionario funcionario = new Funcionario(nome, email, senha, cpf);
		funcionario.condicaoCadastro(funcionario, getCaminhoFileUser());
		
	}

	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha);
	}
	
	
	@Override
	public boolean confirmarUser(String[] dadosEntrada) {
		ArrayList<Funcionario> funcionarios = listarUsuarios(getCaminhoFileUser());
		if (funcionarios == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Funcionario funcionario = new Funcionario(dadosEntrada[0], dadosEntrada[1]);
			for (Funcionario pessoa : funcionarios) {
				if (pessoa.equalsByEmailAndSenha(funcionario.getEmail(), funcionario.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void operacoesUser() {
	    System.out.println();
	    int valor;
	    do {
	        valor = AplicarMenu.getRequest(5);

	        if (valor >= 0 && valor <= 7) {
	            switch (valor) {
	                case 1:
	                    System.out.println("Edição de produto do estoque: \n");
	                    System.out.println("Selecione o id do produto para edição: ");
	                    int code = sc.nextInt();
	                    DadosProduto produtoEditar = Estoque.buscarIDBinarySearch(code);
	                    System.out.println("-Opções de edição-");
	                    produtoEditar.editarRemessa(AplicarMenu.getRequest(6));
	                    System.out.println("\nPressione Enter para voltar");
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 2:
	                    System.out.println("Dados do Cliente/Fornecedor do registro: \n");
	                    System.out.println("1 - Listar\n2 - Apagar dados");
	                    short desejo = sc.nextShort();
	                    if (desejo == 1) {
	                        System.out.println("1 - Cliente\n2 - Fornecedor");
	                        desejo = sc.nextShort();
	                        if (desejo == 1) {
	                            Funcionario cl = new Funcionario();
	                            cl.listarUsuarios(cl.getCaminhoFileUser());
	                        } else if (desejo == 2) {
	                            Fornecedor forn = new Fornecedor();
	                            forn.listarUsuarios(forn.getCaminhoFileUser());
	                        }
	                    } else if (desejo == 2) {
	                        System.out.println("1 - Cliente\n2-Fornecedor");
	                        desejo = sc.nextShort();
	                        if (desejo == 1) {
	                            System.out.println("Digite o CPF do cliente: ");
	                            String cpf = sc.next();
	                            Funcionario cliente = new Funcionario(cpf);
	                            cliente.removerUser(cliente);
	                        } else if (desejo == 2) {
	                            System.out.println("Digite o email do fornecedor: ");
	                            String email = sc.next();
	                            Fornecedor fornecedor = new Fornecedor(email);
	                            fornecedor.removerUser(fornecedor);
	                        }
	                        System.out.println("Pressione Enter para voltar");
	                        sc.nextLine();
	                        sc.nextLine(); // Consumir a nova linha pendente
	                    }
	                    break;
	                case 3:
	                    System.out.println("Dados de Funcionario do registro:");
	                    System.out.println("Selecione uma opção: ");
	                    System.out.println("1 - Listar funcionarios\n2 - Remover funcionario");
	                    short opcao = sc.nextShort();
	                    if (opcao == 1) {
	                        System.out.println("Listar funcionarios");
	                        listarUsuarios(getCaminhoFileUser());
	                    } else if (opcao == 2) {
	                        System.out.println("Apagar funcionario do registro");
	                        System.out.println("Digite o cpf do funcionario: ");
	                        String cpf = sc.next();
	                        Funcionario funcionario = new Funcionario(cpf);
	                        System.out.printf("Tem certeza que deseja deletar o funcionario a seguir (s/n)?\n%s - %s - %s",
	                                funcionario.getNome(), funcionario.getEmail(), funcionario.getCpf());
	                        short contador = 0;
	                        do {
	                            contador++;
	                            System.out.println("Digite a sua senha para confirmar operação: ");
	                        } while (!sc.next().equals(getSenha()) && contador < 4);
	                        if (contador < 4) {
	                            System.out.println("Funcionario removido");
	                        } else {
	                            System.out.println("Operação cancelada, excedeu tentativas.");
	                        }
	                    }
	                    System.out.println("Pressione Enter para voltar");
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 4:
	                    System.out.println("Operações de edição de dívida: ");
	                    // chamar usar o listar produtos para identificar se tem avisos canal e passar para a função.
	                    System.out.println("Pressione Enter para voltar");
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 5:
	                    System.out.println("Operações de registro de funcionario");
	                    cadastrarFuncionario();
	                    System.out.println("Pressione Enter para voltar");
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 6:
	                    System.out.println("Listando Estoque\n");
	                    System.out.println("Buscar item de Cliente/nBuscar item de Fornecedor/n");
	                    for (DadosProduto produto : Estoque.listaProdutosEstoque()) {
	                        System.out.println(produto);
	                    }
	                    System.out.println("Encontar produtos: \n1 - CPF do Cliente\n2 - e-mail do Fornecedor\n-Pressione Enter para voltar");
	                    opcao = sc.nextShort();
	                    if (opcao == 1) {
	                        System.out.println("Listando produtos do cliente");
	                        System.out.println("Informe o cpf do cliente: ");
	                        String cpfCliente = sc.next();
	                        Cliente cl = new Cliente(cpfCliente);
	                        List<DadosProduto> produtosEstqCliente = Estoque.buscarClientEquals(cl);
	                        produtosEstqCliente.forEach(System.out::println);
	                    } else if (opcao == 2) {
	                        System.out.println("Listando produtos do fornecedor");
	                        System.out.println("Informe o e-mail do fornecedor");
	                        String emailFornecedor = sc.next();
	                        Fornecedor fornecedor = new Fornecedor(emailFornecedor);
	                        List<DadosProduto> produtosEstqForn = Estoque.listaProdutosEstoque().stream().filter(x -> x.getFornecedor().equals(fornecedor)).collect(Collectors.toList());
	                        produtosEstqForn.forEach(System.out::println);
	                    }
	                    System.out.println("Pressione Enter para voltar");
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 7:
	                    System.out.println("Listando produtos despachados");
	                    for (DadosProduto produto : EstoqueDespache.listaProdutosDespache()) {
	                        System.out.println(produto);
	                    }
	                    System.out.println("Encontar produtos: \n1 - CPF do Cliente\n2 - e-mail do Fornecedor\n-Pressione Enter para voltar");
	                    opcao = sc.nextShort();
	                    if (opcao == 1) {
	                    	System.out.println("Listando produtos do cliente");
	                        System.out.println("Informe o cpf do cliente: ");
	                        String cpfCliente = sc.next();
	                        Cliente cl = new Cliente(cpfCliente);
	                        List<DadosProduto> produtosEstqCliente = EstoqueDespache.buscarClienteEquals(cl);
	                        produtosEstqCliente.forEach(System.out::println);
	                    } else if (opcao == 2) {
	                    	System.out.println("Listando produtos do fornecedor");
	                        System.out.println("Informe o e-mail do fornecedor");
	                        String emailFornecedor = sc.next();
	                        Fornecedor fornecedor = new Fornecedor(emailFornecedor);
	                        List<DadosProduto> produtosEstqForn = EstoqueDespache.listaProdutosDespache().stream().filter(x -> x.getFornecedor().equals(fornecedor)).collect(Collectors.toList());
	                        produtosEstqForn.forEach(System.out::println);
	                    }
	                    sc.nextLine();
	                    sc.nextLine(); // Consumir a nova linha pendente
	                    break;
	                case 8:
	                    System.out.println("Fim das operações de usuário.");
	                    break;
	                default:
	                    System.out.println("Opção inválida. Tente novamente.");
	                    break;
	            }
	        } else {
	            System.out.println("Entrada inválida. Por favor, insira um número entre 0 e 7.");
	            sc.nextLine(); // Consumir a entrada inválida
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

	// criar metodo cadastrar funcionario, pois ele só podera ser criado a partir de
	// outro funcionario.

}
