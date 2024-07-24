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
import utilidade.ModelagemFile;

public class Banco implements Pagamento {
	private static Impostos imposto; // static?
	private static double saldoTotalBanco;
	private static List<String[]> historicoPagamentos;
	public static Map<String, Double> impostosMap = new HashMap<>();
	private transient static String caminhoBanco = "C:\\Users\\All members\\OneDrive\\Documentos\\clone\\sistema-alfandega\\files\\sistemaBanco\\banco.txt";
	
	static Scanner sc = new Scanner(System.in);

	public Banco() {
	}
	// provavelmente irei serializar tudo em uma array genérica

	public void setImpostos() {
		// alterar talvez
		Impostos.setBaseImposto(0.11, 0.20);
	}

	public static void saveDadosBanco() {
		DadosBanco dadosBanco = new DadosBanco(saldoTotalBanco, historicoPagamentos, impostosMap, imposto);
		try (FileOutputStream fileOut = new FileOutputStream(getCaminhoBanco());
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(dadosBanco);
			System.out.println("Dados do banco serializados com sucesso.");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void loadDadosBanco() {
		try (FileInputStream fileIn = new FileInputStream(getCaminhoBanco());
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			DadosBanco dadosBanco = (DadosBanco) in.readObject();
			saldoTotalBanco = dadosBanco.getSaldoTotalBanco();
			historicoPagamentos = dadosBanco.getHistoricoPagamentos();
			impostosMap = dadosBanco.getImpostosMap();
			imposto = dadosBanco.getImposto();
			System.out.println("Dados do banco deserializados com sucesso.");
		} catch (IOException | ClassNotFoundException i) {
			System.out.println("Falha na desserialização dos dados do banco: " + i.getMessage());
		}
	}

	public static void adicionarImposto(String chave, double valor) {
		impostosMap.put(chave, impostosMap.getOrDefault(chave, 0.0) + valor);
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

	public static void addHistoricoPagamento(String[] vetorImpostosCalc) {
		ArrayList<String[]> estoqueGeral = listaHistoricoPagamentos();
		estoqueGeral.add(vetorImpostosCalc);
		//
	}

	public static ArrayList<String[]> listaHistoricoPagamentos() {
		ArrayList<String[]> listaHistoricoPagamentos = ModelagemFile.desserializar(getCaminhoBanco());
		return listaHistoricoPagamentos;
	}

	public static void exibirHistoricoPagamentos() {
		System.out.println("\nHistórico de Pagamentos:");
		for (String[] registro : listaHistoricoPagamentos()) {
			Pagamento.printarDivida(registro);
			// recomendo dar uma a
			// registro será a lista enorme de pagamentos feitos
			// print do historico pagamentos);
		}
	}
	
	public static void operacaoFuncionario() {
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
	                try {
	                    System.out.println("Impostos: " + getImposto());
	                } catch (Exception e) {
	                    System.out.println("Erro ao obter impostos: " + e.getMessage());
	                    e.printStackTrace();
	                }
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


	// realizar pagamento de dividas
	public static void liberarPedido(Dividas divida) {
		if (divida != null && !divida.dividaPendente()) {
			System.out.println("Pedido liberado para o cliente: " + divida.getDadosProduto().getCliente().getNome());
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

	@Override
	public DadosProduto getDadosProduto() {
		// TODO Auto-generated method stub
		return null;
	}

}