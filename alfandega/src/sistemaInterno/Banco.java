package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;
import entidades.Endereco;
import entidades.Fornecedor;
import tiposProduto.Acessorios;
import tiposProduto.Automoveis;
import tiposProduto.Produto;

public class Banco {
	private double saldoTotal;
	private Impostos imposto;
// tem que acessar o estoque divida em estatico -> devido ao uso do seriarizable
	private List<String> historicoPagamentos;
	private List<String> historicoImpostos;
	
	private static String caminhoBanco = "C:\\Users\\All members\\OneDrive\\Documentos\\clone\\sistema-alfandega\\files\\sistemaBanco\\banco.txt";

	public Banco() {
		this.saldoTotal = 0;
		this.historicoPagamentos = new ArrayList<>();
		this.historicoImpostos = new ArrayList<>();
	}

	// Metodo liberarPedido - receber paaramentros(boolean - confirmando pagamento -
	// fazer em dividas,, mostrar o cliente, receberProduto, )

	// mostrar se eh verdadeiro, se for vai no estoque de divida -> lista -> busca o
	// cliente -> busca o item associado ao cliente pelo ID, comparar se o produto
	// eh igual ao outro
	// se for verdadero entao esta aprovado e retorna true. se for falso retor
	// sysout e falso.
	
	public static void liberarPedido(Dividas divida) { // corrigir
        if (divida != null && !divida.dividaPendente()) {
            System.out.println("Pedido liberado para o cliente: " + divida.getDadoProduto().getCliente());
            EstoqueDivida.removerDivida(divida);
        } else {
            System.out.println("Pedido não liberado. Dívida pendente ou produto não encontrado.");
        }
    }
	
	//metodo receber taxaProduto Banco.addInsumos() método que vai receber esse metodo de impostos calcularImpostoGeral(), serializar

	public void calcularSaldoTotal() {
		//calcular saldoBanco juntar o valor total icms, ipi, if, não é valor de uma compra, valor do maps do imposto
	}

	

	public void registrarPagamento(String metodo, Cliente cliente, double valor) {
		historicoPagamentos
				.add(String.format("Cliente: %s | Método: %s | Valor: %.2f", cliente.getNome(), metodo, valor));
	}

	public void exibirHistoricoPagamentos() { // cliente 
		System.out.println("\nHistórico de Pagamentos:");
		for (String registro : historicoPagamentos) {
			System.out.println(registro);
		}
	}

	public void exibirHistoricoImpostos() { // cliente pagou tais impostos
		System.out.println("\nHistórico de Impostos:");
		for (String registro : historicoImpostos) {
			System.out.println(registro);
		}
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public double getIcmsTotal() {
		return icmsTotal;
	}

	public double getIpiTotal() {
		return ipiTotal;
	}

	public double getImpostoFixoTotal() {
		return impostoFixoTotal;
	}

	
}

/*
 * public void executarTestes() {
		System.out.println("Iniciando Testes do Banco...");

		Cliente cliente1 = new Cliente("Gabriel", "el@gmail.com", "123", "123.456.789-00");
		Cliente cliente2 = new Cliente("Pedro", "pedro@yahoo.com", "red", "987.654.321-00");

		adicionarCliente(cliente1);
		adicionarCliente(cliente2);

		Endereco endereco1 = new Endereco("12345-678", (short) 123);
		Fornecedor fornecedor1 = new Fornecedor("Fornecedor 1", "fornecedor1@example.com", "senha123");

		Produto produto1 = new Acessorios(100.0, 2);
		Produto produto2 = new Automoveis(50000.0, 1);
		Produto produto3 = new Acessorios(200.0, 1);

		DadosProduto dadosProduto1 = new DadosProduto(cliente1, fornecedor1, produto1, true, endereco1);
		DadosProduto dadosProduto2 = new DadosProduto(cliente1, fornecedor1, produto2, true, endereco1);
		DadosProduto dadosProduto3 = new DadosProduto(cliente2, fornecedor1, produto3, true, endereco1);

		Dividas divida1 = new Dividas(cliente1);
		divida1.selecionarProduto(dadosProduto1);
		divida1.selecionarProduto(dadosProduto2);

		Dividas divida2 = new Dividas(cliente2);
		divida2.selecionarProduto(dadosProduto3);

		adicionarDividaParaCliente(cliente1, divida1);
		adicionarDividaParaCliente(cliente2, divida2);

		calcularSaldoTotal();
		calcularImpostos();

		System.out.println(String.format("Saldo total: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
				getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));

		divida1.pagarPorPix(10000.0);
		System.out.println(String.format("Saldo após pagamento parcial de cliente1: %.2f", getSaldoTotal()));

		calcularSaldoTotal();
		calcularImpostos();

		System.out.println(
				String.format("Saldo após pagamento parcial: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
						getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));

		divida2.pagarPorBoleto(200.0);
		System.out.println(String.format("Saldo após pagamento completo de cliente2: %.2f", getSaldoTotal()));

		calcularSaldoTotal();
		calcularImpostos();

		System.out.println(
				String.format("Saldo após pagamento completo: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
						getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));

		divida1.pagarPorPix(divida1.getMontante());
		System.out.println(String.format("Saldo após pagamento completo de cliente1: %.2f", getSaldoTotal()));

		calcularSaldoTotal();
		calcularImpostos();

		System.out.println(
				String.format("Saldo após pagamento completo: %.2f | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
						getSaldoTotal(), getIcmsTotal(), getIpiTotal(), getImpostoFixoTotal()));

		exibirHistoricoPagamentos();
		exibirHistoricoImpostos();
	}*/
