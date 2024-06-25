package reserva;

import java.util.ArrayList;
import java.util.Objects;

import entidades.DadosProduto;

public class Estoque {
	
	private int idLista;
	
	protected ArrayList<DadosProduto> estoqueGeral = new ArrayList<>();

	public ArrayList<DadosProduto> getEstoqueGeral() {
		return estoqueGeral;
	}
	

	public void verificarRemessa() {
		
	}
	
	public void subtotalRemessa() {
		ArrayList<DadosProduto> valorProd = new ArrayList<>();
		
	}
	
	public void clienteSubtotal() {
		
	}
	
	public void verificarPagamento() {
		
	}
	
	public void addProduto(ArrayList<DadosProduto> lista) {
		
	}
	
	public void buscarProdutosID(ArrayList<DadosProduto> lista) {
		
	}
	
	public void buscarProdutosCPF(ArrayList<DadosProduto> lista) {
		
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
