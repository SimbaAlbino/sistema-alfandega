package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;
import filtradores.Canais;
import utilidade.ModelagemFile;

public class Estoque implements Serializable {

	// ideia: ao iniciar as operações, todos os estoque desserializam os produtos,
	// ao selecionar a opção finalizar, os produtos são serializados.
	
	private static long totalProdutos; // Total de produtos no estoque

	private static final long serialVersionUID = 1L;

	private transient static String caminhoEstoqueproduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDadosProduto.txt";

	// Obtém o caminho do arquivo de estoque de produtos
	public static String getCaminhoEstoqueProduto() {
		return caminhoEstoqueproduto;
	}

	// Obtém o total de produtos no estoque
	public static long getTotalProdutosEstoque() {
		return totalProdutos;
	}
	
	// Adiciona um produto ao estoque
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
	
	// Remove um produto do estoque
	public synchronized static void removerProdutoEstoque(DadosProduto produto) {
	    ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
	    try {
	        // Verifica se o produto está na lista antes de tentar removê-lo
	        if (estoqueGeral.contains(produto)) {
	            estoqueGeral.remove(produto);
	            produto.setArmazenamentoAtual(Local.DESPACHE);
	            // Serializa a lista atualizada
	            ModelagemFile.serializar(getCaminhoEstoqueProduto(), estoqueGeral);
	            System.out.println("Produto removido do estoque com sucesso.");
	        } else {
	            System.out.println("Produto não encontrado no estoque.");
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao remover o produto do estoque: " + e.getMessage());
	    }
	}

	
	// Despacha um produto para fora do estoque
	public static void despacharProduto(DadosProduto produto) {
		try {
		    EstoqueDespache.addProduto(produto);
		    produto.setArmazenamentoAtual(Local.DESPACHE);
		    removerProdutoEstoque(produto);
		} catch (Exception e) {
		    System.out.println("Erro ao despachar o produto: " + e.getMessage());
		}
	}
	/*
	Canais canais;
	ArrayList<DadosProduto> att = new ArrayList<DadosProduto>();
	for (DadosProduto produto: listaProdutosEstoque()) {
		canais = new Canais(produto);
		canais.moldagemProduto();
		att.add(produto);
	}
	*/
	// a att do código será no começo e final da operação
	public static void atualizarSistema() {
		// aplicar um design bonito de carregando
		if (listaProdutosEstoque() != null) {
			ArrayList<DadosProduto> dp = listaProdutosEstoque();
			attCanaisFiltro(dp);
			ModelagemFile.serializar(getCaminhoEstoqueProduto(), dp);
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

	// Lista os produtos no estoque
	public static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = ModelagemFile.desserializar(getCaminhoEstoqueProduto());
		if (listaProdutos != null)
			totalProdutos = listaProdutos.size();
		else
			return new ArrayList<>();
		return listaProdutos;
	}
	
	// Busca um produto no estoque ou despache pelo ID usando busca binária
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

	public static void attCanaisFiltro(ArrayList<DadosProduto> lista) {
		// Estoque pode ter: fiscalizando, aguardando pagamento, pago, rejeitado
		// vencimentos no estoque
		for (DadosProduto produto: lista) {
			LocalDate dataOperacaoDeProduto = produto.getDataDeOperacao();
			LocalDate agora = LocalDate.now();
			Canais canais = new Canais(produto);
			switch (produto.getStatus()) {
			case FISCALIZANDO: {
				canais.moldagemProduto();
				
				//if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 2) {
		
			}
				break;
			case PAGO:
				canais.moldagemProduto();
				break;
			case REJEITADO:
				// primeiro if significa que o fornecedor não forneceu o documento em 7 dias
				if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 7) {
					produto.setRecado("Vencimento do prazo para documento rejeitado");
					despacharProduto(produto);
				} else if (produto.isDocumentos() == true) {
					produto.setStatus(StatusProduto.FISCALIZANDO);
					produto.setRecado(null);
				}
				break;
			case INEXISTENTE:
				// feito
				// Remover o produto caso a data de inexistência declarada pelo funcionario.
				// O código deveria mostrar 3 days between, mas para uso didático, coloquei 3
				if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 1) {
					removerProdutoEstoque(produto);
				}
				break;
			default:
				break;
			}
		}
		
	}

	public static void statusPago(DadosProduto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        // Carregar a lista geral de produtos
        ArrayList<DadosProduto> listaProdutos = listaProdutosEstoque();

        if (listaProdutos == null) {
            listaProdutos = new ArrayList<>();
        }

        boolean produtoEncontrado = false;

        // Procurar o produto na lista
        Iterator<DadosProduto> iterator = listaProdutos.iterator();
        while (iterator.hasNext()) {
            DadosProduto p = iterator.next();
            if (p.equals(produto)) {
                p.setStatus(StatusProduto.PAGO);
                p.setRecado(null);
                produtoEncontrado = true;
                break; // Produto encontrado e atualizado, podemos parar a busca
            }
        }

        if (produtoEncontrado) {
            // Salvar a lista atualizada de volta ao arquivo
            ModelagemFile.serializar(getCaminhoEstoqueProduto(), listaProdutos);
            System.out.println("Status do produto atualizado para PAGO com sucesso.");
        } else {
            System.out.println("Produto não encontrado na lista.");
        }
    }
}

