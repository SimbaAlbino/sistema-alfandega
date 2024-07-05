package aplicacao;

import java.util.Scanner;

import entidades.Fornecedor;
import entidades.Usuario;
import entidades.Utilizador;

public class Programa {
	final static MenuInicial[] choices = MenuInicial.values();
	final static MenuUser[] choicesUser = MenuUser.values();

	public static void main(String[] args) {
		// Obtém a solicitação do usuário (por exemplo, saldo zero, credor ou devedor)
		Scanner sc = new Scanner(System.in);

		MenuUser conta = choicesUser[AplicarMenu.getRequest(1)];

		while (conta != MenuUser.FINALIZAR) {

			if (conta == MenuUser.FINALIZAR) {
				break;
			}

			MenuInicial operacao = choices[AplicarMenu.getRequest(2)];

			while (operacao != MenuInicial.VOLTAR) {
				switch (operacao) {
				case RASTREAR:
					System.out.printf("%nRastreamento:%n");
					Utilizador.rastrearProdutos();
					break;
				case CADASTRAR:
					System.out.printf("%nCadastrar usuário no sistema:%n");
					System.out.println();
					Usuario.identificarCadastro(conta);
					// seleciona como deseja cadastrar: usuario, fornecedor
					// cadastrarCliente(); // cadastra, coloca nos arquivos e volta ao menu com o
					// break seguinte

					break;
				case LOGAR:
					System.out.printf("%nLogin:%n");
					Fornecedor pessoa = confirmarFornecedor();
					// seleciona como deseja entrar cliente, fornecedor ou funcionario
					// operações
					break;
				default:
					break;
				}
				operacao = choices[AplicarMenu.getRequest(2)]; // Obtém a solicitação do usuário
				// if T extends Utilizador else if Funcionario
				// operacao vai carregar a opcao de usuario
			}

			conta = choicesUser[AplicarMenu.getRequest(1)];
		}

		sc.close();
	}
	
	

}

/*
 * public static void operador(MenuUser status) {
		if (status.equals(MenuUser.CLIENTE)) {
			Cliente.operador();
		} else if (status.equals(MenuUser.FORNECEDOR)) {
			Fornecedor.operador();
		} else if (status.equals(MenuUser.FUNCIONARIO)) {
			Funcionario.operador();
		} else {
			System.out.println("Operador não encontrado");
		}
	}*/
