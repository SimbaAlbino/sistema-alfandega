package filtradores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import entidades.DadosProduto;
import reserva.Estoque;
import reserva.StatusProduto;
import sistemaInterno.Dividas;
import sistemaInterno.EstoqueDivida;
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
			this.cor = CanalCor.CANAL_CINZA;
			getProduto().setRecado("• Produto rejeitado, entre em contato com um funcionário para fornecer documentos.");
			// status vai ser definido como rejeitado
			
		} else if (getProduto().getStatus() == StatusProduto.AGUARDANDO_PAGAMENTO
				&& ChronoUnit.DAYS.between(getProduto().getDataDeOperacao(), LocalDate.now()) > 30) {
			this.cor = CanalCor.CANAL_VERMELHO;
			getProduto().setDataDeOperacao(LocalDate.now());
			getProduto().setRecado("• Produto retornado, prazo de 30 dias expirou.");

			// produto terá status como retornado e será enviado imediatamente ao despache

		} else if ((getProduto().getStatus() == StatusProduto.FISCALIZANDO
				&& getProduto().getTipoProduto() instanceof Informatica)
				|| (getProduto().getStatus() == StatusProduto.FISCALIZANDO
						&& getProduto().getTipoProduto().getPrecoUnico() < getMinPrecoTax())
				|| getProduto().getStatus() == StatusProduto.PAGO) {
			// libera se for produto de informática, ou preço unico do produto for menor que
			// 250 reais, ou status do produto for pago
			this.cor = CanalCor.CANAL_VERDE;
		} else if (getProduto().getStatus() == StatusProduto.FISCALIZANDO) {
			this.cor = CanalCor.CANAL_AMARELO;
			getProduto().setRecado("Pague para despachar o produto.");
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
			getProduto().setDataDeOperacao(LocalDate.now());
			break;
		case CANAL_AMARELO:
			getProduto().setStatus(StatusProduto.AGUARDANDO_PAGAMENTO);
			getProduto().setDataDeOperacao(LocalDate.now());
			Dividas divida = new Dividas(getProduto());
			EstoqueDivida.addDividas(divida);
			break;
		case CANAL_VERDE:
			getProduto().setStatus(StatusProduto.ENVIADO);
			Estoque.despacharProduto(getProduto());
			break;
		case CANAL_VERMELHO:
			getProduto().setStatus(StatusProduto.RETORNADO);
			Estoque.despacharProduto(getProduto());
			break;
		default:
			break;
		}
	}
}
