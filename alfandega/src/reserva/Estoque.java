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

	private static String caminhoEstoqueproduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDadosProduto.txt";

	public static String getCaminhoEstoqueProduto() {
		return caminhoEstoqueproduto;
	}

	public static long getTotalProdutosEstoque() {
		return totalProdutos;
	}

	public synchronized void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		estoqueGeral.add(produto);
		ModelagemFile.serializar(getCaminhoEstoqueProduto(), estoqueGeral);
		produto.setArmazenamentoAtual(Local.ESTOQUE);
		//
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
		for (DadosProduto produto : listaProdutosEstoque()) {
			attCanaisFiltro(produto);
		}
		EstoqueDespache.atualizarDespache();
		ModelagemFile.serializar(getCaminhoEstoqueProduto(), listaProdutosEstoque());
		
		
		int total = 50;
		for (int i = 0; i <= total; i++) {
            // Simulando um processo que leva tempo
            try {
                Thread.sleep(100); // Pausa por 100 milissegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Atualizando a barra de carregamento
            int progress = (i * 100) / total; // Calculando a porcentagem de progresso
            StringBuilder barra = new StringBuilder("[");
            for (int j = 0; j < total; j++) {
                if (j < i) {
                    barra.append("=");
                } else {
                    barra.append(" ");
                }
            }
            barra.append("] " + progress + "%");

            // Imprimindo a barra de carregamento
            System.out.print("\r" + barra.toString());
        }

        System.out.println("\nCarregamento concluído!");
	}

	public final static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		listaProdutos = ModelagemFile.desserializar(getCaminhoEstoqueProduto());
		totalProdutos = listaProdutos.size();
		return listaProdutos;
		// construir ou retornar
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
				dadoProduto.setNotaFiscal("NOTA DE PIRANGAGEM");
				despacharProduto(dadoProduto);
			}
			break;
		case FISCALIZANDO: {
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 3) {
				canais.moldagemProduto();
				// manda para canais
			}
		}
			break;
		case PAGO:
			canais.moldagemProduto();
			// manda para canais, o valor será verde
			break;
		case REJEITADO:
			// primeiro if significa que o fornecedor não forneceu o documento em 7 dias
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 7) {
				dadoProduto.setNotaFiscal("Vencimento do prazo para documento");
				despacharProduto(dadoProduto);
			} else if (dadoProduto.isDocumentos() == true) {
				dadoProduto.setStatus(StatusProduto.FISCALIZANDO);
			}
			break;
		case INEXISTENTE:
			// feito
			// Remover o produto caso a data de inexistência declarada pelo funcionario.
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 3) {
				removerProdutoEstoque(dadoProduto);
			}
			break;
		default:
			break;
		}
	}

	// ver se ainda precisa
	public void clienteSubtotal() {
		// subtotal de todos os produtos relacionados ao cliente, tanto o preço total de
		// compra, quanto o imposto aplicado para cada compra, a partir daqui será
		// possivel efetuar o pagamento.
	}

	public void verificarPagamento() {

	}
}
