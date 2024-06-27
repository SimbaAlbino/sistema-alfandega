package entidades;

import java.io.Serializable;
import java.time.LocalDate;

import tiposProduto.Produto;

public class DadosProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRastreio;
	private Cliente cliente;
	private Vendedor vendedor;
	private Produto tipoItem;
	private boolean documentos;
	private Endereco endereco;
	private LocalDate dataChegada;
	private StatusProduto status;

	// add construtor e getters

	public void editarRemessa(int caso) {
		// aplicar switch case 
	}
	
	//usar o equals e hashCode de acordo com a necessidade no futuro. em listar produtos precisamos encontrar por Cliente
}
