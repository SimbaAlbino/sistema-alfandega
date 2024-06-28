package sistemaInterno;

public class ImpostoFixo extends Impostos {
	private final float iFixo = 50.0f; // Valor fixo do imposto

	// Construtor que recebe o produto ao qual o imposto será aplicado
	public ImpostoFixo(Produto objetoTaxa) {
		this.objetoTaxa = objetoTaxa;
	}

	// Implementação do método abstrato para calcular o valor do imposto fixo
	@Override
	public double impostoTotal() {
		return iFixo; // Retorna o valor fixo do imposto
	}
}