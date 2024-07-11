package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import aplicacao.MenuUser;
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
	
	public abstract void listarDividas();
	
	//passo a determinada lista para printar todos os produtos associados. tanto cliente, quanto fornecedor
	public void printarProdutos(ArrayList<DadosProduto> listaTodosProdutos) {
		System.out.println("Total de registros: " + listaTodosProdutos.size());
		System.out.println();
		System.out.println("🟡 indica que é preciso realizar ação para que o objeto seja encaminhado ao seu destino.");
		System.out.println("🔴 simboliza que o produto será retornado por expirar");
		System.out.println("⚫ mostra que o produto foi negado na alfândega e foi encaminhado para as autoridades.\n");
		System.out.printf("%s", "aviso", "id", "cpf vinculado", "situação atual", "data da situação");
		//recebendo uma lista já filtrada de todos produtos do cliente.
		for (DadosProduto dadoProduto : listaTodosProdutos) {
			// se não estiver no estoque, estará no despache
			System.out.println(dadoProduto);
		}
	}
	
	public void avisosCanal(ArrayList<DadosProduto> produtosListados) {
		// contador para o total de produtos
		long contador = 0;
		for (DadosProduto produto : produtosListados) {
			if (!(produto.getRecado() == null)) {
				System.out.printf("O produto: %s está %s - %s", produto.getTipoProduto(), produto.getStatus(),
						produto.getRecado());
				contador ++;
			}
		}
		System.out.println("Total de avisos: " + contador);
		System.out.println();
		if (contador == 0) {
			System.out.println("Você não possui avisos de urgência estendidos do produto.");
		}
		// Se o produto tiver no estoque e com a coloção amarela, verificar com o
		// funcionario, mensagem;
	}
	
	public abstract void cadastro();
	
	public static void identificarCadastro(MenuUser status) {
		System.out.println("Cadastrar:");
		System.out.print("Seu nome: ");
		String nome = sc.nextLine();
		System.out.print("Seu e-mail: ");
		String email = sc.next();
		System.out.print("Sua senha: ");
		String senha = sc.next();

		if (status.equals(MenuUser.CLIENTE)) {
			System.out.print("Seu cpf: ");
			String cpf = sc.next();
			Cliente pessoa = new Cliente(nome, email, senha, cpf);
			pessoa.cadastro();
		} else if (status.equals(MenuUser.FORNECEDOR)) {
			Fornecedor pessoa = new Fornecedor(nome, email, senha);
			pessoa.cadastro();
		} else if (status.equals(MenuUser.FUNCIONARIO)) {
			System.out.println("Um usuário só pode ser cadastrado por outro funcionário.");
		} else {
			System.out.println("Operador não encontrado");
		}
		// chamar o método cadastrarUser
	}
}
