package sistemaInterno;

import entidades.Cliente;
import entidades.DadosProduto;

public class TransacaoUser {

	public static boolean confirmarPagamento(Dividas divida, double valor, String metodo) {
		boolean sucesso = false;

		switch (metodo.toLowerCase()) {
		case "pix":
			sucesso = divida.pagarPorPix(valor);
			break;
		case "boleto":
			sucesso = divida.pagarPorBoleto(valor);
			break;
		default:
			System.out.println("Método de pagamento inválido.");
			break;
		}

		return true;
	}

	public static void adicionarDividaED(EstoqueDivida estoqueDivida, Cliente cliente, DadosProduto produto) {
		Dividas dividaExistente = estoqueDivida.encontrarDividaPorCliente(cliente);

		if (dividaExistente == null) {
			Dividas novaDivida = new Dividas(cliente);
			novaDivida.selecionarProduto(produto);
			estoqueDivida.addDividaFile(novaDivida);
		} else {
			dividaExistente.selecionarProduto(produto);
		}
	}
}
