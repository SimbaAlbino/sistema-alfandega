package entidades;

import java.io.Serializable;
import java.time.LocalDate;

import reserva.StatusProduto;
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

	public DadosProduto(Cliente cliente, Vendedor vendedor, Produto tipoItem, boolean documentos,
			Endereco endereco, LocalDate dataChegada, StatusProduto status) {
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.tipoItem = tipoItem;
		this.documentos = documentos;
		this.endereco = endereco;
		this.dataChegada = dataChegada;
		this.status = status;
	}

	public int getIdRastreio() {
		return idRastreio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public Produto getTipoItem() {
		return tipoItem;
	}

	public boolean isDocumentos() {
		return documentos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public LocalDate getDataChegada() {
		return dataChegada;
	}

	public StatusProduto getStatus() {
		return status;
	}
	
	public void gerarIdRastreio() {
		
	}

	public void editarRemessa(int caso) {
		// aplicar switch case
	}

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
