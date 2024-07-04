package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

public class Dividas {
	private Cliente clientela;
	private double montante;
	private List<Produto> produtos;
	private List<Divida> dividas;

	public Dividas(Cliente clientela) {
		this.clientela = clientela;
		this.produtos = new ArrayList<>();
		this.dividas = new ArrayList<>();
		this.montante = 0.0;
	}

	// Método para calcular a despesa total
	private double calcularDespesa() {
		double total = 0.0;
		for (Produto produto : produtos) {
			total += produto.getPreco();
		}
		return total;
	}

	// Método para verificar se a dívida está pendente
	private boolean dividaPendente() {
		;
	}

	// Método para selecionar um produto associado à dívida
	private void selecionarProduto(Produto produto) {
		
	}

	// Método para marcar a dívida como paga
	private void dividaPaga() {
		if (dividaPendente()) {
			montante = 0;
			produtos.clear();
			System.out.println("Dívida paga com sucesso.");
		} else {
			System.out.println("Não há dívida pendente.");
		}
	}

	// Método para adicionar a dívida a um arquivo (simulação)
	private void addDividaFile() {
		Divida divida = new Divida(clientela, montante, produtos);
		dividas.add(divida);
		System.out.println("Dívida adicionada ao arquivo.");
	}

	// Classe interna para representar uma dívida
	private class Divida {
		private Cliente cliente;
		private double valor;
		private List<Produto> produtos;

		public Divida(Cliente cliente, double valor, List<Produto> produtos) {
			this.cliente = cliente;
			this.valor = valor;
			this.produtos = new ArrayList<>(produtos);
		}
	}
}