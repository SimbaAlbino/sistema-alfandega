package aplicacao;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicarMenu {
	
	//
	
	private static Scanner input = new Scanner(System.in);
	
	
	// Obtém a solicitação do usuário
	public static int getRequest(int vetorAtual) {
		List<String> opcoes = null;
		switch (vetorAtual) {
		case 1:
			opcoes = Arrays.asList("Cliente", "Fornecedor", "Funcionario", "Finalizar");
			break;
		case 2:
			opcoes = Arrays.asList("Rastrear um produto por id", "Cadastro", "Login", "Voltar");
			break;
		default:
			throw new IllegalArgumentException("Opção inválida, tente novamente");
		}
		
		int request = 1;
		short contador = 1;
		// Exibe opções de solicitação
		for (String opcao : opcoes) {
			System.out.printf("%d - %s\n", contador++, opcao);
		}
		try {
			do { // Insere a solicitação de usuário
				System.out.printf("%n-> ");
				request = input.nextInt();
			} while ((request < 1) || (request > opcoes.size() - 1));
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Invalid input. Terminating.");
		}
		return request - 1; // Retorna o valor enum da opção
	}
	
	 // Retorna o valor enum da opção
		//pesquisar como pegar 2 tipos de erros em um código entre parÊnteses()
}


