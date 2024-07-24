package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;
import filtradores.Canais;
import utilidade.ModelagemFile;

public class Estoque implements Serializable {

	// ideia: ao iniciar as operações, todos os estoque desserializam os produtos,
	// ao selecionar a opção finalizar, os produtos são serializados.
	private static long totalProdutos;

	private static final long serialVersionUID = 1L;

	private transient static String caminhoEstoqueproduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDadosProduto.txt";

	public static String getCaminhoEstoqueProduto() {
		return caminhoEstoqueproduto;
	}

	public static long getTotalProdutosEstoque() {
		return totalProdutos;
	}

	public synchronized static void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		if (produto != null) {
		    estoqueGeral.add(produto);
		    produto.setArmazenamentoAtual(Local.ESTOQUE);
		    ModelagemFile.serializar(getCaminhoEstoqueProduto(), estoqueGeral);
		} else {
			System.out.println("Produto não foi estocado, pois está vazio.");
		}
	}

	public synchronized static void removerProdutoEstoque(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		estoqueGeral.remove(produto);
		ModelagemFile.serializar(getCaminhoEstoqueProduto(), estoqueGeral);
	}

	// o código já está atualizado
	public static void despacharProduto(DadosProduto produto) {
		EstoqueDespache.addProduto(produto);
		produto.setArmazenamentoAtual(Local.DESPACHE);
		removerProdutoEstoque(produto);
	}

	// a att do código será no começo e final da operação
	public static void atualizarSistema() {
		// aplicar um design bonito de carregando
		if (listaProdutosEstoque() != null) {
			for (DadosProduto produto : listaProdutosEstoque()) {
				attCanaisFiltro(produto);
			}
			ModelagemFile.serializar(getCaminhoEstoqueProduto(), listaProdutosEstoque());
		}
		EstoqueDespache.atualizarDespache();

		int totalSteps = 20; // Total de etapas da barra de carregamento

		System.out.print("Carregando Sistema: [");

		for (int i = 0; i < totalSteps; i++) {
			System.out.print(".");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("ERRO: " + e.getMessage());
			}
		}

		System.out.println("] Concluído!");
			
	}


	public static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = ModelagemFile.desserializar(getCaminhoEstoqueProduto());
		if (listaProdutos != null)
			totalProdutos = listaProdutos.size();
		else
			return null;
		return listaProdutos;
	}

	public static DadosProduto buscarIDBinarySearch(Integer code) {
		// Desserializar do Estoque e do Despache
		List<DadosProduto> produtosEstoque = listaProdutosEstoque();
		List<DadosProduto> produtosDespache = EstoqueDespache.listaProdutosDespache();

		// Ordenar as listas pelo ID de rastreio
		produtosEstoque.sort(Comparator.comparingInt(DadosProduto::getIdRastreio));
		produtosDespache.sort(Comparator.comparingInt(DadosProduto::getIdRastreio));

		// Buscar no estoque
		int indiceEstoque = Collections.binarySearch(produtosEstoque, new DadosProduto(code),
				Comparator.comparingInt(DadosProduto::getIdRastreio));

		// Se o produto foi encontrado no estoque, retornar
		if (indiceEstoque >= 0) {
			return produtosEstoque.get(indiceEstoque);
		}

		// Buscar no despache
		int indiceDespache = Collections.binarySearch(produtosDespache, new DadosProduto(code),
				Comparator.comparingInt(DadosProduto::getIdRastreio));

		// Se o produto foi encontrado no despache, retornar
		if (indiceDespache >= 0) {
			return produtosDespache.get(indiceDespache);
		}

		// Se não encontrado em nenhum dos dois, retornar null
		return null;
	}

	// Método para buscar produtos associados a um cliente
	public static ArrayList<DadosProduto> buscarClientEquals(Cliente clienteBusca) {
		ArrayList<DadosProduto> produtosEncontrados = new ArrayList<>();

		ArrayList<DadosProduto> produtosEstoque = Estoque.listaProdutosEstoque();
		ArrayList<DadosProduto> produtosDespache = EstoqueDespache.listaProdutosDespache();

		if (produtosEstoque != null) {
			for (DadosProduto dadoProduto : produtosEstoque) {
				if (clienteBusca.equals(dadoProduto.getCliente())) {
					produtosEncontrados.add(dadoProduto);
				}
			}
		}

		if (produtosDespache != null) {
			for (DadosProduto dadoProduto : produtosDespache) {
				if (clienteBusca.equals(dadoProduto.getCliente())) {
					produtosEncontrados.add(dadoProduto);
				}
			}
		}

		if (produtosEncontrados.isEmpty()) {
			System.out.println("Nenhum produto encontrado para o cliente especificado.");
		}

		return produtosEncontrados;
	}

	// buscar produtos do fornecedor

	public static void attCanaisFiltro(DadosProduto dadoProduto) {
		// Estoque pode ter: fiscalizando, aguardando pagamento, pago, rejeitado
		// vencimentos no estoque
		LocalDate dataOperacaoDeProduto = dadoProduto.getDataDeOperacao();
		LocalDate agora = LocalDate.now();
		Canais canais = new Canais(dadoProduto);
		switch (dadoProduto.getStatus()) {
		case AGUARDANDO_PAGAMENTO:
			// feito
			if (ChronoUnit.DAYS.between(dadoProduto.getDataDeOperacao(), LocalDate.now()) > 30) {
				dadoProduto.setStatus(StatusProduto.RETORNADO);
				dadoProduto.setDataDeOperacao(agora);
				dadoProduto.setRecado("Nota de pirangagem"); // pense
				// REMOVER PRODUTO DO ESTOQUE DE DIVIDA ADD EstoqueDividas.removeDivida
				despacharProduto(dadoProduto);
			}
			break;
		case FISCALIZANDO: {
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 2) {
				canais.moldagemProduto();
				// manda para canais
			}
		}
			break;
		case PAGO:
			canais.moldagemProduto();
			break;
		case REJEITADO:
			// primeiro if significa que o fornecedor não forneceu o documento em 7 dias
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 7) {
				dadoProduto.setRecado("Vencimento do prazo para documento rejeitado");
				despacharProduto(dadoProduto);
			} else if (dadoProduto.isDocumentos() == true) {
				dadoProduto.setStatus(StatusProduto.FISCALIZANDO);
				dadoProduto.setRecado(null);
			}
			break;
		case INEXISTENTE:
			// feito
			// Remover o produto caso a data de inexistência declarada pelo funcionario.
			// O código deveria mostrar 3 days between, mas para uso didático, coloquei 3
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 1) {
				removerProdutoEstoque(dadoProduto);
			}
			break;
		default:
			break;
		}
	}

	public static void statusPago(DadosProduto produto) {
		produto.setStatus(StatusProduto.PAGO);
		produto.setRecado(null);
	}
}
