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
		
	}
	
	public void verificarPagamento() {
		
	}
	
	
	public void buscarProdutosID(ArrayList<DadosProduto> lista) {
		
	}
	
	public void buscarProdutosCPF(ArrayList<DadosProduto> lista) {
		
	}
	
	public void serializar(String caminhoFile) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(estoqueGeral);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		return Objects.equals(estoqueGeral, other.estoqueGeral);
	}
	
}
