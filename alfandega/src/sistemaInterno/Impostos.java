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
	protected static ICMS icms;
	protected static IPI ipi;
	protected static ImpostoFixo impostoFixo;
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
	public static double calcularImpostoGeral() {
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
	
	//corrigir
	public static void calcularImpostos() {
		icmsTotal = 0;
		ipiTotal = 0;
		impostoFixoTotal = 0;
		for (Dividas divida : estoqueDivida.getDividas()) {
			ICMS icms = new ICMS(divida.getMontante());
			double valorICMS = icms.calcularImpostoTotal();
			icmsTotal += valorICMS;

			IPI ipi = new IPI(divida.getMontante());
			double valorIPI = ipi.calcularImpostoTotal();
			ipiTotal += valorIPI;

			ImpostoFixo impostoFixo = new ImpostoFixo();
			double valorImpostoFixo = impostoFixo.calcularImpostoTotal();
			impostoFixoTotal += valorImpostoFixo;

			historicoImpostos.add(String.format("Cliente: %s | ICMS: %.2f | IPI: %.2f | Imposto Fixo: %.2f",
					divida.getClientela().getNome(), valorICMS, valorIPI, valorImpostoFixo));
		}
	}

	public DadosProduto getDadosProduto() {
		return dadosProduto;
	}

	public static ICMS getIcms() {
		return icms;
	}

	public static ImpostoFixo getImpostoFixo() {
		return impostoFixo;
	}

	public static Map<String, Double> getImpostosMap() {
		return impostosMap;
	}
}
