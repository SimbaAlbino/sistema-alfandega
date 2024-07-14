package sistemaInterno;

import java.util.ArrayList;
import entidades.Cliente;
import reserva.Estoque;
import entidades.DadosProduto;

public class EstoqueDivida extends Estoque {
	private ArrayList<Dividas> dividas;

	public EstoqueDivida() {
		this.dividas = new ArrayList<>();
	}

	public void lerEstoqueDividas() {
		for (Dividas divida : dividas) {
			System.out.println("Dívida de " + divida.getClientela().getNome() + ": " + divida.getMontante());
		}
	}

	public double calcularDespesa() {
		double total = 0;
		for (Dividas divida : dividas) {
			total += divida.getMontante();
		}
		return total;
	}

	public void selecionarProduto(DadosProduto produto) {
		Cliente cliente = produto.getCliente();
		Dividas dividaExistente = encontrarDividaPorCliente(cliente);

		if (dividaExistente == null) {
			Dividas novaDivida = new Dividas(cliente);
			novaDivida.selecionarProduto(produto);
			dividas.add(novaDivida);
		} else {
			dividaExistente.selecionarProduto(produto);
		}
	}

	Dividas encontrarDividaPorCliente(Cliente cliente) {
		for (Dividas divida : dividas) {
			if (divida.getClientela().equals(cliente)) {
				return divida;
			}
		}
		return null;
	}

	public void addDividaFile(Dividas divida) {
		dividas.add(divida);
	}

	public ArrayList<Dividas> getDividas() {
		return dividas;
	}
}
