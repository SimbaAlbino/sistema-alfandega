package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;

public class Dividas implements Pagamento {
	private double montante;
	private DadosProduto dadoProduto;
	private List<DadosProduto> produtos;

	public DadosProduto getDadoProduto() {
		return dadoProduto;
	}

	public Dividas(Cliente clientela, DadosProduto dadoProduto) {
		this.dadoProduto = dadoProduto;
		this.produtos = new ArrayList<>();
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

	public void selecionarProduto(DadosProduto produto) {
		produtos.add(produto);
		montante += produto.getTipoProduto().getPrecoUnico();
	}

	public String detalharCalculoImposto() {
		ICMS icms = new ICMS(dadoProduto);
		icms.receberImpostos(dadoProduto);

		IPI ipi = new IPI(dadoProduto);
		ipi.receberImpostos(dadoProduto);

		ImpostoFixo impostoFixo = new ImpostoFixo(dadoProduto);
		impostoFixo.receberImpostos(dadoProduto);

		return Impostos.detalharImpostos();
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
		private List<DadosProduto> produtos;

		public Divida(Cliente cliente, double valor, List<DadosProduto> produtos) {
			this.cliente = cliente;
			this.valor = valor;
			this.produtos = new ArrayList<>(produtos);
		}
	}
}
