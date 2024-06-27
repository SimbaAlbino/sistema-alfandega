package entidades;

import java.util.Scanner;

public abstract class Utilizador {
	
	protected abstract void pagamento();
	
	protected DadosProduto rastrearProdutos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1 - Rastreio por CPF%n2 - Rastreio por Código");
		int opcao = sc.nextInt();
		if (opcao == 1) {
			//Estoque.buscarProdutosID
		} else {
			//Estoque.buscarProdutosCPF
		}
		//enum.codRastreio para saber onde está localizado em destaque no enum
	}
	
	protected abstract void cadastrarUser();
}
