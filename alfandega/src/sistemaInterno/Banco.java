package sistemaInterno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class Banco implements Pagamento {
	private static Impostos imposto; // static?
	private static double saldoTotalBanco;
	private static List<String[]> historicoPagamentos;
	public static Map<String, Double> impostosMap = new HashMap<>();
	private static String caminhoBanco = "C:\\Users\\All members\\OneDrive\\Documentos\\clone\\sistema-alfandega\\files\\sistemaBanco\\banco.txt";

	public Banco() {
	}
	//provavelmente irei serializar tudo em uma array genérica

	public static void adicionarImposto(String chave, double valor) {
		impostosMap.put(chave, impostosMap.getOrDefault(chave, 0.0) + valor);
		// serializar
	}

	// Método para calcular o imposto total a partir do map
	public static double calcularImpostoGeral() {
		return impostosMap.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	// Método para detalhar os impostos
	// para o banco usar ele vai mostrar ao funcionário o total recolhido
	public static String detalharImpostos() {
		// jose icms para pagar: 2,50 ...
		StringBuilder detalhes = new StringBuilder();
		for (Map.Entry<String, Double> entry : impostosMap.entrySet()) {
			detalhes.append(String.format("%s: %.2f\n", entry.getKey(), entry.getValue()));
		}
		return detalhes.toString();
	}

	// talvez tenha que setar os impostos, ver como vai salvar o ipi, icms e imposto
	// fixo

	// nos metodos abaixo, adicionamos ao banco, registros no formato:
	// [pedro,2.5,8.5,2.9,somatotimposto]

	public static void addHistoricoPagamento(String[] vetorImpostosCalc) {
		ArrayList<String[]> estoqueGeral = listaHistoricoPagamentos();
		estoqueGeral.add(vetorImpostosCalc);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);
		//
	}

	public static ArrayList<String[]> listaHistoricoPagamentos() {
		ArrayList<String[]> listaHistoricoPagamentos = ModelagemFile.desserializar(getCaminhoBanco());
		return listaHistoricoPagamentos;
	}

	public void calcularSaldoTotal() {
		saldoTotalBanco = 0;

		for (String[] pagamento : historicoPagamentos) {
			if (pagamento.length > 4) { // Verifica se há pelo menos 5 elementos no vetor
				try {
					double valor = Double.parseDouble(pagamento[4]);
					saldoTotalBanco += valor;
				} catch (NumberFormatException e) {
					System.out.println("Erro ao converter valor para double: " + e.getMessage());
				}
			}
		}
	}

	public static double getSaldoTotalBanco() {
		return saldoTotalBanco;
	}

	public void exibirHistoricoPagamentos() {
		System.out.println("\nHistórico de Pagamentos:");
		for (String[] registro : listaHistoricoPagamentos()) {
			printarDivida(registro);
			// recomendo dar uma a
			// registro será a lista enorme de pagamentos feitos
			// print do historico pagamentos);
		}
	}

	public static void operacaoFuncionario() {
		// exibirHistoricoPagamentos
		// get saldo total do banco
		// get informações staticas do imposto
	}

	// fazer
	// realizar pagamento de dividas
	public static void liberarPedido(Dividas divida) {
		if (divida != null && !divida.dividaPendente()) {
			System.out.println("Pedido liberado para o cliente: " + divida.getDadosProduto().getCliente().getNome());
			// fazer com que os valores de ipi, icms e imp fix sejam adicionados ao map
			EstoqueDivida.removerDivida(divida);

			// divida.getDadosProduto() colocar o status como pago em um método do produto
		} else {
			System.out.println("Pedido não liberado. Dívida pendente ou produto não encontrado.");
		}
	}
	
	//metodo para serializar todos os componentes do banco e desserializar

	// rever esse método
	public void attSaldoBanco() {
		ArrayList<Banco> listaBanco = new ArrayList<>();
		saldoTotalBanco = calcularImpostoGeral();
		listaBanco.add(this);
		ModelagemFile.serializar(getCaminhoBanco(), listaBanco);
	}

	public Impostos getImposto() {
		return imposto;
	}

	public static void setImposto(Impostos imposto) {
		Banco.imposto = imposto;
	}

	public static String getCaminhoBanco() {
		return caminhoBanco;
	}

	@Override
	public DadosProduto getDadosProduto() {
		return null;
	}

	@Override
	public boolean dividaPendente() {
		// TODO Auto-generated method stub
		return false;
	}

}

/*
 * public void executarTestes() {
 * System.out.println("Iniciando Testes do Banco...");
 * 
 * Cliente cliente1 = new Cliente("Gabriel", "el@gmail.com", "123",
 * "123.456.789-00"); Cliente cliente2 = new Cliente("Pedro", "pedro@yahoo.com",
 * "red", "987.654.321-00");
 * 
 * adicionarCliente(cliente1); adicionarCliente(cliente2);
 * 
 * Endereco endereco1 = new Endereco("12345-678", (short) 123); Fornecedor
 * fornecedor1 = new Fornecedor("Fornecedor 1", "fornecedor1@example.com",
 * "senha123");
 * 
 * Produto produto1 = new Acessorios(100.0, 2); Produto produto2 = new
 * Automoveis(50000.0, 1); Produto produto3 = new Acessorios(200.0, 1);
 * 
 * DadosProduto dadosProduto1 = new DadosProduto(cliente1, fornecedor1,
 * produto1, true, endereco1); DadosProduto dadosProduto2 = new
 * DadosProduto(cliente1, fornecedor1, produto2, true, endereco1); DadosProduto
 * dadosProduto3 = new DadosProduto(cliente2, fornecedor1, produto3, true,
 * endereco1);
 * 
 * Dividas divida1 = new Dividas(cliente1);
 * divida1.selecionarProduto(dadosProduto1);
 * divida1.selecionarProduto(dadosProduto2);
 * 
 * Dividas divida2 = new Dividas(cliente2);
 * divida2.selecionarProduto(dadosProduto3);
 * 
 * adicionarDividaParaCliente(cliente1, divida1);
 * adicionarDividaParaCliente(cliente2, divida2);
 * 
 * calcularSaldoTotal(); calcularImpostos();
 * 
 * System.out.println(String.
 * format("Saldo total: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
 * getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));
 * 
 * divida1.pagarPorPix(10000.0); System.out.println(String.
 * format("Saldo após pagamento parcial de cliente1: %.2f", getSaldoTotal()));
 * 
 * calcularSaldoTotal(); calcularImpostos();
 * 
 * System.out.println( String.
 * format("Saldo após pagamento parcial: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f"
 * , getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));
 * 
 * divida2.pagarPorBoleto(200.0); System.out.println(String.
 * format("Saldo após pagamento completo de cliente2: %.2f", getSaldoTotal()));
 * 
 * calcularSaldoTotal(); calcularImpostos();
 * 
 * System.out.println( String.
 * format("Saldo após pagamento completo: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f"
 * , getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));
 * 
 * divida1.pagarPorPix(divida1.getMontante()); System.out.println(String.
 * format("Saldo após pagamento completo de cliente1: %.2f", getSaldoTotal()));
 * 
 * calcularSaldoTotal(); calcularImpostos();
 * 
 * System.out.println( String.
 * format("Saldo após pagamento completo: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f"
 * , getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));
 * 
 * exibirHistoricoPagamentos(); exibirHistoricoImpostos(); }
 */
