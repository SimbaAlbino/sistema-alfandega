package entidades;

import java.util.ArrayList;

public interface Usuario {
	boolean rastrearProdutos();
	default void pagamento() {
	
	}
	void listarProdutos(); // arrumar
}
