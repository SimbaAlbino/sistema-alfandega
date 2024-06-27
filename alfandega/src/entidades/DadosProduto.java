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

	public DadosProduto(int idRastreio, Cliente cliente, Vendedor vendedor, Produto tipoItem, boolean documentos,
			Endereco endereco, LocalDate dataChegada, StatusProduto status) {
		this.idRastreio = idRastreio;
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



	public void setIdRastreio(int idRastreio) {
		this.idRastreio = idRastreio;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Vendedor getVendedor() {
		return vendedor;
	}



	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}



	public Produto getTipoItem() {
		return tipoItem;
	}



	public void setTipoItem(Produto tipoItem) {
		this.tipoItem = tipoItem;
	}



	public boolean isDocumentos() {
		return documentos;
	}



	public void setDocumentos(boolean documentos) {
		this.documentos = documentos;
	}



	public Endereco getEndereco() {
		return endereco;
	}



	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}



	public LocalDate getDataChegada() {
		return dataChegada;
	}



	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}



	public StatusProduto getStatus() {
		return status;
	}



	public void setStatus(StatusProduto status) {
		this.status = status;
	}



	public void editarRemessa(int caso) {
		// aplicar switch case 
	}
	
	//usar o equals e hashCode de acordo com a necessidade no futuro. em listar produtos precisamos encontrar por Cliente
}
