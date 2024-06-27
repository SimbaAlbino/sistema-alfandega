package entidades;

import java.time.LocalDate;

public class DadosProduto {

	private int idRastreio;
	private Cliente cliente;
	private Produto tipoItem;
	private boolean documentos;
	private String cep;
	private String endereço;
	private LocalDate dataChegada;

	public DadosProduto(int idRastreio, Cliente cliente, Produto tipoItem, boolean documentos, String cep,
			String endereço, LocalDate dataChegada) {
		this.idRastreio = idRastreio;
		this.cliente = cliente;
		this.tipoItem = tipoItem;
		this.documentos = documentos;
		this.cep = cep;
		this.endereço = endereço;
		this.dataChegada = dataChegada;
	}

	public int getIdRastreio() {
		return idRastreio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Produto getTipoItem() {
		return tipoItem;
	}

	public boolean isDocumentos() {
		return documentos;
	}

	public String getCep() {
		return cep;
	}

	public String getEndereço() {
		return endereço;
	}

	public LocalDate getDataChegada() {
		return dataChegada;
	}

	public void editarRemessa(int caso) {
		
	}

}
