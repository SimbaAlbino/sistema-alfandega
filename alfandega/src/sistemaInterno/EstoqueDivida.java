package sistemaInterno;

import java.util.ArrayList;
import tiposProduto.Produto;
import reserva.Estoque;

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

	@Override
	public void selecionarProduto(Produto produto) {

	}

	public void addDividaFile(Dividas divida) {
		dividas.add(divida);
	}
}
