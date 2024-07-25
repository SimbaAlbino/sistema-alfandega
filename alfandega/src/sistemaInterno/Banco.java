package sistemaInterno;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import aplicacao.AplicarMenu;
import entidades.DadosProduto;

public class Banco implements Pagamento {
	private static Impostos imposto; // Objeto Impostos que gerencia as taxas de impostos
	private static double saldoTotalBanco; // Saldo total do banco
	private static List<String[]> historicoPagamentos; // Histórico de pagamentos
	public static Map<String, Double> impostosMap = new HashMap<>(); // Mapa para armazenar tipos de impostos e seus
																		// valores !
	private transient static String caminhoBanco = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\sistemaBanco\\banco.txt";

	static Scanner sc = new Scanner(System.in);

	public Banco() {
	}

	// Método para salvar os dados do banco (serialização)
	public static void saveDadosBanco() {
		DadosBanco dadosBanco = new DadosBanco(saldoTotalBanco, historicoPagamentos, impostosMap);
		try (FileOutputStream fileOut = new FileOutputStream(getCaminhoBanco());
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(dadosBanco);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// Método para carregar os dados do banco (desserialização)
	public static void loadDadosBanco() {
	    try (FileInputStream fileIn = new FileInputStream(getCaminhoBanco());
	         ObjectInputStream in = new ObjectInputStream(fileIn)) {
	        
	        DadosBanco dadosBanco = (DadosBanco) in.readObject();
	        
	        if (dadosBanco != null) {
	            saldoTotalBanco = dadosBanco.getSaldoTotalBanco();
	            historicoPagamentos = dadosBanco.getHistoricoPagamentos();
	            impostosMap = dadosBanco.getImpostosMap();
	        } else {
	            inicializarValoresPadrao();
	        }
	        
	    } catch (IOException e) {
	        inicializarValoresPadrao();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Classe não encontrada durante a desserialização: " + e.getMessage());
	        inicializarValoresPadrao();
	    } catch (Exception e) {
	        System.out.println("Erro inesperado: " + e.getMessage());
	        inicializarValoresPadrao();
	    }
	}

	private static void inicializarValoresPadrao() {
	    saldoTotalBanco = 0.0;  // Defina o valor padrão desejado
	    historicoPagamentos = new ArrayList<>();  // Supondo que seja uma lista
	    impostosMap = new HashMap<>();  // Supondo que seja um mapa
	    saveDadosBanco();
	}


	// Método para calcular o imposto total a partir do mapa de impostos
	public static void adicionarImposto(String chave, double valor) {
		loadDadosBanco();
		
		impostosMap.put(chave, impostosMap.getOrDefault(chave, 0.0) + valor);
		saveDadosBanco();
	}

	// Método para calcular o imposto total a partir do map
	public static double calcularImpostoGeral() {
		saldoTotalBanco = impostosMap.values().stream().mapToDouble(Double::doubleValue).sum();
		return saldoTotalBanco;
	}

	// Método para detalhar os impostos
	// para o banco usar ele vai mostrar ao funcionário o total recolhido
	// ipi, icms e imposto fixo
	public static String valoresImpostosGerais() {
		// jose icms para pagar: 2,50 ...
		StringBuilder detalhes = new StringBuilder();
		for (Map.Entry<String, Double> entry : impostosMap.entrySet()) {
			detalhes.append(String.format("%s: %.2f\n", entry.getKey(), entry.getValue()));
		}
		return detalhes.toString();
	}

	// [pedro,2.5,8.5,2.9,somatotimposto]

	// Método para adicionar o histórico de pagamentos
	public static void addHistoricoPagamento(String[] vetorImpostosCalc) {
		loadDadosBanco();
		ArrayList<String[]> estoqueGeral = listaHistoricoPagamentos();
		estoqueGeral.add(vetorImpostosCalc);
		saveDadosBanco();
		//
	}

	// Método para listar o histórico de pagamentos
	public static ArrayList<String[]> listaHistoricoPagamentos() {
		loadDadosBanco();
		ArrayList<String[]> listaHistoricoPagamentos = (ArrayList<String[]>) getHistoricoPagamentos();
		if (listaHistoricoPagamentos == null || listaHistoricoPagamentos.isEmpty()) {
			System.out.println("Não há pagamento no histórico.");
			return new ArrayList<>();
		}
		return listaHistoricoPagamentos;
	}

	// Método para exibir o histórico de pagamentos
	public static void exibirHistoricoPagamentos() {
		System.out.println("\nHistórico de Pagamentos:");
		for (String[] registro : listaHistoricoPagamentos()) {
			Pagamento.printarDivida(registro);
			// recomendo dar uma a
			// registro será a lista enorme de pagamentos feitos
			// print do historico pagamentos);
		}
	}

	// Método para executar operações do funcionário
	public void operacaoFuncionario() {
		loadDadosBanco();
		System.out.println("Selecione a operação:");

		int operacao = AplicarMenu.getRequest(8);

		try {
			switch (operacao) {
			case 1:
				try {
					System.out.println("Saldo total do banco: " + getSaldoTotalBanco());
				} catch (Exception e) {
					System.out.println("Erro ao obter saldo total do banco: " + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					exibirHistoricoPagamentos();
				} catch (Exception e) {
					System.out.println("Erro ao exibir histórico de pagamentos: " + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					Banco.valoresImpostosGerais();
				} catch (Exception e) {
					System.out.println("Erro ao exibir valores gerais de impostos: " + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 4:
				try {
					EstoqueDivida.lerEstoqueDividas();
				} catch (Exception e) {
					System.out.println("Erro ao ler estoque de dívidas: " + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("\nVoltando...");
				Thread.sleep(3000);
				break;
			default:
				System.out.println("Operação inválida.");
				break;
			}
		} catch (Exception e) {
			System.out.println("Erro geral: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Método para liberar o pedido de um cliente após pagamento
	public static void liberarPedido(Dividas divida) {
		if (divida != null && !divida.dividaPendente()) {
			System.out.println("Pedido liberado para o cliente: " + divida.getDadosProduto().getCliente().getCpf());
			EstoqueDivida.removerDivida(divida);
			saveDadosBanco();
		} else {
			System.out.println("Pedido não liberado. Dívida pendente ou produto não encontrado.");
		}
	}

	// metodo para serializar todos os componentes do banco e desserializar

	public static String getCaminhoBanco() {
		return caminhoBanco;
	}

	public static double getSaldoTotalBanco() {
		return saldoTotalBanco;
	}

	public static Impostos getImposto() {
		return imposto;
	}
	
	

	public static List<String[]> getHistoricoPagamentos() {
		return historicoPagamentos;
	}

	@Override
	// Método da interface Pagamento que retorna os dados do produto
	public DadosProduto getDadosProduto() {
		return null;
	}
}