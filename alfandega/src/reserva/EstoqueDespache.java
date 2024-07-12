package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class EstoqueDespache implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String caminhoDespacheProduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDespache.txt";
	private static long totalProdutosDespache;

	public static long getTotalProdutosDespache() {
		return totalProdutosDespache;
	}

	public static String getCaminhoDespacheProduto() {
		return caminhoDespacheProduto;
	}
	
	public static void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosDespache();
		estoqueGeral.add(produto);
		ModelagemFile.serializar(getCaminhoDespacheProduto(), estoqueGeral);
		//
	}

	public static ArrayList<DadosProduto> listaProdutosDespache() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		listaProdutos = ModelagemFile.desserializar(caminhoDespacheProduto);
		return listaProdutos;
	}

	
	public static void atualizarDespache() {
		ArrayList<DadosProduto> estoqueGeral = new ArrayList<>();
		estoqueGeral = ModelagemFile.desserializar(caminhoDespacheProduto);

		Iterator<DadosProduto> iterator = estoqueGeral.iterator();
		while (iterator.hasNext()) {
			DadosProduto produto = iterator.next();
			if (ChronoUnit.DAYS.between(produto.getDataDeOperacao(), LocalDate.now()) > 30) {
				iterator.remove();
			}
		}

		totalProdutosDespache = estoqueGeral.size();
		ModelagemFile.serializar(caminhoDespacheProduto, estoqueGeral);
	}
	
	
}
