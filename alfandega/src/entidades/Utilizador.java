package entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import aplicacao.MenuUser;
import reserva.Estoque;
import reserva.StatusProduto;
import sistemaInterno.Dividas;
import sistemaInterno.EstoqueDivida;
import utilidade.ValidarDados;

public abstract class Utilizador<T> {

	static Scanner sc = new Scanner(System.in);

	public abstract void cadastro();

	public abstract void listarDividas();
	
	public abstract boolean equals(Object obj);
	
	public abstract int hashCode();

	public static void rastrearProdutos() {
		// definir como null pra conseguirmos retornar um parâmetro
		DadosProduto resultado = null;
		Integer idRastreio;
		boolean listagem = true;
		while (listagem) {
			try {
				System.out.println("Digite o ID de rastreio do produto: ");
				System.out.print("\n->");
				idRastreio = sc.nextInt();
				resultado = Estoque.buscarIDBinarySearch(idRastreio);
				if (!ValidarDados.validarIdProduto(idRastreio)) {
					throw new IllegalArgumentException("Erro, são necessários 6 dígitos para o ID");
				}
				if (resultado == null) {
					System.out.println("Produto: " + StatusProduto.INEXISTENTE);
				} else {
					ArrayList<DadosProduto> listaUnica = new ArrayList<>();
					listaUnica.add(resultado);
					// fazer print metodo static de utilidades.
					printarProdutos(listaUnica);
				}
				listagem = false;
			} catch (IllegalArgumentException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				sc.nextLine();
			}
			System.out.println("Pressione enter para voltar");
			sc.nextLine();
			sc.nextLine();
		}
	}

	public static void printarProdutos(ArrayList<DadosProduto> listaTodosProdutos) {
		System.out.println("Total de registros: " + listaTodosProdutos.size());
		System.out.println();
		System.out.println("🟡 - deve realizar pagamento.");
		System.out.println("🟢 - produto aprovado");
		System.out.println("🔴 - exige documento.");
		System.out.println("⚫ - fiscalizando\n");

		// Cabeçalhos das colunas
		String format = "%-8s %-20s %-15s %-20s %-20s";
		System.out.printf(format, "Aviso", "ID", "CPF Vinculado", "Situação Atual", "Data da Situação");
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		// Exibição dos produtos
		for (DadosProduto dadoProduto : listaTodosProdutos) {
			System.out.printf(format, dadoProduto.getIconeStatus(), dadoProduto.getIdRastreio(),
					dadoProduto.getCliente().getCpf(), dadoProduto.getStatus(), dadoProduto.getDataDeOperacao());
			System.out.println();
		}
	}

	public void avisosCanal(ArrayList<DadosProduto> produtosListados) {
		// contador para o total de produtos
		long contador = 0;
		if (produtosListados.isEmpty() || produtosListados == null) {
			System.out.println("\nNão há registro de produtos em seu nome\n");
		} else {
			for (DadosProduto produto : produtosListados) {
				if (!(produto.getRecado() == null)) {
					System.out.printf("O produto: %s está %s - %s", produto.getTipoProduto(), produto.getStatus(),
							produto.getRecado());
					contador++;
				}
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

	public static void identificarCadastro(MenuUser status) {
		boolean opCadastro = true;
		while (opCadastro) {
			try {
				System.out.println("Cadastrar:");
				System.out.print("Seu nome: ");
				String nome = sc.nextLine();
				if (!ValidarDados.validarNome(nome))
					throw new IllegalArgumentException("Nome inválido");
				System.out.print("Seu e-mail: ");
				String email = sc.next();
				if (!ValidarDados.validarEmail(email))
					throw new IllegalArgumentException("Email inválido");
				System.out.print("Sua senha: ");
				String senha = sc.next();
				if (!ValidarDados.validarSenha(senha)) {
					throw new IllegalArgumentException("senha inválida, precisa conter pelo menos 6 dígitos");
				}
				if (status.equals(MenuUser.CLIENTE)) {
					System.out.print("Seu cpf: ");
					String cpf = sc.next().replace(".", "").replace("-", "");
					if (!ValidarDados.validarCPF(cpf)) {
						throw new IllegalArgumentException("CPF inválido");
					}
					// testar sem - e .
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
				sc.nextLine();
				opCadastro = false;
			} catch (IllegalArgumentException e) {
				System.out.printf("Erro, argumento inválido, %s, reiniciando o cadastro.\n", e.getMessage());
			}
		}
	}

	protected void pagamento() {
		boolean continuar = true;
		while (continuar) {
			System.out.println("Informe o código do produto ou pressione Enter para sair: ");
			try {
				String input = sc.nextLine();

				// Verificar se o usuário pressionou Enter
				if (input.isEmpty()) {
					continuar = false;
					System.out.println("Saindo...");
					continue;
				}

				int code = Integer.parseInt(input);

				if (ValidarDados.validarIdProduto(code)) {
					try {
						DadosProduto produto = Estoque.buscarIDBinarySearch(code);
						if (EstoqueDivida.encontrarDivida(produto)) {
							Dividas div = new Dividas(produto);
							div.pagar();
						} else {
							System.out.println("O produto informado não está no estoque de dívidas.");
						}
					} catch (Exception e) {
						System.out.println("Erro ao buscar o produto: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					System.out.println("Código de produto inválido. O id deve ter 6 dígitos.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, informe um número.");
			} catch (Exception e) {
				System.out.println("Erro geral: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
