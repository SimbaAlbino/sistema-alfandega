package aplicacao;

import java.util.Scanner;

import entidades.Utilizador;

public class Programa {
	final static MenuInicial[] choices = MenuInicial.values();
	
	public static void main(String[] args) {
		// Obtém a solicitação do usuário (por exemplo, saldo zero, credor ou devedor)
		Scanner sc = new Scanner(System.in);
		MenuInicial tipoConta = choices[AplicarMenu.getRequest()];

		while(tipoConta != MenuInicial.SAIR) {
			switch (tipoConta) {
				case RASTREAR:
					System.out.printf("%nRastreamento de Produto por ID:%n");
					Utilizador.rastrearProdutos();
					break;
				case CADASTRAR:
					System.out.printf("%nCadastrar usuário no sistema:%n");
					//seleciona como deseja cadastrar: usuario, fornecedor
					// cadastrarCliente(); // cadastra, coloca nos arquivos e volta ao menu com o break seguinte
					
					break;
				case LOGAR:
					System.out.printf("%nLogin:%n");
					//seleciona como deseja entrar cliente, fornecedor ou funcionario
					//operações
					break;
				default:
					break;
			}

			tipoConta = choices[AplicarMenu.getRequest()]; // Obtém a solicitação do usuário
		}
	}
}
