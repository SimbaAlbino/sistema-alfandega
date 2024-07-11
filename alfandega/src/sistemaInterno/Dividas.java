package sistemaInterno;

import java.util.ArrayList;
import java.util.List;
import entidades.Cliente;
import tiposProduto.Produto;

public class Dividas implements Pagamento {
	private Cliente clientela;
	private double montante;
	private List<Produto> produtos;
	private List<Divida> dividas;

	public Dividas(Cliente clientela) {
		this.clientela = clientela;
		this.produtos = new ArrayList<>();
		this.dividas = new ArrayList<>();
		this.montante = 0.0;
	}

	private double calcularDespesa() {
		double total = 0.0;
		for (Produto produto : produtos) {
			total += produto.getPrecoUnico();
		}
		return total;
	}

	@Override
	public boolean dividaPendente() {
		return montante > 0;
	}

	@Override
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

	@Override
	public boolean pagarPorPix(double valor) {
		return realizarPagamento(valor, "PIX");
	}

	@Override
	public boolean pagarPorBoleto(double valor) {
		return realizarPagamento(valor, "boleto");
	}

	private boolean realizarPagamento(double valor, String metodo) {
		if (valor < 0) {
			System.out.println("Valor indisponível para pagamento via " + metodo + ".");
			return false;
		}
		if (valor >= montante) {
			liberarDivida();
			return true;
		} else {
			montante -= valor;
			System.out
					.println("Pagamento via " + metodo + " de " + valor + " realizado. Montante restante: " + montante);
			return false;
		}
	}

	public void selecionarProduto(Produto produto) {
		produtos.add(produto);
		montante += produto.getPrecoUnico();
	}

	public String processoCalculo() {
		ICMS icms = new ICMS(montante);
		double valorICMS = icms.calcularImpostoTotal();

		IPI ipi = new IPI(montante);
		double valorIPI = ipi.calcularImpostoTotal();

		ImpostoFixo impostoFixo = new ImpostoFixo();
		double valorImpostoFixo = impostoFixo.calcularImpostoTotal();

		return "Cálculo de impostos:\n" + "ICMS: " + valorICMS + "\n" + "IPI: " + valorIPI + "\n" + "Imposto Fixo: "
				+ valorImpostoFixo;
	}

	public Cliente getClientela() {
		return clientela;
	}

	public double getMontante() {
		return montante;
	}

	private class Divida {
		private Cliente cliente;
		private double valor;
		private List<Produto> produtos;

		public Divida(Cliente cliente, double valor, List<Produto> produtos) {
			this.cliente = cliente;
			this.valor = valor;
			this.produtos = new ArrayList<>(produtos);
		}
	}
}
