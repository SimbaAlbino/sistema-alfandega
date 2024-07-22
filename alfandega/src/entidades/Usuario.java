package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import aplicacao.AplicarMenu;
import utilidade.ModelagemFile;

public interface Usuario<T> {

	static Scanner sc = new Scanner(System.in);

	ArrayList<DadosProduto> listarProdutos(ArrayList<DadosProduto> produtosEstoque, ArrayList<DadosProduto> produtosDespache );

	void operacoesUser();
	
	void removerUser(T pessoa);

	// cada usuario terá seu confirmarUser dentro de sua classe para a senha e email
	// serem restritos à classe.
	boolean confirmarUser(String[] dadosEntrada);

	default void apagarUser(String caminho, T classChamada) {
		ArrayList<?> pessoas = new ArrayList<>();
		pessoas = (ArrayList<?>) ModelagemFile.desserializar(caminho);
		if (pessoas.contains(classChamada)) {
			pessoas.remove(classChamada);
		}
		// ver como faz para remover uma linha do arquivo
		// act.serializar();
		// fazer em cada classe usuario, vendedor e funcionario umaa arraylist e chamar
		// esse método apagarUser
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
				sc.nextLine();
				if (confirmarUser(dados)) {
					break;
				} else {
					System.out.println("E-mail/Senha errados, tente novamente!");
					contador--;
				}
			} while (contador > 0);
			if (dados[0] == null || dados[1] == null) {
				throw new IllegalArgumentException(
						"Seus dados não correnspondem ao sistema. Tente novamente mais tarde.");
			} else {
				AplicarMenu.clearScreen();
				System.out.println("Bem-vindo ao Sistema Alfândega.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro por um argumento ilegal: " + e.getMessage());
		} 
		return dados;
	}

	

	// O método abaixo recebe uma lista atualizada para ser serializada, ele
	// verifica se pode adicionar e assim o faz
	default void condicaoCadastro(T classChamada, String caminho) {
		ArrayList<T> listaUsers = ModelagemFile.desserializar(caminho);
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

	String getNome();

	// alterar para funcionar com a serialização
	default void printarUsers(ArrayList<? extends Usuario<T>> lista) {
		// utilidade. desserializar();
		System.out.printf("Lista de todos os %s: %n", getClass().getSimpleName());
		try {
			int contador = 1;
			for (Usuario<T> user : lista) {
				System.out.printf("%d %s: %s%n", contador++, getClass().getSimpleName(), user.getNome());

			}
		} catch (NullPointerException e) {
			System.out.println("Erro ao listar o arquivo clientes: " + e.getMessage());
		}
	}

	// ArrayList<T> cadastroAttUser();
	default ArrayList<T> listarUsuarios(String caminho) {
		ArrayList<T> listaPessoas = null;
		try {
			listaPessoas = ModelagemFile.desserializar(caminho);
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
