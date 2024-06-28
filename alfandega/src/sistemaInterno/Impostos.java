package sistemaInterno;

import java.util.ArrayList;

public abstract class Impostos {
	protected Produto objetoTaxa; // Produto ao qual o imposto será aplicado
	protected ArrayList<Impostos> taxa = new ArrayList<>(); // Lista de impostos

	// Método abstrato que deve ser implementado pelas subclasses afim de clcular os
	// impostos
	public abstract double impostoTotal();

	// Adiciona um imposto à lista de impostos
	public void addImposto(Impostos imposto) {
		taxa.add(imposto);
	}

	// Calcula o total de todos os impostos na lista
	public double calcularTotalImpostos() {
		double total = 0.0;
		for (Impostos imposto : taxa) { // Itera sobre cada imposto na lista
			total += imposto.impostoTotal(); // Soma o valor do imposto ao total
		}
		return total; // Retorna o total dos impostos
	}
}