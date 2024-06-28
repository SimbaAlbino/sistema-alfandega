package aplicacao;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AplicarMenu {
	
	private static Scanner input = new Scanner(System.in);
	// Obtém a solicitação do usuário
	public static int getRequest() {
		int request = 4;
	
		// Exibe opções de solicitação
		System.out.printf("%nSelecione um dígito:%n%s%n%s%n%s%n%s%n",
				" 1 - Rastrear um produto por id",
				" 2 - Cadastro",
				" 3 - Login",
				" 4 - Sair");
	
		try {
			do { // Insere a solicitação de usuário
				System.out.printf("%n-> ");
				request = input.nextInt();
			} while ((request < 1) || (request > 4));
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Invalid input. Terminating.");
		}
		return request - 1; // Retorna o valor enum da opção
	}
}
