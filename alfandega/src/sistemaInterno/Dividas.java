package sistemaInterno;

import java.util.Random;
import java.util.Scanner;

import entidades.DadosProduto;
import reserva.Estoque;
import reserva.StatusProduto;

public class Dividas implements Pagamento {
	private double montante;
	private DadosProduto dadoProduto;
	private static final int SIZE = 21; // Tamanho do "QR code"
	private static Scanner sc = new Scanner(System.in);

	// ao declarar um produto como taxado, passar a dívida referente no montante -
	// confirmar
	// se a dívida não está no estoque de dívidas

	public Dividas(DadosProduto dadoProduto) {
		this.dadoProduto = dadoProduto;
		// colocar montante;
	}

	public void setMontante() {
		String[] montanteDivida = Impostos.calcularImpostos(getDadosProduto(), 0);
		this.montante = Double.parseDouble(montanteDivida[4]);
	}

	public double getMontante() {
		return montante;
	}

	// ver se precisa:

	@Override
	public DadosProduto getDadosProduto() {
		return dadoProduto;
	}

	public void pagar() {
		Banco.loadDadosBanco();
		// lá em cima eu instancio a divida no metodo pagar
		if (getDadosProduto().getStatus() == StatusProduto.AGUARDANDO_PAGAMENTO) {
			// considerando que a dívida já está no estoqueDividas
			metodoPagamento();
		} else {
			System.out.println("O produto não aguarda um pagamento, verifique o seu status.");
		}
	}

	// Método para pagar produto chamado pelo cliente ou fornecedor

	public void metodoPagamento() {
		// Chama o método que especifica o imposto pago e o imposto total (se existir)
		try {
			Pagamento.printarDivida(Impostos.calcularImpostos(getDadosProduto(), 0));
			System.out.println("Escolha o método de pagamento: ");
			System.out.println("1. PIX");
			System.out.println("2. Boleto");

			int metodo = sc.nextInt();
			sc.nextLine(); // Consumir a nova linha

			switch (metodo) {
			case 1:
				System.out.println("Pagamento via PIX selecionado.");
				char[][] qrCode = gerarPix();
				printPix(qrCode);
				break;
			case 2:
				System.out.println("Pagamento via Boleto selecionado.");
				String codigoBarras = generateCodigoBarras();
				printCodigoBarras(codigoBarras);
				break;
			default:
				System.out.println("Método de pagamento inválido.");
				return;
			}

			System.out.println("Deseja realizar o pagamento? (s/n)");
			char confirmacao = sc.nextLine().toLowerCase().charAt(0);

			if (confirmacao == 's') {
				confirmarPagamento(true);
			} else {
				System.out.println("Pagamento não autorizado.");
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao processar o pagamento: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Método para realizar o pagamento
	private void confirmarPagamento(boolean pago) {
		if (!pago) {
			System.out.println("Pagamento não confirmado, fim da operação de pagamento.");
		} else {
			Impostos.calcularImpostos(dadoProduto, 1);
			Banco.liberarPedido(this);
			Estoque.statusPago(getDadosProduto());
			Estoque.atualizarSistema();
			System.out.println("Pagamento confirmado.");
		}
	}

	@Override
	public boolean dividaPendente() {
		return montante != 0;
	}

	public boolean liberarDivida() {
		if (dividaPendente()) {
			montante = 0;
			System.out.println("Dívida paga com sucesso.");
			return true;
		} else {
			System.out.println("Não há dívida pendente.");
			return false;
		}
	}

	// Gera um "QR code" aleatório
	private static char[][] gerarPix() {
		char[][] qrCode = new char[SIZE][SIZE];
		Random random = new Random();

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				qrCode[i][j] = random.nextBoolean() ? '█' : ' ';
			}
		}

		return qrCode;
	}

	// Imprime o "QR code" no console
	private static void printPix(char[][] qrCode) {
		for (char[] row : qrCode) {
			for (char cell : row) {
				System.out.print(cell);
			}
			System.out.println();
		}
	}

	// Gera uma sequência de 44 dígitos para o código de barras do boleto
	private static String generateCodigoBarras() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 44; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	// Imprime a representação ASCII do código de barras
	private static void printCodigoBarras(String codigoBarras) {
		System.out.println("-------------------------------------------------");
		System.out.println("|                    CÓDIGO DE BARRAS                    |");
		System.out.println("-------------------------------------------------");

		for (int i = 0; i < codigoBarras.length(); i += 4) {
			String segment = codigoBarras.substring(i, Math.min(i + 4, codigoBarras.length()));
			for (char c : segment.toCharArray()) {
				if (c % 2 == 0) {
					System.out.print("█");
				} else {
					System.out.print(" ");
				}
			}
		}

		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Código: " + codigoBarras);
		System.out.println("-------------------------------------------------");
	}

	@Override
	public String toString() {
		return "Dividas: [ montante= " + getMontante() + ", cliente=" + getDadosProduto().getCliente().getNome() + "]";
	}

}
