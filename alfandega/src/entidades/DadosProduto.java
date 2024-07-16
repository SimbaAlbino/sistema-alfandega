package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import reserva.Estoque;
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
	private String notaFiscal; // implementar
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
	
	//Default para o utilizador conseguir usar
	String getIconeStatus() {
	    if (this.getArmazenamentoAtual() == Local.ESTOQUE) {
	        switch (this.getStatus()) {
	            case REJEITADO: return "🔴";
	            case FISCALIZANDO: return "⚫";
	            case AGUARDANDO_PAGAMENTO: return "🟡";
	            default: return "";
	        }
	    } else if (this.getArmazenamentoAtual() == Local.DESPACHE) {
	        return "🟢";
	    }
	    return "";
	}

	// continuar
	public void editarRemessa(int caso) throws InterruptedException, InputMismatchException {
			switch (caso) {
			case 1:
				// tratar
				System.out.println("Alteração de documentos: ");
				System.out.print("A remessa possui documentação (s/n)? ");
				char bool = sc.next().toLowerCase().charAt(0);
				if (bool == 's') {
					this.documentos = true;
				} else if (bool == 'n') {
					this.documentos = false;
				} else {
					throw new InputMismatchException("Digite (s/n) para Sim/Não");
				}
				break;
			case 2:
				// tratar
				System.out.println("Alteração de notificações do produto: ");
				System.out.println("Digite o recado para o quadro de avisos | Pressione enter para remover aviso: ");
				String aviso = sc.nextLine();
				this.setRecado(aviso);
				break;
			case 3:
				// Mudar status de produto
				// tratar Urgente
				System.out.println("Alteração de status do produto: ");
				
				// logica para identificar onde o produto está:
				if (this.getArmazenamentoAtual() == Local.ESTOQUE) {
					System.out.println("Produto no estoque: ");
					
					System.out.println("INEXISTENTE, FISCALIZANDO;");
				} else {
					System.out.println("Produto no Despache: ");
					
					System.out.println("REJEITADO, RETORNADO, ENVIADO;");
				}
				break;
			case 4:
				Estoque.removerProdutoEstoque(this);
				System.out.println("Produto removido");
				Thread.sleep(2000);
				break;
			case 5:
				System.out.println("Saindo do sistema...");
				Thread.sleep(2000);
				break;
			}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, documentos, fornecedor, idRastreio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosProduto other = (DadosProduto) obj;
		return Objects.equals(cliente, other.cliente) && documentos == other.documentos
				&& Objects.equals(fornecedor, other.fornecedor) && Objects.equals(idRastreio, other.idRastreio);
	}

	@Override
	public String toString() {
	    return String.format("%d %s %s %s",
	        getIdRastreio(), getCliente().getCpf(), getStatus(), getDataDeOperacao());
	}
	
	  

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
/*
 * @Override public int compareTo(DadosProduto dadoProduto) { return
 * Integer.compare(this.idRastreio, dadoProduto.getIdRastreio()); }
 */
