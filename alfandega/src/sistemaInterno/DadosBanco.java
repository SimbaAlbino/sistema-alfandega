package sistemaInterno;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DadosBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String[]> historicoPagamentos;
	private Map<String, Double> impostosMap;

	public DadosBanco(List<String[]> historicoPagamentos, Map<String, Double> impostosMap) {
		this.historicoPagamentos = historicoPagamentos;
		this.impostosMap = impostosMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String[]> getHistoricoPagamentos() {
		return historicoPagamentos;
	}

	public Map<String, Double> getImpostosMap() {
		return impostosMap;
	}

}