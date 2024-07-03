package sistemaInterno;

public class ImpostoFixo extends Impostos {
	// Atributo final que representa a taxa de imposto fixo
	private final float iFixo = 0.05f; // Exemplo de valor

	// Implementação do cálculo de imposto fixo
	@Override
	public double impostoTotal() {
		return iFixo;
	}
}