package reserva;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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
		
	}
	
	public void clienteSubtotal() {
		//subtotal de todos os produtos relacionados ao cliente, decidir se é subtotal de imposto ou quantidade x preço unico.
	}
	
	public void verificarPagamento() {
		
	}
	
	
	public ArrayList<DadosProduto> buscarProdutosID(ArrayList<DadosProduto> lista, int code) {
		ArrayList<DadosProduto> produdtosUser;
		produtosUser = lista.stream().filter(x -> x.get)
	}
	
	public void buscarProdutosCPF(ArrayList<DadosProduto> lista) {
		//aplicar o equals
	}
	
	public void addStatusEnum() {
		// gostaria de herdar esse Status para o canal, para classificar onde o produto está no despache
	}
	
	public void limiteData() {
		
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
