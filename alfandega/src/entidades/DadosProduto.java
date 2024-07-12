package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import reserva.Local;
import reserva.StatusProduto;
import tiposProduto.Produto;

public class DadosProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idRastreio;
	private Cliente cliente;
	private Fornecedor fornecedor;
	private Produto tipoProduto;
	private boolean documentos;
	private Endereco endereco;
	private LocalDate dataDeOperacao = LocalDate.now();
	private StatusProduto status = StatusProduto.valueOf("FISCALIZANDO"); // VALOR INICIAL
	private String recado = null;
	private String notaFiscal; //implementar
	private Local armazenamentoAtual;
	// add construtor e getters

	Scanner sc = new Scanner(System.in);

	public DadosProduto(Cliente cliente, Fornecedor fornecedor, Produto tipoProduto, boolean documentos,
			Endereco endereco) {
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.tipoProduto = tipoProduto;
		this.documentos = documentos;
		this.endereco = endereco;
	}

	// Construtor para instanciar um objeto na busca do binarySearch -> Estoque
	public DadosProduto(Integer idRastreio) {
		this.idRastreio = idRastreio;
	}

	public Integer getIdRastreio() {
		return idRastreio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Produto getTipoProduto() {
		return tipoProduto;
	}

	public boolean isDocumentos() {
		return documentos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public LocalDate getDataDeOperacao() {
		return dataDeOperacao;
	}

	public void setDataDeOperacao(LocalDate dataDeOperacao) {
		this.dataDeOperacao = dataDeOperacao;
	}

	public StatusProduto getStatus() {
		return status;
	}

	public String getRecado() {
		return recado;
	}

	public void setStatus(StatusProduto status) {
		this.status = status;
	}

	// metodo abaixo usado pelo próprio sistema para adicionar status de produto.
	public void setRecado(String msg) {
		this.recado = msg;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Local getArmazenamentoAtual() {
		return armazenamentoAtual;
	}

	public void setArmazenamentoAtual(Local armazenamentoAtual) {
		this.armazenamentoAtual = armazenamentoAtual;
	}

	public void gerarIdRastreio() {
		Random random = new Random();
		int idGerado = random.nextInt(999999);
		// receber a lista de estoque e de despache e verificar se o id já existe
		this.idRastreio = idGerado;
	}

	// continuar
	public void editarRemessa(int caso) {
		switch (caso) {
		case 1:
			// tratar
			System.out.println("Alteração de documentos: ");
			System.out.print("A remessa possui documentação (s/n)? ");
			char bool = sc.next().charAt(0);
			if (bool == 's') {
				this.documentos = true;
			} else if (bool == 'n') {
				this.documentos = false;
			} else {
				System.out.println("Entrada inválida.");
			}
		case 2:
			// tratar
			System.out.println("Alteração de notificações do produto: ");
			System.out.println("Digite o recado para o quadro de avisos: ");
			String aviso = sc.nextLine();
			this.setRecado(aviso);

		case 3:
			// Mudar status de produto
			// tratar Urgente
			System.out.println("Alteração de status do produto: ");
			System.out.println("Selecione o status desejado: ");
			// logica para identificar onde o produto está:
			System.out.println("Produto no estoque: ");
			System.out.println("INEXISTENTE, CONGELADO, FISCALIZANDO;");

			//
		case 4:
			// deletar remessa
			System.out.println("Remoção de produto: ");
			// instanciar o estoque pois vai mexer e precisa serializar.
		}
	}

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
/*
 * @Override public int compareTo(DadosProduto dadoProduto) { return
 * Integer.compare(this.idRastreio, dadoProduto.getIdRastreio()); }
 */
