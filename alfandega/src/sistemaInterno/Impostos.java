package sistemaInterno;

import entidades.DadosProduto;

// Classe abstrata que representa diferentes tipos de impostos
public abstract class Impostos {

	// Método abstrato que cada subclasse irá implementar
	public abstract void receberImpostos(int quantidade);
	// recebe impostos para o banco

	// Método para adicionar um imposto específico ao mapa -> vai acumular icms, ipi
	// e imposto Fixo (IMPOSTO TOTAL)
	public static String[] calcularImpostos(DadosProduto produto, int code) {
		if (produto == null || produto.getTipoProduto() == null || produto.getCliente() == null) {
			throw new IllegalArgumentException("Produto ou informações do produto são inválidos.");
		}
		try {
			String[] historicoImposto;

			ICMS icms = new ICMS(produto.getTipoProduto().getPrecoUnico());
			double valorICMS = icms.impostoProduto();

			IPI ipi = new IPI(produto.getTipoProduto().getPrecoUnico());
			double valorIPI = ipi.impostoProduto();

			ImpostoFixo impostoFixo = new ImpostoFixo(produto);
			double valorImpostoFixo = impostoFixo.impostoProduto();

			double valorTotal = (valorICMS + valorIPI + valorImpostoFixo) * produto.getTipoProduto().getQuantidade();

			if (code == 0) {
				produto.getCliente().getNome();
				historicoImposto = new String[] { produto.getCliente().getCpf(), String.format("%.2f", valorICMS),
						String.format("%.2f", valorIPI), String.format("%.2f", valorImpostoFixo),
						String.format("%.2f", valorTotal) };
				// historicoImpostos.add(registro); verificar
				return historicoImposto;
			} else if (code == 1) {
				icms.receberImpostos(produto.getTipoProduto().getQuantidade());
				ipi.receberImpostos(produto.getTipoProduto().getQuantidade());
				impostoFixo.receberImpostos(produto.getTipoProduto().getQuantidade());
				return null;
			} else {
				throw new IllegalArgumentException("Código de operação inválido.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao calcular impostos: " + e.getMessage());
			return null;
		}
	}
}
