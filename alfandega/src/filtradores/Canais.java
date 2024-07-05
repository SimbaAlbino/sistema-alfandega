package filtradores;

import entidades.DadosProduto;
import reserva.StatusProduto;
import tiposProduto.Informatica;

public class Canais {

	private CanalCor cor;
	private DadosProduto produto;

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

	public void setProduto(DadosProduto produto) {
		this.produto = produto;
	}

	public CanalCor coloracaoObj(DadosProduto produto) {
	    if (produto.isDocumentos() == true) {
	        this.cor = CanalCor.valueOf("CINZA");
	    } else if (produto.getStatus() == StatusProduto.RETORNADO) {
	        this.cor = CanalCor.valueOf("VERMELHO");
	    } else if (produto.getStatus() == StatusProduto.FISCALIZANDO) {
	        this.cor = CanalCor.valueOf("AMARELO");
	    } else if (produto.getTipoProduto().getClass() instanceof Informatica || //aguardando pagamento do status){ // corrigir
	        this.cor = CanalCor.valueOf("VERDE");
	    }
	    //lançar um throw que o produto não tem um status
	    return this.cor;
	}
	
	public void finalProdutoPago() {
		
	}
	
	public String notaFiscal() {
		System.out.println("O produto %s de id %s foi revisado e está no canal %s%s."); // afirmar imposto, como foi calculado, quantidade e 
	}
	
	public String emitirAviso() {
		StringBuilder sb = new StringBuilder();
		sb.append("O produto %s de id %s não foi repassado por motivos de : %s%s.")
		if (this.cor == "")
			
		produto.setRecado()
	}
}
