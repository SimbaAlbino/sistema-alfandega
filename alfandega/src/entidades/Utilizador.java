package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import aplicacao.MenuUser;
import reserva.Estoque;
import reserva.StatusProduto;

public abstract class Utilizador <T> {
	
	static Scanner sc = new Scanner(System.in);
	protected abstract void pagamento();
	
	private static Scanner input = new Scanner(System.in);
	
	public static ArrayList<DadosProduto> rastrearProdutos() {
		System.out.println("1 - Rastreio por CPF\n2 - Rastreio por Código");
		System.out.print("\n->");
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
		//fazer print metodo static de utilidades.
	}
	
	//o identificar serve para saber a quem está se referindo entre os utilizadores: cliente 
	public T identificar(MenuUser status) {
		T pessoa = null;
		System.out.println("Cadastrar:");
		System.out.println("Seu nome: ");
		System.out.println("Seu e-mail: ");
		System.out.println("Sua senha: ");
		System.out.println("Seu cpf: ");
		
		if (status.equals(MenuUser.Cliente)) {
			pessoa = new Cliente
		}
		//chamar o método cadastrarUser
	}
	
	public abstract void cadastrarUser();
	
	
}


