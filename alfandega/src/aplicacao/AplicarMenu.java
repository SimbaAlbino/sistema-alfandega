package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
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
		case 3 :
			opcoes = Arrays.asList("Listar produtos associados a conta", "Fazer pagamento", "Dívidas", "Notificações de produto", "Sair da conta");
			break;
		case 4:
			opcoes = Arrays.asList("Listar produtos fornecidos","Cadastrar Produto","Fazer pagamento","Dívidas","Notificações de produto", "Sair da conta");
			break;
		case 5:
			opcoes = Arrays.asList("Editar Produto", "Editar Cliente/Fornecedor", "Editar Funcionario", "Operações de Banco e Pagamento", "Registrar Funcionário","Listar produtos do estoque", "Listar produtos despachados", "Sair da conta");
			break;
		case 6:
			opcoes = Arrays.asList("Documento do Produto", "Notificações do Produto", "Status do Produto", "Remoção do Produto");
			break;
		case 7:
			opcoes = Arrays.asList("Acessórios", "Automóveis", "Eletrodomésticos", "Ferramentas", "Informática", "Mobília", "Roupa");
			break;
		case 8:
			opcoes = Arrays.asList("Exibir saldo total do banco","Exibir histórico de pagamentos","Exibir valores gerais de impostos","Ler estoque de dívidas","Exibir impostos");
		default:
			throw new IllegalArgumentException("Opção inválida, tente novamente");
		}
		
		boolean entrada = false;
		int request = 4;
		short contador = 1;
		// Exibe opções de solicitação
		for (String opcao : opcoes) {
			System.out.printf("%d - %s\n", contador++, opcao);
		}
		while (!entrada) {
			try {
				do { // Insere a solicitação de usuário
					System.out.print("\n-> ");
					String digito = input.nextLine().trim();
					if (digito.isEmpty()) {
						throw new IllegalArgumentException("Valor nulo lançado");
					}
					request = Integer.parseInt(digito);
					clearScreen();
					if (request < 1 || request > opcoes.size()) {
						System.out.println("Valor fora dos parâmetros, digite entre 1 e " + opcoes.size());
					}
				} while ((request < 1) || (request > opcoes.size()));
				clearScreen();
				entrada = true;
			} catch (InputMismatchException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				input.nextLine();
			}
		}
		return request; // Retorna o valor enum da opção
	}
	
	 // Retorna o valor enum da opção
		//pesquisar como pegar 2 tipos de erros em um código entre parÊnteses()
	
	public static void clearScreen() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void titulo() {
		System.out.println("    ___    __    _________    _   ______  _______________ \r\n"
				+ "   /   |  / /   / ____/   |  / | / / __ \\/ ____/ ____/   |\r\n"
				+ "  / /| | / /   / /_  / /| | /  |/ / / / / __/ / / __/ /| |\r\n"
				+ " / ___ |/ /___/ __/ / ___ |/ /|  / /_/ / /___/ /_/ / ___ |\r\n"
				+ "/_/  |_/_____/_/   /_/  |_/_/ |_/_____/_____/\\____/_/  |_|\n");
		System.out.println("------------------------------------------------------------");
	}

	
}


