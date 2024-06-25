package sistemaInterno;

import java.util.ArrayList;

public abstract class Impostos {
	private Produto objetoTaxa;
	private float taxa;
	IPI ipiTaxa;
	
	ArrayList<? extends Impostos> listaImposto = new ArrayList<>();

	public Double impostoTotal() {
		
	}
	
	public abstract boolean atualizCatalogo();
}
