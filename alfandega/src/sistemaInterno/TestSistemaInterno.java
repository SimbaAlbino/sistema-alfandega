package sistemaInterno;

import entidades.Cliente;
import tiposProduto.Acessorios;
import tiposProduto.Produto;

public class TestSistemaInterno {

	public static void main(String[] args) {

		Banco banco = new Banco();

		Cliente cliente1 = new Cliente("Gabriel", "el.lopes@gmial.com", "212", "123.456.789-00");
		Cliente cliente2 = new Cliente("Pedro", "Pedro@yahho.com", "333", "023.22.135.23-00");

		// Adicionar clientes ao banco
		banco.adicionarCliente(cliente1);
		banco.adicionarCliente(cliente2);

		// Criar produtos para clientes
		Produto produto1 = new Acessorios(100.0, 2, cliente1); 
		Produto produto2 = new Acessorios(200.0, 1, cliente2);

		// Criar dívidas para clientes
		Dividas divida1 = new Dividas(cliente1);
		divida1.selecionarProduto(produto1);

		Dividas divida2 = new Dividas(cliente2);
		divida2.selecionarProduto(produto2);

		// Adicionar dívidas ao banco
		banco.adicionarDividaParaCliente(cliente1, divida1);
		banco.adicionarDividaParaCliente(cliente2, divida2);

		// Calcular saldo total e impostos
		banco.calcularSaldoTotal();
		banco.calcularImpostos();

		// Imprimir resultados
		System.out.println("Saldo total do banco: " + banco.getSaldoTotal());
		System.out.println("Total de ICMS arrecadado: " + banco.getIcmsTotal());
		System.out.println("Total de IPI arrecadado: " + banco.getIpiTotal());
		System.out.println("Total de Imposto Fixo arrecadado: " + banco.getImpostoFixoTotal());

		// Realizar pagamento via PIX para cliente1
		divida1.pagarPorPix(200.0);
		System.out.println("Saldo total após pagamento de cliente1: " + banco.getSaldoTotal());

		// Recalcular saldo total e impostos após pagamento
		banco.calcularSaldoTotal();
		banco.calcularImpostos();

		System.out.println("Saldo total do banco após pagamento: " + banco.getSaldoTotal());
		System.out.println("Total de ICMS arrecadado após pagamento: " + banco.getIcmsTotal());
		System.out.println("Total de IPI arrecadado após pagamento: " + banco.getIpiTotal());
		System.out.println("Total de Imposto Fixo arrecadado após pagamento: " + banco.getImpostoFixoTotal());
	}
}