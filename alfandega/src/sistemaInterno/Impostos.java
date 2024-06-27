package sistemaInterno;

import java.util.ArrayList;

import tiposProduto.Produto;

public abstract class Impostos {
	private Produto objetoTaxa;
	private float taxa;
	
	ArrayList<? extends Impostos> listaImposto = new ArrayList<>();

	public Double impostoTotal() {
		
	}
	
	public abstract boolean atualizCatalogo();
}
