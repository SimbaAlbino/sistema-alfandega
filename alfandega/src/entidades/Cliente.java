package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import reserva.Estoque;

public class Cliente extends Utilizador<Cliente> implements Usuario<Cliente>, Serializable {

	// alterar os construtores usando this

	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private String email;
	private String senha;
	private String cpf;

	private static String caminhoClientesFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileClientes.txt";

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

	@Override
	public String getCaminhoFileUser() {
		return caminhoClientesFile;
	}
	
	public void printarProdutos(ArrayList<DadosProduto> listaTodosProdutos) {
		System.out.println("Total de registros: " + listaTodosProdutos.size());
		System.out.println();
		System.out.println("🟡 indica que é preciso realizar ação para que o objeto seja encaminhado ao seu destino.");
		System.out.println("🔴 simboliza que o produto será retornado por expirar");
		System.out.println("⚫ mostra que o produto foi negado na alfândega e foi encaminhado para as autoridades.");
		System.out.println();
		System.out.printf("%s", "aviso", "id", "cpf vinculado", "situação atual", "data da situação");
		//recebendo uma lista já filtrada de todos produtos do cliente.
		for (DadosProduto dadoProduto : listaTodosProdutos) {
			// se não estiver no estoque, estará no despache
			System.out.println(dadoProduto);
		}
	}

	@Override
	public ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque, ArrayList<DadosProduto> produtosDespache ) {
		//recebendo uma lista já filtrada dos produtos do cliente Estoque-Despache.
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
	
	public void avisosCanal(ArrayList<DadosProduto> produtosCliente) {
		// contador para o total de produtos
		long contador = 0;
		for (DadosProduto produto : produtosCliente) {
			if (!(produto.getRecado() == null)) {
				System.out.printf("O produto: %s está %s - %s", produto.getTipoProduto(), produto.getStatus(),
						produto.getRecado());
				contador ++;
			}
		}
		System.out.println("Total de avisos: " + contador);
		System.out.println();
		if (contador == 0) {
			System.out.println("Você não possui avisos de urgência estendidos do produto.");
		}
		// Se o produto tiver no estoque e com a coloção amarela, verificar com o
		// funcionario, mensagem;
	}

	@Override
	public void cadastro() {
		condicaoCadastro(this, caminhoClientesFile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

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
	public boolean confirmarUser(String[] dadosEntrada) {
		ArrayList<Cliente> clientes = listarUsuarios(getCaminhoFileUser());
		if (clientes == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Cliente cliente = new Cliente(dadosEntrada[0], dadosEntrada[1]);
			for (Cliente pessoa : clientes) {
				if (pessoa.equalsByEmailAndSenha(cliente.getEmail(), cliente.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

	// equals já está sendo usado para o cpf
	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha);
	}

	@Override
	public void operacoesUser() {
		System.out.println("Operações de Cliente: ");
		System.out.println();
		int valor = 0;
		do {
			System.out.println(
					"1 - Listar produtos associados a conta\n2 - Fazer pagamento\n3 - Dívidas\n4 - Notificações de produto\n5 - Sair da conta");
			System.out.print("Escolha uma opção: ");

			valor = sc.nextInt();
			// Verifique se a entrada é um inteiro válido
			if (valor > 0 || valor < 6) {
				switch (valor) {
				case 1:
					System.out.println("Listando produtos: ");
					//this.printarProdutos(listarProdutos(Estoque.buscarClientEquals(this), Despache.buscarClientEquals(this)));
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 2:
					//pagando
					this.pagamento();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 3:
					//this.listarDividas(null);
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 4:
					// chamar usar o listar produtos para identificar se tem avisos canal e passar
					// para a função.

					//this.avisosCanal();
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

	@Override
	public String toString() {
		return "Cliente [nomeCliente=" + nomeCliente + ", cpf=" + cpf + "]";
	}
	
	// parte de gabriel
	@Override
	protected void pagamento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listarDividas() {

	}
}
