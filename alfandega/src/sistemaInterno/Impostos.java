package sistemaInterno;

public abstract class Impostos {
	// Atributo protegido que representa o produto associado à taxa
	protected Produto ObjetoTaxa;

	// Atributo protegido que representa a taxa de impostos
	protected Impostos taxa;

	// Método abstrato para calcular o imposto total
	public abstract double impostoTotal();
}
