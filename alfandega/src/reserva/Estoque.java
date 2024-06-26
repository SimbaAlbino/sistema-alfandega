package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class Estoque implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<DadosProduto> estoqueGeral = new ArrayList<>();

	public ArrayList<DadosProduto> getEstoqueGeral() {
		return estoqueGeral;
	}
	
	public void addProduto(DadosProduto produto) {
		this.estoqueGeral.add(produto);
	}

	public Double subtotalRemessa(DadosProduto produto) {
		return produto.getPrecoUni() * produto.getQuantidade();
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
	
	@SuppressWarnings("unchecked")
	public static ArrayList<DadosProduto> listaProdutosEstoque() {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = (ArrayList<DadosProduto>) ModelagemFile.desserializar(Cliente.getCaminhoClientesFile()); // CASTING DO CURINGA
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}
	
	public static DadosProduto buscarIDBinarySearch(Integer code) {
		//desserializarDoEstoque ou do Despache
		List<DadosProduto> produtosEstoque = Estoque.listaProdutosEstoque();
		produtosEstoque.sort((p1, p2) -> p1.getIdRastreio().compareTo(p2.getIdRastreio()));
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
	public static ArrayList<DadosProduto> buscarClientEquals(Cliente produtoCliente) {
		ArrayList<DadosProduto> produtosEncontrados = new ArrayList<>();
		for (DadosProduto dadoProduto : Estoque.listaProdutosEstoque()) {
			// se não estiver no estoque, estará no despache
			if (dadoProduto.getCliente().equals(produtoCliente)) {
				produtosEncontrados.add(dadoProduto);
			}
		}
		if (produtosEncontrados.size() == 0) {
			// tentar buscar no despache
			return null;
		}
		return produtosEncontrados;
	}
	
	//buscar produtos do fornecedor
	
	public void addStatusEnum() {
		// SE ESTIVER CLASSIFICADO COMO 
		// gostaria de herdar esse Status para o canal, para classificar onde o produto está no despache
		// localizar o produto por id no arquivo, esse método será constantemente chamdo por atualizar
	}
	
	public void limiteData(DadosProduto dadoProduto) {
		LocalDate chegadaProduto = dadoProduto.getDataChegada();
		LocalDate agora = LocalDate.now();
		if (ChronoUnit.DAYS.between(chegadaProduto, agora) > 31) {
			dadoProduto.setStatus(StatusProduto.valueOf("RETORNO"));
		}
		//mover produto e enviar para o despache
		//colocarStatusEnum Retorno	
		//se passar da data, chamar statusEnum
	}

	
	//aplicar o hascode e equals
	
}


//como serializei e desserializei
/*	public void serializar(String caminhoFile) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFile))) {
			oos.writeObject(this);
		} catch (IOException er) {
			System.out.println("Arquivo não encontrado na serialização: " + er.getMessage());
		} 
	}

	public Estoque desserializar(String caminhoFile) throws ClassNotFoundException {
		Estoque listaRetorno = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFile))) {
			listaRetorno = (Estoque) ois.readObject();
		} catch (IOException er) {
			System.out.println("Arquivo não encontrado na desserialização: " + er.getMessage());
		} catch (ClassNotFoundException er) {
			System.out.println("Exceção de Classe não encontrada na desserialização: " + er.getMessage());
		}
		return listaRetorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void buscarProdutosCpfNome(ArrayList<DadosProduto> lista, Cliente produtoCliente) {
		ArrayList<DadosProduto> listaProdutos = new ArrayList<>();
		try {
			listaProdutos = (ArrayList<DadosProduto>) ModelagemFile.desserializar(Cliente.getCaminhoClientesFile()); // CASTING DO CURINGA
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (DadosProduto dadoProduto : listaProdutos) {
			// se não estiver no estoque, estará no despache
			if (dadoProduto.getCliente().equals(produtoCliente)) {
				System.out.println(dadoProduto);
			}
		}
	}
 */
