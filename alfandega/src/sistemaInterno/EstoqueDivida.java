package sistemaInterno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Cliente;
import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class EstoqueDivida {

	private static long totalDividas;

	private static String caminhoEstoqueDividas = "C:\\Users\\Pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDivida.txt";

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

	public synchronized static void removerDivida(Dividas dividas) {
		ArrayList<Dividas> estoqueGeral = listaDividas();
		estoqueGeral.remove(dividas);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);

	}

	public static void lerEstoqueDividas() {
		for (Dividas divida : listaDividas()) {
			System.out.println("Dívida de " + divida.getDadoProduto().getCliente().getNome() + ": " + divida.getMontante());
		}
	}
	
	//metodo para encontrar uma divida a partir de um produto
	public static Dividas encontrarDividaPorProduto(DadosProduto produto) { //corrigir este metodo
		//parecido com o de baixo
		return null;
	}

	//metodo para listar todas as dividas do cliente
	//quando o cliente quiser saber quais dividas tem
	public static ArrayList<Dividas> encontrarDividasPorCliente(Cliente cliente) { //
		List<Dividas> dividas = listaDividas().stream().filter(x -> x.getDadoProduto().getCliente().equals(cliente))
				.collect(Collectors.toList()); // ArrayList<String> arrayList = new ArrayList<>(list);
		if (dividas != null) {
			ArrayList<Dividas> arrayDividas = new ArrayList<>(dividas);
			return arrayDividas;
		}

		return null;
	}

	public static long getTotalDividas() {
		return totalDividas;
	}

	public static String getCaminhoBanco() {
		return caminhoEstoqueDividas;
	}
}
