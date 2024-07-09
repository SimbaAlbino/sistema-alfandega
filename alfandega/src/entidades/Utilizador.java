package entidades;

import java.util.Scanner;

import reserva.Estoque;
import reserva.StatusProduto;

public abstract class Utilizador <T> {
	
	static Scanner sc = new Scanner(System.in);
	protected abstract void pagamento();
		
	public static DadosProduto rastrearProdutos() {
		System.out.println("- Rastreio por Código");
		DadosProduto resultado;
		sc.nextLine();	
		System.out.println("Digite o ID de rastreio do produto: ");
		System.out.print("\n->");
		Integer idRastreio = sc.nextInt();
		resultado = Estoque.buscarIDBinarySearch(idRastreio);				
		if (resultado == null) {
			System.out.println("Produto: " + StatusProduto.INEXISTENTE);
		}
		//enum.codRastreio para saber onde está localizado em destaque no enum
		return resultado;
		//fazer print metodo static de utilidades.
	}

// o identificar serve para saber a quem está se referindo entre os
// utilizadores: cliente
// acho que coloca na interface

}
