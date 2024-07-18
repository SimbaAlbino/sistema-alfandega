package sistemaInterno;

import entidades.DadosProduto;

public interface Pagamento {

	DadosProduto getDadosProduto();

	boolean dividaPendente();

	static void printarDivida(String[] registro) {
		System.out.printf("Cliente: %s | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f| Total: %.2f ", registro[0],
				registro[1], registro[2], registro[3], registro[4]);
	}
}
