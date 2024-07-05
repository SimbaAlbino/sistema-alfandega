package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import utilidade.ModelagemFile;

public interface Usuario<T> {

	void listarProdutos(ArrayList<DadosProduto> produtosFiltrados);

	boolean avisosCanal(DadosProduto produto);

	void confirmarUser(String[] dadosEntrada);

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

	default boolean existeUser(ArrayList<?> pessoas, T classChamada, String caminho) {
		try {
			pessoas = (ArrayList<?>) ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (pessoas.contains(classChamada)) {
			return true;
		}
		else
			return false;
	}

	default void listarUser(T classChamada, String caminho) {
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

	default String[] loginUser(T pessoas) {
		Scanner sc = null;
		String[] dados = new String[2];
		try {
			sc = new Scanner(System.in);
			System.out.println("Login:");
			System.out.print("E-mail: ");
			dados[0] = sc.next();
			System.out.println();
			System.out.println("Senha: ");
			dados[1] = sc.next();
		} catch (IllegalArgumentException e) {
			System.out.println("Erro por um argumento ilegal: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		return dados;
	}

	//cadastrarUser chama existeUser.
	//O método abaixo recebe uma lista atualizada para ser serializada, ele verifica se pode adicionar e assim o faz
	default void cadastrarUser(ArrayList<T> , boolean existe, String caminho) {
		if (existe) {
			System.out.println("Usuário já cadastrado no sistema.");
		} else {
			
			
			ModelagemFile.serializar(caminho, cadastroAttUser(caminho));
			System.out.println("Usuário cadastrado");
		}
	}

	ArrayList<T> cadastroAttUser(String caminho);
}
//pesquisar o T e generics, e se interface pode ser considerada como extends
/*
 * String caminho = null; if (classChamada instanceof Cliente) { caminho = ""; }
 * else if (classChamada instanceof Vendedor) { caminho = ""; } else { caminho =
 * ""; }
 */
