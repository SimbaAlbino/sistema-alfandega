package sistemaInterno;

import entidades.DadosProduto;

public interface Pagamento {

	DadosProduto getDadosProduto();

	static void printarDivida(String[] registro) {
		System.out.printf("Cliente: %s | ICMS: %s | IPI: %s | Imposto Fixo: %s | Total: %s ", registro[0],
				registro[1], registro[2], registro[3], registro[4]);
	}
}
