package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import aplicacao.AplicarMenu;
import reserva.Estoque;
import sistemaInterno.Dividas;
import sistemaInterno.EstoqueDivida;
import utilidade.ModelagemFile;

public class Cliente extends Utilizador<Cliente> implements Usuario<Cliente>, Serializable {

	// alterar os construtores usando this
	// Declaração de variáveis e constantes
	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private String email;
	private String senha;
	private String cpf;

	private transient static String caminhoClientesFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileClientes.txt";

	static Scanner sc = new Scanner(System.in);

	// construtor de cadastro
	public Cliente(String nomeCliente, String email, String senha, String cpf) {
		this.nomeCliente = nomeCliente;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	// para operações de login
	public Cliente(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	// gerando construtor de uso para o Vendedor e busca
	public Cliente(String cpf) {
		this.cpf = cpf;
	}

	public Cliente() {
	}

	@Override
	public String getNome() {
		return nomeCliente;
	}

	private String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	private String getSenha() {
		return senha;
	}

	public String getCaminhoFileUser() {
		return caminhoClientesFile;
	}

	// Método para listar os produtos do cliente
	@Override
	public ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque,
			ArrayList<DadosProduto> produtosDespache) {
		// recebendo uma lista já filtrada dos produtos do cliente Estoque-Despache.
		ArrayList<DadosProduto> listaGeralProdutos = new ArrayList<>();
		for (DadosProduto dadoProduto : produtosEstoque) {
			// se não estiver no estoque, estará no despache
			listaGeralProdutos.add(dadoProduto);
		}
		for (DadosProduto dadoProduto : produtosDespache) {
			listaGeralProdutos.add(dadoProduto);
		}
		return listaGeralProdutos;
	}

	// avisoscanal receberá o listar produtos()

	// Método para cadastro do cliente
	@Override
	public void cadastro() {
		condicaoCadastro(this, caminhoClientesFile);
	}

	// Método para gerar o hash code do cliente baseado no CPF
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	// Método para comparar dois objetos Cliente
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	// ao validar o cpf do cliente, apenas considerar os números, se vier uma letra
	// faz um while.
	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente


	@Override
	public Cliente confirmarUser(String[] dadosEntrada) {
		ArrayList<Cliente> clientes = listarUsuarios(getCaminhoFileUser());
		if (clientes == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Cliente cliente = new Cliente(dadosEntrada[0], dadosEntrada[1]);
			for (Cliente pessoa : clientes) {
				if (pessoa.equalsByEmailAndSenha(cliente.getEmail(), cliente.getSenha())) {
					return pessoa;
				}
			}
		}
		return null;
	}

	// Método para remover o usuário (cliente)
	@Override
	public void removerUser(Cliente pessoa) {
		ArrayList<Cliente> clientes = listarUsuarios(getCaminhoFileUser());
		try {
			clientes.removeIf(user -> user.equals(pessoa));
		} catch (NullPointerException e) {
			System.out.println("Erro ao listar o arquivo: " + e.getMessage());
		}
		ModelagemFile.serializar(caminhoClientesFile, clientes);
	}
	// Método para comparar email e senha
	
	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha); //// equals já está sendo usado para o cpf
	}

	// Método para executar operações de usuário
	@Override
	public void operacoesUser() {
		System.out.println("Operações de Cliente: ");
		System.out.println();
		int valor = 0;
		do {
			System.out.print("Escolha uma opção: \n");

			valor = AplicarMenu.getRequest(3);
			// Verifique se a entrada é um inteiro válido
			if (valor > 0 || valor < 6) {
				switch (valor) {
				case 1:
					System.out.println("Listando produtos: ");
					printarProdutos(Estoque.buscarClientEquals(this));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 2:
					// pagando
					System.out.println("Quadro de pagamento: \n");
					pagamento();
					break;
				case 3:
					System.out.println("Quadro de dívidas: \n");
					listarDividas();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 4:
					// chamar usar o listar produtos para identificar se tem avisos canal e passar
					// para a função.

					System.out.println("Quadro de avisos: \n");
					avisosCanal(Estoque.buscarClientEquals(this));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 5:
					System.out.println("Saindo da conta...");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Erro no sleep: " + e.getMessage());
					}
					break;
				default:
					System.out.println("Opção inválida. Pressione enter para voltar e tente novamente.");
					sc.nextLine();
					break;
				}
				AplicarMenu.clearScreen();
			} else {
				System.out.println("Entrada inválida. Por favor, insira um número.");
				sc.nextLine(); // Consumir a entrada inválida
			}
			Estoque.atualizarSistema();
			System.out.println();
		} while (valor != 5);
	}

	// Método toString para retornar as informações do cliente
	@Override
	public String toString() {
		return "[nome=" + nomeCliente + ", cpf=" + cpf + "]";
	}

	// Método para listar as dívidas do cliente
	@Override
	public void listarDividas() {
		System.out.println("Mostrando Dívidas relacionadas ao cliente.");
		List<Dividas> dividas = EstoqueDivida.encontrarDividasPorCliente(this);
		if (dividas == null || dividas.isEmpty()) {
			System.out.println("\nVocê não possui dívidas no registro.");
			System.out.println("Verifique se possui algum produto aguardando pagamento\n");
		} else {
			for (Dividas divCliente : dividas) {
				System.out.println(divCliente);
			}
		}
	}
}
