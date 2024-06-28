package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

import reserva.StatusProduto;
import tiposProduto.Produto;

public class DadosProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idRastreio;
	private Cliente cliente;
	private Vendedor vendedor;
	private Produto tipoItem;
	private boolean documentos;
	private Endereco endereco;
	private LocalDate dataChegada = LocalDate.now();
	private StatusProduto status = StatusProduto.valueOf("ESTOQUE"); // VALOR INICIAL

	// add construtor e getters

	public DadosProduto(Cliente cliente, Vendedor vendedor, Produto tipoItem, boolean documentos, Endereco endereco,
			StatusProduto status) {
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.tipoItem = tipoItem;
		this.documentos = documentos;
		this.endereco = endereco;
		this.status = status;
	}

	//Construtor para instanciar um objeto na busca do binarySearch -> Estoque 
	public DadosProduto(Integer idRastreio) {
		this.idRastreio = idRastreio;
	}

	public Integer getIdRastreio() {
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

	public void setStatus(StatusProduto status) {
		this.status = status;
	}

	public void gerarIdRastreio() {
		Random random = new Random();
		int idGerado = random.nextInt(999999);
		//receber a lista de estoque e de despache e verificar se o id já existe
		this.idRastreio = idGerado;
		
	}

	public void editarRemessa(int caso) {
		// aplicar switch case
	}

	

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
/*
 * @Override
public int compareTo(DadosProduto dadoProduto) {
	return Integer.compare(this.idRastreio, dadoProduto.getIdRastreio());
}
*/
