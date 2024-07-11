package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

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

	public String getSenha() {
		return senha;
	}

	public void cadastrarFuncionario(String nomeAdm, String email, String senha, String cpf) {
		// instanciamos o funcionario com o construtor
		
	}

	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha);
	}

	public void editarDadoProduto() {
	}
	

	public void editarProcesso() {

	}

	public void editarDivida() {

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
	public void cadastro() {
		condicaoCadastro(this, caminhoFuncionariosFile);
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
					System.out.println("Editar produto: ");
					//lista o produto do estoque por id.
					// a partir disso, chamar a função dentro do 
					// this.printarProdutos(listarProdutos(Estoque.buscarClientEquals(this),
					// Despache.buscarClientEquals(this)));
					// Fornecedor escolhe entre escolher com um determinado cliente e ele ou todos
					// os seus produtos fornecidos
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 2:
					// pagando
					System.out.println("Editar cliente: ");

					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 3:
					System.out.println("Editar Fornecedor");
					// this.listarDividas(null);
					// Fornecedor escolhe entre escolher com um determinado cliente e ele ou todos
					// os seus produtos fornecidos
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 4:
					System.out.println("Operações de edição de dívida: ");
					// chamar usar o listar produtos para identificar se tem avisos canal e passar
					// para a função.

					// this.avisosCanal();
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 5:
					System.out.println("Operações de registro de funcionario");
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 6:
					System.out.println("Listando Estoque");
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
					break;
				case 7:
					System.out.println("Listando produtos despachados");
					System.out.println("Pressione Enter para voltar");
					sc.nextLine();
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

	// criar metodo cadastrar funcionario, pois ele só podera ser criado a partir de
	// outro funcionario.

}
