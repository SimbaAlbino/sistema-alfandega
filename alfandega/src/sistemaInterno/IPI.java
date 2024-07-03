package sistemaInterno;

public class IPI extends Impostos {
	// Atributo final que representa a taxa de IPI
	private final float ipi = 0.04f; // Exemplo de valor

	// Implementação do cálculo de IPI
	@Override
	public double impostoTotal() {
		return ipi;
	}
}