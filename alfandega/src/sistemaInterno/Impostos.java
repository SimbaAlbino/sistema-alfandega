package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

public abstract class Impostos {
	protected String tipoImposto;
	protected double valorImposto;
	protected List<Double> valoresRecolhidos;

	public Impostos(String tipoImposto, double valorImposto) {
		this.tipoImposto = tipoImposto;
		this.valorImposto = valorImposto;
		this.valoresRecolhidos = new ArrayList<>();
	}

	public abstract double calcularImpostoTotal();

	public String getTipoImposto() {
		return tipoImposto;
	}

	public void setTipoImposto(String tipoImposto) {
		this.tipoImposto = tipoImposto;
	}

	public double getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(double valorImposto) {
		this.valorImposto = valorImposto;
	}

	public void adicionarValorRecolhido(double valor) {
		valoresRecolhidos.add(valor);
	}

	public List<Double> getValoresRecolhidos() {
		return valoresRecolhidos;
	}
}
