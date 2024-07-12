package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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

	public static String getCaminhoEstoqueproduto() {
		return caminhoEstoqueproduto;
	}

	public static long getTotalProdutosEstoque() {
		return totalProdutos;
	}

	public static void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		estoqueGeral.add(produto);
		ModelagemFile.serializar(caminhoEstoqueproduto, estoqueGeral);
		totalProdutos++;
	}

	public static void removerProdutoEstoque(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		estoqueGeral.remove(produto);
		ModelagemFile.serializar(caminhoEstoqueproduto, estoqueGeral);
		totalProdutos--;
	}

	public static void despacharProduto(DadosProduto produto) {

	}

	public static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = ModelagemFile.desserializar(getCaminhoEstoqueproduto());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaProdutos;
		// construir ou retornar
	}

	// Adicionar para verificar no despache tambem
	public static DadosProduto buscarIDBinarySearch(Integer code) {
		// desserializarDoEstoque ou do Despache
		List<DadosProduto> produtosEstoque = new ArrayList<>();
		try {
			produtosEstoque = Estoque.listaProdutosEstoque();
			produtosEstoque.sort((p1, p2) -> p1.getIdRastreio().compareTo(p2.getIdRastreio()));
		} catch (NullPointerException e) {
			System.out.println("A lista de produtos está vazia" + e.getMessage());
		}
		// Collections.sort(produtosEstoque);
		// Usar comparator para ter uma ordem de itens personalizada
		int indice = Collections.binarySearch(produtosEstoque, new DadosProduto(code),
				(p1, p2) -> Integer.compare(p1.getIdRastreio(), p2.getIdRastreio()));
		// Também poderia usar o Comparator.comparingInt
		// Comparator.comparing -> perguntar como usar essa serialização ao professor.
		// Fazer um if para caso não encontre
		if (indice == 0) {
			// tentar buscar no despache
			return null;
		}
		return produtosEstoque.get(indice);
	}

	// Adicionar para verificar no despache tambem
	// Passando o método estático listaProdutosEstoque e um cliente
	public static ArrayList<DadosProduto> buscarClientEquals(Cliente clienteBusca) {
		ArrayList<DadosProduto> produtosEncontrados = new ArrayList<>();
		try {
			for (DadosProduto dadoProduto : Estoque.listaProdutosEstoque()) {
				// se não estiver no estoque, estará no despache
				if (dadoProduto.getCliente().equals(clienteBusca)) {
					produtosEncontrados.add(dadoProduto);
				}
			}
			if (produtosEncontrados.size() == 0) {
				// tentar buscar no despache
				return null;
			}
		} catch (NullPointerException e) {
			System.out.println("A lista de produtos está vazia");
		}
		return produtosEncontrados;
	}

	// buscar produtos do fornecedor

	public void attCanaisFiltro(DadosProduto dadoProduto) {
		// Estoque pode ter: fiscalizando, aguardando pagamento, pago, rejeitado
		//vencimentos no estoque
		LocalDate dataOperacaoDeProduto = dadoProduto.getDataDeOperacao();
		LocalDate agora = LocalDate.now();
		Canais canais = new Canais(dadoProduto);
		switch (dadoProduto.getStatus()) {
		case AGUARDANDO_PAGAMENTO:
			//feito
			if (ChronoUnit.DAYS.between(dadoProduto.getDataDeOperacao(), LocalDate.now()) > 30) {
				dadoProduto.setStatus(StatusProduto.RETORNADO);
				dadoProduto.setNotaFiscal("NOTA DE PIRANGAGEM");
				despacharProduto(dadoProduto);
			}
			break;
		case FISCALIZANDO: {
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 3) {
				canais.moldagemProduto();
				//manda para canais
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
			//feito
			// Remover o produto caso a data de inexistência declarada pelo funcionario.
			if (ChronoUnit.DAYS.between(dataOperacaoDeProduto, agora) > 3) {
				removerProdutoEstoque(dadoProduto);
			}
			break;
		default:
			break;
		}

		// mover produto e enviar para o despache
		// colocarStatusEnum Retorno
		// se passar da data, chamar statusEnum
	}

	public Double subtotalRemessa(DadosProduto produto) {
		return produto.getTipoProduto().getPrecoUnico() * produto.getTipoProduto().getPrecoUnico();
	}

	// a att do código será no começo e final da operação
	//urgente
	public void atualizarSistema() {
		//aplicar um design bonito de carregando
		for (DadosProduto produto : listaProdutosEstoque()) {
			attCanaisFiltro(produto);
		}
	}

	public void checkStatusEstoque(DadosProduto dadoProduto) {

		// verificar se a remessa está no estoque pelo buscarProdutosID, se não estiver,
		// passar para o despache, é aqui onde o cliente possui contato indireto.
	}

	// sem muita importância
	public void ordenarLista() {
		// pegar listaProdutosEstoque, fazer um sort de acordo com o Cliente, e
		// reescrever o arquivo serializando.
	}

	public void clienteSubtotal() {
		// subtotal de todos os produtos relacionados ao cliente, tanto o preço total de
		// compra, quanto o imposto aplicado para cada compra, a partir daqui será
		// possivel efetuar o pagamento.
	}

	public void verificarPagamento() {

	}

	/*
	 * public void liberarProduto(DadosProduto produtoDado) { // instancia o Canais
	 * Canais canalProduto = new Canais(produtoDado); switch (canalProduto.getCor())
	 * { case VERDE: ModelagemFile.moverArquivo(produtoDado); case AMARELO:
	 * ModelagemFile.moverArquivo(produtoDado); case VERMELHO:
	 * ModelagemFile.moverArquivo(produtoDado); case CINZA:
	 * ModelagemFile.moverArquivo(produtoDado);
	 * 
	 * }
	 * 
	 * }
	 */
}
