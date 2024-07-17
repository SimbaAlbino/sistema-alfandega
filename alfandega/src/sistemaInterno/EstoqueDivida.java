package sistemaInterno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public double calcularDespesaDoPedido() { // De cada produto do cliente
		double total = 0;
		for (Dividas divida : listaDividas()) {
			quantidade = divida.getDadoProduto().getTipoProduto().getQuantidade();
		}
		return total;
	}

	public void selecionarProduto(DadosProduto produto) { //
		Cliente cliente = produto.getCliente();
		ArrayList<Dividas> dividaExistente = encontrarDividaPorCliente(cliente);

		if (dividaExistente == null) {
			Dividas novaDivida = new Dividas(cliente);
			novaDivida.selecionarProduto(produto);
			listaDividas().add(novaDivida);
		} else {
			dividaExistente.selecionarProduto(produto);
		}
	}

	ArrayList<Dividas> encontrarDividaPorCliente(Cliente cliente) { //
		List<Dividas> dividas = listaDividas().stream().filter(x -> x.getClientela().equals(cliente))
				.collect(Collectors.toList()); // ArrayList<String> arrayList = new ArrayList<>(list);
		if (dividas != null) {
			ArrayList<Dividas> arrayDividas = new ArrayList<>(dividas);
			return arrayDividas;
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
