package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import aplicacao.MenuUser;
import utilidade.ModelagemFile;

public interface Usuario<T> {
	
	static Scanner sc = new Scanner(System.in);

	void listarProdutos(ArrayList<DadosProduto> produtosFiltrados);

	boolean avisosCanal(DadosProduto produto);

	//cada usuario terá seu confirmarUser dentro de sua classe para a senha e email serem restritos à classe.
	boolean confirmarUser(String[] dadosEntrada);
	

	default void apagarUser(String caminho, T classChamada) {
		ArrayList<?> pessoas = new ArrayList<>();
		try {
			pessoas = (ArrayList<?>) ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (pessoas.contains(classChamada)) {
			pessoas.remove(classChamada);
		}
		// ver como faz para remover uma linha do arquivo
		// act.serializar();
		// fazer em cada classe usuario, vendedor e funcionario umaa arraylist e chamar
		// esse método apagarUser
	}

	default void printarUsers(T classChamada, String caminho) {
		// utilidade. desserializar();
		System.out.printf("Lista de todos os %s: %n", classChamada.getClass().getSimpleName());
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String line = br.readLine();
			int contador = 1;
			while (line != null) {
				String[] dadosUser = line.split(",");
				System.out.printf("%d %s: %s%n", contador++, classChamada.getClass().getSimpleName(), dadosUser[0]);
				br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao listar o arquivo clientes: " + e.getMessage());
		}
	}

	default String[] loginUser() {
		Scanner sc = null;
		String[] dados = new String[2];
		short contador = 3;
		try {
			sc = new Scanner(System.in);
			System.out.println("- Login -:");
			do {
				System.out.print("E-mail: ");
				dados[0] = sc.next();
				System.out.print("Senha: ");
				dados[1] = sc.next();
				contador--;
				if (confirmarUser(dados)) {
					break;
				} else {
					dados = null;
				}
				System.out.println("E-mail/Senha errados, tente novamente!");
			} while (contador > 0);
			if (dados.equals(null)) {
				throw new IllegalAccessError("Seus dados não correnspondem ao sistema. Tente novamente mais tarde.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro por um argumento ilegal: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		return dados;
	}

	void cadastro();

	// O método abaixo recebe uma lista atualizada para ser serializada, ele verifica se pode adicionar e assim o faz
	@SuppressWarnings("unchecked")
	default void condicaoCadastro(T classChamada, String caminho) {
		ArrayList<T> listaUsers = null;
		try {
			listaUsers = (ArrayList<T>) ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Inicializa a lista se estiver nula
		if (listaUsers == null) {
			listaUsers = new ArrayList<>();
		}
		if (listaUsers.contains(classChamada)) {
			System.out.println("Usuário já cadastrado no sistema.");
		} else {
			listaUsers.add(classChamada);
			ModelagemFile.serializar(caminho, listaUsers);
			System.out.println("Usuário cadastrado");
		}
	}
	
	public static void identificarCadastro(MenuUser status) {
		System.out.println("Cadastrar:");
		System.out.println("Seu nome: ");
		String nome = sc.nextLine();
		System.out.println("Seu e-mail: ");
		String email = sc.next();
		System.out.println("Sua senha: ");
		String senha = sc.next();
		System.out.println("Seu cpf: ");
		String cpf = sc.next();
		
		if (status.equals(MenuUser.CLIENTE)) {
			Cliente pessoa = new Cliente(nome, email, senha, cpf);
			pessoa.cadastro();
		} else if (status.equals(MenuUser.FORNECEDOR)) {
			Fornecedor pessoa = new Fornecedor(nome, email, senha, cpf);
			pessoa.cadastro();
		} else if (status.equals(MenuUser.FUNCIONARIO)) {
			System.out.println("Um usuário só pode ser cadastrado por outro funcionário.");
		} else {
			System.out.println("Operador não encontrado");
		}
		//chamar o método cadastrarUser
	}
	// ArrayList<T> cadastroAttUser();
	default ArrayList<T> listarUsuarios(String caminho) {
		ArrayList<T> listaPessoas = null;
		try {
			listaPessoas = ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.printf("Não há %ss na lista.", getClass().getSimpleName());
		}
		return listaPessoas;
	}
}
//pesquisar o T e generics, e se interface pode ser considerada como extends
/*
 * String caminho = null; if (classChamada instanceof Cliente) { caminho = ""; }
 * else if (classChamada instanceof Vendedor) { caminho = ""; } else { caminho =
 * ""; }
 */
