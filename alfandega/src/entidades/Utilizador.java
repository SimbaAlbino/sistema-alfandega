package entidades;

import java.util.Scanner;

public abstract class Utilizador {
	
	protected abstract void pagamento();
	
	protected abstract DadosProduto rastrearProdutos() {
		Scanner sc = new Scanner(System.in);
		int codRastreio = sc.nextInt();
		//enum.codRastreio
		
		
		
	}
}
