package reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.DadosProduto;

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
		//subtotal de todos os produtos relacionados ao cliente, decidir se é subtotal de imposto ou quantidade x preço unico.
	}
	
	public void verificarPagamento() {
		
	}
	
	
	public DadosProduto buscarProdutosID(Integer code) {
		//desserializarDoEstoque ou do Despache
		List<DadosProduto> produtosEstoque = new ArrayList<>();
		Collections.sort(produtosEstoque);
		//Usar comparator para ter uma ordem de itens personalizada
		int indice = Collections.binarySearch(produtosEstoque, new DadosProduto(code), 
				(p1, p2) -> Integer.compare(p1.getIdRastreio(), p2.getIdRastreio()));
				//Também poderia usar o Comparator.comparingInt
				//Comparator.comparing -> perguntar como usar essa serialização ao professor.
				//Fazer um if para caso não encontre
		return produtosEstoque.get(indice);
	}
	
	public void buscarProdutosCPF(ArrayList<DadosProduto> lista) {
		//aplicar o equals
	}
	
	public void addStatusEnum() {
		// gostaria de herdar esse Status para o canal, para classificar onde o produto está no despache
		// localizar o produto por id no arquivo, esse método será constantemente chamdo por atualizar
	}
	
	public void limiteData(DadosProduto dadoProduto) {
		LocalDate chegadaProduto = dadoProduto.getDataChegada();
		LocalDate
				
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
 */
