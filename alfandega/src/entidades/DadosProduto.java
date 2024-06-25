package entidades;

import reserva.Estoque;

public class DadosProduto extends Estoque {
	
	private int idProduto;
	private Cliente individuoDados;
	private String cep;
	private boolean documentos;
	private Produto tipoItem;
	private int quantidade;
	private Double precoUni;
	
	public DadosProduto(int idProduto, Cliente individuoDados, String cep, boolean documentos, Produto tipoItem,
			int quantidade, Double precoUni) {
		super();
		this.idProduto = idProduto;
		this.individuoDados = individuoDados;
		this.cep = cep;
		this.documentos = documentos;
		this.tipoItem = tipoItem;
		this.quantidade = quantidade;
		this.precoUni = precoUni;
	}
	
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public Cliente getIndividuoDados() {
		return individuoDados;
	}

	public void setIndividuoDados(Cliente individuoDados) {
		this.individuoDados = individuoDados;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public boolean isDocumentos() {
		return documentos;
	}

	public void setDocumentos(boolean documentos) {
		this.documentos = documentos;
	}

	public Produto getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(Produto tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoUni() {
		return precoUni;
	}

	public void setPrecoUni(Double precoUni) {
		this.precoUni = precoUni;
	}

	public void editarRemessa() {
		
	}
}
