package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Cliente;
import entidades.DadosProduto;
import entidades.Fornecedor;
import utilidade.ModelagemFile;

public class Estoque implements Serializable {
	
	//ideia: ao iniciar as operações, todos os estoque desserializam os produtos, ao selecionar a opção finalizar, os produtos são serializados.
	
	private static final long serialVersionUID = 1L;
		
	private static String caminhoEstoqueproduto = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDadosProduto.txt";

	public static String getCaminhoEstoqueproduto() {
		return caminhoEstoqueproduto;
	}
	
	public static void addProduto(DadosProduto produto) {
		ArrayList<DadosProduto> estoqueGeral = listaProdutosEstoque();
		estoqueGeral.add(produto);
		ModelagemFile.serializar(caminhoEstoqueproduto, estoqueGeral);
	}


	public static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = ModelagemFile.desserializar(getCaminhoEstoqueproduto()); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaProdutos;
		//construir ou retornar
	}
	
	// adicionar métodos de busca por Fornecedor e fornecedor-cliente
	
	public static DadosProduto buscarIDBinarySearch(Integer code) {
		//desserializarDoEstoque ou do Despache
		List<DadosProduto> produtosEstoque = new ArrayList<>();
		try {
			produtosEstoque = Estoque.listaProdutosEstoque();
			produtosEstoque.sort((p1, p2) -> p1.getIdRastreio().compareTo(p2.getIdRastreio()));
		} catch (NullPointerException e) {
			System.out.println("A lista de produtos está vazia" + e.getMessage());
		}
		//Collections.sort(produtosEstoque);
		//Usar comparator para ter uma ordem de itens personalizada
		int indice = Collections.binarySearch(produtosEstoque, new DadosProduto(code), 
				(p1, p2) -> Integer.compare(p1.getIdRastreio(), p2.getIdRastreio()));
				//Também poderia usar o Comparator.comparingInt
				//Comparator.comparing -> perguntar como usar essa serialização ao professor.
				//Fazer um if para caso não encontre
		if (indice == 0) {
			// tentar buscar no despache
			return null;
		}
		return produtosEstoque.get(indice);
	}
	
	//Passando o método estático listaProdutosEstoque e um cliente
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
	
	//buscar produtos do fornecedor
	
	
	public void limiteData(DadosProduto dadoProduto) {
		LocalDate chegadaProduto = dadoProduto.getDataChegada();
		LocalDate agora = LocalDate.now();
		if (ChronoUnit.DAYS.between(chegadaProduto, agora) > 31) {
			dadoProduto.setStatus(StatusProduto.valueOf("RETORNO"));// NÃO É RETORNO, É VERMELHO
		}
		//mover produto e enviar para o despache
		//colocarStatusEnum Retorno	
		//se passar da data, chamar statusEnum
	}
		
	public Double subtotalRemessa(DadosProduto produto) {
		return produto.getTipoProduto().getPrecoUnico() * produto.getTipoProduto().getPrecoUnico();
	}
	
	public void verificarRemessa() {
		//verificar se a remessa está no estoque pelo buscarProdutosID, se não estiver, passar para o despache, é aqui onde o cliente possui contato indireto.
	}
	
	public void clienteSubtotal() {
		//subtotal de todos os produtos relacionados ao cliente, tanto o preço total de compra, quanto o imposto aplicado para cada compra, a partir daqui será possivel efetuar o pagamento.
	}
	
	public void verificarPagamento() {
		
	}
	
	public void ordenarLista() {
		//pegar listaProdutosEstoque, fazer um sort de acordo com o Cliente, e reescrever o arquivo serializando.
	}	
	

	/*
	public void liberarProduto(DadosProduto produtoDado) {
		// instancia o Canais
		Canais canalProduto = new Canais(produtoDado);
		switch (canalProduto.getCor()) {
			case VERDE:
				ModelagemFile.moverArquivo(produtoDado);
			case AMARELO:
				ModelagemFile.moverArquivo(produtoDado);
			case VERMELHO:
				ModelagemFile.moverArquivo(produtoDado);
			case CINZA:
				ModelagemFile.moverArquivo(produtoDado);

		}
			
	}
	*/
}
