package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private List<Cliente> clientes;
	private EstoqueDivida estoqueDivida;
	private double saldoTotal;
	private double icmsTotal;
	private double ipiTotal;
	private double impostoFixoTotal;

	public Banco() {
		this.clientes = new ArrayList<>();
		this.estoqueDivida = new EstoqueDivida();
		this.saldoTotal = 0;
		this.icmsTotal = 0;
		this.ipiTotal = 0;
		this.impostoFixoTotal = 0;
	}

	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void adicionarDividaParaCliente(Cliente cliente, Dividas divida) {
		estoqueDivida.addDividaFile(divida);
	}

	public void calcularSaldoTotal() {
		saldoTotal = estoqueDivida.calcularDespesa();
	}

	public void calcularImpostos() {
		for (Dividas divida : estoqueDivida.getDividas()) {
			ICMS icms = new ICMS(divida.getMontante());
			icmsTotal += icms.calcularImpostoTotal();

			IPI ipi = new IPI(divida.getMontante());
			ipiTotal += ipi.calcularImpostoTotal();

			ImpostoFixo impostoFixo = new ImpostoFixo();
			impostoFixoTotal += impostoFixo.calcularImpostoTotal();
		}
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public double getIcmsTotal() {
		return icmsTotal;
	}

	public double getIpiTotal() {
		return ipiTotal;
	}

	public double getImpostoFixoTotal() {
		return impostoFixoTotal;
	}
}
