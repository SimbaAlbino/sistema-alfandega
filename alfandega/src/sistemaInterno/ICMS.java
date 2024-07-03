package sistemaInterno;

public class ICMS extends Impostos {
	// Atributo final que representa a taxa de ICMS
	private final float icms = 0.18f; // Exemplo de valor

	// Implementação do cálculo de ICMS
	@Override
	public double impostoTotal() {
		return icms;
	}
}