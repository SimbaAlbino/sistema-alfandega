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
	private transient static String caminhoDespacheProduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDespache.txt";
	private static long totalProdutosDespache;

	// Obtém o total de produtos no despache
	public static long getTotalProdutosDespache() {
		return totalProdutosDespache;
	}

	// Obtém o caminho do arquivo de despache de produtos
	public static String getCaminhoDespacheProduto() {
		return caminhoDespacheProduto;
	}

	// Adiciona um produto ao despache
	public static void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = new ArrayList<>();
		if (listaProdutosDespache() != null) {
			produto.setArmazenamentoAtual(Local.DESPACHE);
			estoqueGeral = listaProdutosDespache();
		}
		estoqueGeral.add(produto);
		ModelagemFile.serializar(getCaminhoDespacheProduto(), estoqueGeral);
	}

	// Lista os produtos no despache
	public static ArrayList<DadosProduto> listaProdutosDespache() {
		ArrayList<DadosProduto> listaProdutos = ModelagemFile.desserializar(getCaminhoDespacheProduto());
		if (listaProdutos != null)
			totalProdutosDespache = listaProdutos.size();
		else
			return new ArrayList<>();
		return listaProdutos;
	}

	// Atualiza o despache, removendo produtos com mais de 30 dias
	public static void atualizarDespache() {
		ArrayList<DadosProduto> estoqueGeral = ModelagemFile.desserializar(getCaminhoDespacheProduto());
		if (estoqueGeral != null && !estoqueGeral.isEmpty()) {
			Iterator<DadosProduto> iterator = estoqueGeral.iterator();
			while (iterator.hasNext()) {
				DadosProduto produto = iterator.next();
				if (ChronoUnit.DAYS.between(produto.getDataDeOperacao(), LocalDate.now()) > 30) {
					iterator.remove();
				}
			}

			ModelagemFile.serializar(caminhoDespacheProduto, estoqueGeral);
		}
	}

}
