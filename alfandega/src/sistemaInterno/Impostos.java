package sistemaInterno;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import entidades.DadosProduto;

public abstract class Impostos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //
	protected ICMS icms;
	protected IPI ipi;
	protected ImpostoFixo impostoFixo;
	protected DadosProduto dadosProduto; ///

	public Impostos() {

	}

	// Map público e estático
	public static Map<String, Double> impostosMap = new HashMap<>();

	public Impostos(DadosProduto dadosProduto) {
		this.dadosProduto = dadosProduto;
	}

	// Método abstrato que cada subclasse irá implementar
	public abstract void receberImpostos();

	// Método para adicionar um imposto específico ao mapa -> vai acumular icms, ipi
	// e imposto Fixo (IMPOSTO TOTAL)
	public static void adicionarImposto(String chave, double valor) {
		impostosMap.put(chave, impostosMap.getOrDefault(chave, 0.0) + valor);
	}

	// Método para calcular o imposto total a partir do map
	public static double calcularImpostoTotal() {
		return impostosMap.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	public static void setBaseImposto(double ipi, double icms, double impostoFixo) {

		IPI.setTaxaIpi(ipi);
		ICMS.setTaxaIcms(icms);
		ImpostoFixo.setTaxaImpostoFixo(impostoFixo);
	}

	public static double[] baseImpostos() { // vetor
		double[] vetorImposto = new double[3];

		vetorImposto[0] = IPI.getTaxaIpi();
		vetorImposto[1] = ICMS.getTaxaIcms();
		vetorImposto[2] = ImpostoFixo.getTaxaImpostoFixo(); // Ja pode instanciar ja

		return vetorImposto; // tem que retornar o vetor.

	}

	// Método para detalhar os impostos
	public static String detalharImpostos() {
		StringBuilder detalhes = new StringBuilder();
		for (Map.Entry<String, Double> entry : impostosMap.entrySet()) {
			detalhes.append(String.format("%s: %.2f\n", entry.getKey(), entry.getValue()));
		}
		return detalhes.toString();
	}

	public DadosProduto getDadosProduto() {
		return dadosProduto;
	}

}
