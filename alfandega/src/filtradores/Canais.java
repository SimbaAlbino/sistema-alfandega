package filtradores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import entidades.DadosProduto;
import reserva.Estoque;
import reserva.StatusProduto;
import tiposProduto.Informatica;

public class Canais {

	private CanalCor cor;
	private DadosProduto produto;
	private double minPrecoTax = 250;

	public Canais(DadosProduto produto) {
		this.produto = produto;
	}

	public CanalCor getCor() {
		return cor;
	}

	public void setCor(CanalCor canal) {
		this.cor = canal;
	}

	public DadosProduto getProduto() {
		return produto;
	}

	public double getMinPrecoTax() {
		return minPrecoTax;
	}

	public void setMinPrecoTax(double minPrecoTax) {
		this.minPrecoTax = minPrecoTax;
	}

	public CanalCor coloracaoStatusObj() {
		if (getProduto().isDocumentos() == false && getProduto().getStatus() == StatusProduto.FISCALIZANDO) {
			this.cor = CanalCor.valueOf("CINZA");
			// status vai ser definido como rejeitado
			
		} else if (getProduto().getStatus() == StatusProduto.FISCALIZANDO
				|| ChronoUnit.DAYS.between(getProduto().getDataDeOperacao(), LocalDate.now()) > 30) {
			this.cor = CanalCor.valueOf("VERMELHO");
			// produto terá status como retornado e será enviado imediatamente ao despache

		} else if ((getProduto().getStatus() == StatusProduto.FISCALIZANDO
				&& getProduto().getTipoProduto() instanceof Informatica)
				|| (getProduto().getStatus() == StatusProduto.FISCALIZANDO
						&& getProduto().getTipoProduto().getPrecoUnico() < getMinPrecoTax())
				|| getProduto().getStatus() == StatusProduto.PAGO) {
			// libera se for produto de informática, ou preço unico do produto for menor que
			// 250 reais, ou status do produto for pago
			this.cor = CanalCor.valueOf("VERDE");

		} else if (getProduto().getStatus() == StatusProduto.FISCALIZANDO) {
			this.cor = CanalCor.valueOf("AMARELO");

		}

		// ou se o produto tiver o status
		// lançar um throw que o produto não tem um status
		return this.cor;

	}

	// aqui o status é alterado
	public void moldagemProduto() {
		// emitir avisos ou nota fiscal
		coloracaoStatusObj();
		switch (cor) {
		case CANAL_CINZA:
			getProduto().setStatus(StatusProduto.REJEITADO);
			break;
		case CANAL_AMARELO:
			getProduto().setStatus(StatusProduto.AGUARDANDO_PAGAMENTO);
			// chamar a operação de adicionar dívida
			break;
		case CANAL_VERDE:
			getProduto().setStatus(StatusProduto.ENVIADO);
			Estoque.despacharProduto(getProduto());
			break;
		case CANAL_VERMELHO:
			Estoque.despacharProduto(getProduto());
			break;
		default:
			break;
		}
	}

	/*
	 * public String notaFiscal() {
	 * System.out.println("O produto %s de id %s foi revisado e está no canal %s%s."
	 * ); // afirmar imposto, como foi calculado, quantidade e }
	 * 
	 * public String emitirAviso() { StringBuilder sb = new StringBuilder();
	 * sb.append("O produto %s de id %s não foi repassado por motivos de : %s%s.")
	 * if (this.cor == "")
	 * 
	 * produto.setRecado() }
	 */
}
