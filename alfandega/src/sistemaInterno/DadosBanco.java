package sistemaInterno;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DadosBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	private double saldoTotalBanco;
	private List<String[]> historicoPagamentos;
	private Map<String, Double> impostosMap;

	public DadosBanco(double saldoTotalBanco, List<String[]> historicoPagamentos, Map<String, Double> impostosMap) {
		this.saldoTotalBanco = saldoTotalBanco;
		this.historicoPagamentos = historicoPagamentos;
		this.impostosMap = impostosMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getSaldoTotalBanco() {
		return saldoTotalBanco;
	}

	public List<String[]> getHistoricoPagamentos() {
		return historicoPagamentos;
	}

	public Map<String, Double> getImpostosMap() {
		return impostosMap;
	}

}