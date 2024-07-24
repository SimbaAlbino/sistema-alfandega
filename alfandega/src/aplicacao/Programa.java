package aplicacao;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import entidades.Cliente;
import entidades.DadosProduto;
import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.Usuario;
import entidades.Utilizador;
import filtradores.Canais;
import reserva.Estoque;
import reserva.StatusProduto;
import sistemaInterno.Banco;
import utilidade.ModelagemFile;

public class Programa {

	// instanciar um unico scanner
	final static MenuInicial[] choices = MenuInicial.values();
	final static MenuUser[] choicesUser = MenuUser.values();

	public static void main(String[] args) {
		
		/*
		ArrayList<DadosProduto> dp = Estoque.listaProdutosEstoque();
		Canais canais = new Canais(dp.get(0));
		canais.moldagemProduto();
		ModelagemFile.serializar("C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDadosProduto.txt", dp);
		*/
		Locale.setDefault(Locale.US);

		Estoque.atualizarSistema();
		AplicarMenu.titulo();

		Scanner sc = new Scanner(System.in);
		
		Banco.exibirHistoricoPagamentos();

		try {

			MenuUser conta = choicesUser[AplicarMenu.getRequest(1) - 1];

			while (conta != MenuUser.FINALIZAR) {
				if (conta == MenuUser.FINALIZAR) {
					break;
				}

				MenuInicial operacao = choices[AplicarMenu.getRequest(2) - 1];

				while (operacao != MenuInicial.VOLTAR) {
					switch (operacao) {
					case RASTREAR:
						System.out.printf("%nRastreamento:%n");
						Utilizador.rastrearProdutos();
						break;
					case CADASTRAR:
						if (conta == MenuUser.FUNCIONARIO) {
							System.out.println("Apenas um funcionário pode cadastrar outro funcionário");
						} else {
							System.out.printf("%nCadastrar usuário no sistema:%n");
							System.out.println();
							Utilizador.identificarCadastro(conta);
						}
						// seleciona como deseja cadastrar: usuario, fornecedor
						// cadastrarCliente(); // cadastra, coloca nos arquivos e volta ao menu com o
						// break seguinte

						break;
					case LOGAR:

						Usuario<?> usuario = null;
						switch (conta) {
						case CLIENTE:
							usuario = new Cliente();
							break;
						case FORNECEDOR:
							usuario = new Fornecedor();
							break;
						case FUNCIONARIO:
							usuario = new Funcionario();
							break;
						default:
							throw new IllegalArgumentException("Tipo de usuário inválido");
						}
						 // Solicitar email e senha
	                    String[] dadosLogin = usuario.loginUser();
	                    
	                    // Confirmar usuário
	                    Usuario<?> usuarioAutenticado = (Usuario<?>) usuario.confirmarUser(dadosLogin);
	                    if (usuarioAutenticado != null) {
	                        usuarioAutenticado.operacoesUser();
	                    } else {
	                        System.out.println("Email ou senha inválidos.");
	                    }
					default:
						break;
					}
					AplicarMenu.clearScreen();
					operacao = choices[AplicarMenu.getRequest(2) - 1]; // Obtém a solicitação do usuário
					// operacao vai carregar a opcao de usuario
				}

				conta = choicesUser[AplicarMenu.getRequest(1) - 1];
			}

		} catch (NoSuchElementException e) {
			System.out.println("Nenhum token disponível: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("Erro: Scanner está fechado.");

		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		} finally {
			sc.close();
		}
	}

}