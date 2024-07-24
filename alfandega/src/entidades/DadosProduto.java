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
	// Atributos da classe

	private Integer idRastreio; // ID de rastreio do produto
	private Cliente cliente; // Cliente associado ao produto
	private Fornecedor fornecedor; // Fornecedor associado ao produto
	private Produto tipoProduto; // Tipo de produto
	private boolean documentos; // Indica se o produto possui documentos
	private Endereco endereco; // Endereço associado ao produto
	private LocalDate dataDeOperacao = LocalDate.now();
	private StatusProduto status = StatusProduto.valueOf("FISCALIZANDO"); // STATUS INICIAL
	private String recado = null;
	private Local armazenamentoAtual;
	// add construtor e getters

	transient Scanner sc = new Scanner(System.in);

	public DadosProduto() {
		// TODO Auto-generated constructor stub
	}

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

	// Getters para os atributos da classe
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

	// Setter para a data de operação
	public void setDataDeOperacao(LocalDate dataDeOperacao) {
		this.dataDeOperacao = dataDeOperacao;
	}

	public StatusProduto getStatus() {
		return status;
	}

	public String getRecado() {
		return recado;
	}

	// Setter para o status do produto
	public void setStatus(StatusProduto status) {
		this.status = status;
	}

	// metodo abaixo usado pelo próprio sistema para adicionar status de produto.
	public void setRecado(String msg) {
		this.recado = msg;
	}

	public Local getArmazenamentoAtual() {
		return armazenamentoAtual;
	}

	// Setter para o local de armazenamento atual
	public void setArmazenamentoAtual(Local armazenamentoAtual) {
		this.armazenamentoAtual = armazenamentoAtual;
	}

	// Método para gerar um ID de rastreio aleatóri
	public void gerarIdRastreio() {
		Random random = new Random();
		int idGerado = random.nextInt(999999);
		// receber a lista de estoque e de despache e verificar se o id já existe
		this.idRastreio = idGerado;
	}

	// Default para o utilizador conseguir usar - ICONES
	String getIconeStatus() {
		if (this.getArmazenamentoAtual() == Local.ESTOQUE) {
			switch (this.getStatus()) {
			case REJEITADO:
				return "🔴" + "";
			case FISCALIZANDO:
				return "⚫";
			case AGUARDANDO_PAGAMENTO:
				return "🟡";
			default:
				return "";
			}
		} else if (this.getArmazenamentoAtual() == Local.DESPACHE) {
			return "🟢";
		}
		return "";
	}

	// continuar - Método para editar informações da remessa
	public void editarRemessa(int caso) throws InterruptedException, InputMismatchException {
		switch (caso) {
		case 1:
			boolean statusOp = false;
			while (!statusOp) {
				try {
					System.out.println("Alteração de documentos: ");
					System.out.print("A remessa possui documentação (s/n)? ");
					char bool = sc.next().toLowerCase().charAt(0);
					sc.nextLine();
					if (bool == 's') {
						this.documentos = true;
						statusOp = true;
					} else if (bool == 'n') {
						this.documentos = false;
						statusOp = true;
					} else {
						throw new IllegalArgumentException("Opção inválida, tente novamente");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Ocorreu um erro: " + e.getMessage());
				}
			}
			break;
		case 2:
			System.out.println("Alteração de notificações do produto: ");
			System.out.println("Digite o recado para o quadro de avisos | Pressione enter para remover aviso: ");
			String aviso = sc.nextLine();
			if (aviso.isEmpty()) {
				this.setRecado(null);
			}
			this.setRecado(aviso);
			break;
		case 3:
			System.out.println("Alteração de status do produto: \n");
			boolean statusInput = false;
			// Lógica para identificar onde o produto está:
			if (this.getArmazenamentoAtual() == Local.ESTOQUE) {
				System.out.println("Produto no estoque: ");
				System.out.println("1 - Status para Inexistente\n2 - Status para Fiscalizando");

				while (!statusInput) {
					try {
						System.out.print("Selecione uma opção: ");
						int opcao = sc.nextInt();
						sc.nextLine(); // Limpar o buffer

						switch (opcao) {
						case 1:
							this.setStatus(StatusProduto.INEXISTENTE);
							statusInput = true;
							break;
						case 2:
							this.setStatus(StatusProduto.FISCALIZANDO);
							statusInput = true;
							break;
						default:
							System.out.println("Opção inválida, tente novamente.");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Por favor, insira um número.");
						sc.nextLine();
					}
				}

				System.out.println("Status alterado, pressione Enter para voltar");
				sc.nextLine();
			} else {
				System.out.println("Produto no Despache: ");
				System.out.println("1 - Status para Rejeitado\n2 - Status para Retornado\n3 - Status para Enviado\n");
				while (!statusInput) {
					try {
						System.out.print("Selecione uma opção: ");
						int opcao = sc.nextInt();
						sc.nextLine();

						switch (opcao) {
						case 1:
							this.setStatus(StatusProduto.REJEITADO);
							statusInput = true;
							break;
						case 2:
							this.setStatus(StatusProduto.RETORNADO);
							statusInput = true;
							break;
						case 3:
							this.setStatus(StatusProduto.ENVIADO);
							statusInput = true;
							break;
						default:
							System.out.println("Opção inválida, tente novamente.");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Por favor, insira um número.");
						sc.nextLine();
					}
				}

				System.out.println("Status alterado, pressione Enter para voltar");
				sc.nextLine();
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
		default:
			throw new InputMismatchException("valor fora de alcance");
		}

	}

	// Método hashCode para geração de hash baseado em cliente, documentos,
	// fornecedor e ID de rastreio
	@Override
	public int hashCode() {
		return Objects.hash(cliente, documentos, fornecedor, idRastreio);
	}

	// Método equals para comparação de objetos DadosProduto
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

	// Método toString para retornar uma string representando o objeto DadosProduto
	@Override
	public String toString() {
		return String.format("%d %s %s %s", getIdRastreio(), getCliente().getCpf(), getStatus(), getDataDeOperacao());
	}

	// usar o equals e hashCode de acordo com a necessidade no futuro. em listar
	// produtos precisamos encontrar por Cliente
}
