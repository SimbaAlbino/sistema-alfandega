package sistemaInterno;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class EstoqueDivida {

	private static long totalDividas;

	private static String caminhoBanco = "C:\\Users\\All members\\OneDrive\\Documentos\\clone\\sistema-alfandega\\files\\sistemaBanco\\banco.txt";

	public static ArrayList<Dividas> listaDividas() {
		ArrayList<Dividas> listaDividas = ModelagemFile.desserializar(getCaminhoBanco());
		totalDividas = listaDividas.size();
		return listaDividas;
	}

	public static void addDividas(Dividas dividas) {
		ArrayList<Dividas> estoqueGeral = listaDividas();
		estoqueGeral.add(dividas);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);
		//
	}

	public synchronized static void removerDividas(Dividas dividas) {
		ArrayList<Dividas> estoqueGeral = listaDividas();
		estoqueGeral.remove(dividas);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);

	}

	public void lerEstoqueDividas() {
		for (Dividas divida : listaDividas()) {
			System.out.println("Dívida de " + divida.getClientela().getNome() + ": " + divida.getMontante());
		}
	}

	public double calcularDespesa() {
		double total = 0;
		for (Dividas divida : listaDividas()) {
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
			listaDividas().add(novaDivida);
		} else {
			dividaExistente.selecionarProduto(produto);
		}
	}

	Dividas encontrarDividaPorCliente(Cliente cliente) {
		for (Dividas divida : listaDividas()) {
			if (divida.getClientela().equals(cliente)) {
				return divida;
			}
		}
		return null;
	}

	public void addDividaFile(Dividas divida) {
		listaDividas().add(divida);
	}

	public ArrayList<Dividas> getDividas() {
		return listaDividas();
	}

	public static long getTotalDividas() {
		return totalDividas;
	}

	public static String getCaminhoBanco() {
		return caminhoBanco;
	}

}
