package sistemaInterno;

public abstract class Impostos {
	protected String tipoImposto;
	protected double valorImposto;

	public Impostos(String tipoImposto, double valorImposto) {
		this.tipoImposto = tipoImposto;
		this.valorImposto = valorImposto;
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

}
