package sistemaInterno;

import java.io.Serializable;

import entidades.DadosProduto;

public abstract class Impostos implements Serializable {

	private static final long serialVersionUID = 1L; //
	protected ICMS icms;
	protected IPI ipi;
	protected ImpostoFixo impostoFixo;
	protected DadosProduto dadosProduto; ///
	protected double precoUnico;
	
	

	public Impostos() {
	}

	public Impostos(double precoUnico) {
		this.precoUnico = precoUnico;
	}

	// Método abstrato que cada subclasse irá implementar
	public abstract void receberImpostos();
	//recebe impostos para o banco
	
	
	// Método para adicionar um imposto específico ao mapa -> vai acumular icms, ipi
	// e imposto Fixo (IMPOSTO TOTAL)

	public static String[] calcularImpostos(DadosProduto produto, int code) { // [pedro,2.5,8.5,2.9,somatotimposto]
		String[] historicoImposto;

		ICMS icms = new ICMS(produto.getTipoProduto().getPrecoUnico());
		double valorICMS = icms.impostoProduto();

		IPI ipi = new IPI(produto.getTipoProduto().getPrecoUnico());
		double valorIPI = ipi.impostoProduto();

		ImpostoFixo impostoFixo = new ImpostoFixo(produto);
		double valorImpostoFixo = impostoFixo.impostoProduto();

		double valorTotal = valorICMS + valorIPI + valorImpostoFixo;
		
		if (code == 0) {
			historicoImposto = new String[] { produto.getCliente().getNome(), String.format("%.2f", valorICMS),
					String.format("%.2f", valorIPI), String.format("%.2f", valorImpostoFixo),
					String.format("%.2f", valorTotal) };
			// historicoImpostos.add(registro); verificar
			return historicoImposto;
		} else if (code == 1) {
			icms.receberImpostos();
			ipi.receberImpostos();
			impostoFixo.receberImpostos();
			return null;
		}
		return null;
	}

	public static double[] getBaseImpostos() { // vetor
		double[] vetorImposto = new double[2];

		vetorImposto[0] = IPI.getTaxaIpi();
		vetorImposto[1] = ICMS.getTaxaIcms();

		return vetorImposto; // tem que retornar o vetor.
	}

	public static void setBaseImposto(double ipi, double icms) {
		IPI.setTaxaIpi(ipi);
		ICMS.setTaxaIcms(icms);
	}

	public DadosProduto getDadosProduto() {
		return dadosProduto;
	}

	public ICMS getIcms() {
		return icms;
	}

	public ImpostoFixo getImpostoFixo() {
		return impostoFixo;
	}

	@Override
	public String toString() {
		return "Impostos [icms=" + icms + ", ipi=" + ipi + "]";
	}
	
	

}
