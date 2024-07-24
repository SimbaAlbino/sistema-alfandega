package sistemaInterno;

public class ICMS extends Impostos {
	
	public static String TIPO_IMPOSTO = "ICMS";
	private  double taxaIcms = 0.020;
	private double precoUnico;

	public ICMS() {
	}

	public ICMS(double precoUnico) {
		this.precoUnico = precoUnico;
	}

	@Override
	public void receberImpostos(int qnt) {
		Banco.adicionarImposto(getTIPO_IMPOSTO(), impostoProduto() * qnt); // adicionando para o valor total.
	}

	public double impostoProduto() {
		return getPrecoUnico() * getTaxaIcms();
	}

	public static String getTIPO_IMPOSTO() {
		return TIPO_IMPOSTO;
	}

	public double getTaxaIcms() {
		return taxaIcms;
	}

	public void setTaxaIcms(double taxaIcms) {
		this.taxaIcms = taxaIcms;
	}
	
	public double getPrecoUnico() {
		return precoUnico;
	}
}
