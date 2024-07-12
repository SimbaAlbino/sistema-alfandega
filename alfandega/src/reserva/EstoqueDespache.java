package reserva;

import java.io.Serializable;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.DadosProduto;

public class EstoqueDespache implements Serializable {
		
		private static final long serialVersionUID = 1L;
	private static String despacheCaminhoFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDespache.txt";
	
	public static String getDespacheCaminhoFile() {
		return despacheCaminhoFile;
	}

	public static ArrayList<DadosProduto> listaProdutosDespache() {
		return null;
	}
	
	public static ArrayList<DadosProduto> buscarClienteEquals(Cliente c1) {
		return null;
	}
	
	
	
	//retornado, rejeitado, enviado, inexistente.
}
