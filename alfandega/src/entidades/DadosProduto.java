package entidades;

import java.time.LocalDate;

import reserva.Produto;

public class DadosProduto {

	private int idRastreio;
	private Cliente cliente;
	private Vendedor vendedor;
	private Produto tipoItem;
	private boolean documentos;
	private String cep;
	private String endereço;
	private LocalDate dataChegada;

	// add construtor e getters

	public void editarRemessa(int caso) {
		// aplicar switch case 
	}
	
	//usar o equals e hashCode de acordo com a necessidade no futuro. em listar produtos precisamos encontrar por Cliente
}
