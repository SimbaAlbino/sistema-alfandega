package aplicacao;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import entidades.Cliente;
import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.Usuario;
import entidades.Utilizador;
import reserva.Estoque;

public class Programa {

    // Instanciar um único Scanner
    final static MenuInicial[] choices = MenuInicial.values();
    final static MenuUser[] choicesUser = MenuUser.values();

    public static void main(String[] args) {
        /*
        // Código comentado para teste de moldagem de produto
        */
        
        // Configurar o Locale para US
        Locale.setDefault(Locale.US);

        // Atualizar o sistema de estoque
        Estoque.atualizarSistema();
        
        // Exibir o título do menu
        AplicarMenu.titulo();

        // Inicializar Scanner para entrada de dados do usuário
        Scanner sc = new Scanner(System.in);

        try {
            // Solicitar o tipo de conta do usuário
            MenuUser conta = choicesUser[AplicarMenu.getRequest(1) - 1];

            while (conta != MenuUser.FINALIZAR) {
                if (conta == MenuUser.FINALIZAR) {
                    break;
                }

                // Solicitar operação inicial do usuário
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
                        break;
                    default:
                        break;
                    }
                    AplicarMenu.clearScreen();
                    operacao = choices[AplicarMenu.getRequest(2) - 1]; // Obtém a solicitação do usuário
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
