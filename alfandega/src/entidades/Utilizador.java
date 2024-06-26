package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import reserva.Estoque;
import reserva.StatusProduto;

public abstract class Utilizador {
	
	static Scanner sc = new Scanner(System.in);
	protected abstract void pagamento();
	
	
	public static ArrayList<DadosProduto> rastrearProdutos() {
		System.out.println("1 - Rastreio por CPF%n2 - Rastreio por Código");
		int opcao = sc.nextInt();
		ArrayList<DadosProduto> resultado = new ArrayList<>();
		if (opcao == 1) {
			System.out.println("Digite o nome do Cliente: ");
			String nome = sc.nextLine();
			System.out.println("Digite o CPF do Cliente: ");
			String cpf = sc.next();
			resultado = Estoque.buscarClientEquals(new Cliente(nome, cpf));
			
		} else if (opcao == 2) {
			System.out.println("Digite o ID de rastreio do produto: ");
			Integer idRastreio = sc.nextInt();
			resultado.add(Estoque.buscarIDBinarySearch(idRastreio));				
		} else {
			System.out.println("Produto: " + StatusProduto.INEXISTENTE);
		}
		//enum.codRastreio para saber onde está localizado em destaque no enum
		return resultado;
		//fazer printmetodo static de utilidades.
	}
	
	protected abstract void cadastrarUser();
	
	
}


