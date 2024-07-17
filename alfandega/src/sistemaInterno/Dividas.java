package sistemaInterno;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import entidades.DadosProduto;

public class Dividas implements Pagamento {
	private double montante;
	private DadosProduto dadoProduto;

	public Dividas(Cliente clientela, DadosProduto dadoProduto) {
		this.dadoProduto = dadoProduto;
		setMontante(calcularDespesaPedidoTot(dadoProduto));
	}

	// arrumar
	// se a divida desse produto for 0, significa que pagou
	@Override
	public boolean dividaPendente() {
		return !(montante == 0);
	}

	@Override
	public boolean liberarDivida() {
		if (dividaPendente()) {
			montante = 0;
			System.out.println("Dívida paga com sucesso.");
			return true;
		} else {
			System.out.println("Não há dívida pendente.");
			return false;
		}
	}

	// metodo para pagar que retorna um boolean

	// se for true
	private void realizarPagamento(boolean pago) {
		
		if (!pagarProduto()) {
			// pensar
			System.out.println("Valor indisponível para pagamento via .");

		} else {
			// quando pagamento confirmado
			// corrigir mensagem
			// setMontante(0);
			// mostrar pagamento de getmontante sendo executado.
			// Banco.addInsumos(); para add valor do imposto
			Banco.liberarPedido(this);

			// -> quem faz é o banco

			// msg pagamento confirmado
		}
	}

	public String detalharCalculoImposto() {
		ICMS icms = new ICMS(dadoProduto);
		icms.receberImpostos(dadoProduto);

		IPI ipi = new IPI(dadoProduto);
		ipi.receberImpostos(dadoProduto);

		ImpostoFixo impostoFixo = new ImpostoFixo(dadoProduto);
		impostoFixo.receberImpostos(dadoProduto);

		return Impostos.detalharImpostos();
	}

	public double getMontante() {
		return montante;
	}

	public void setMontante(double montante) {
		this.montante = montante;
	}

	//criar metodo do boleto
	//criar o metodo do pix que mandei no disc
	
	//fazer
	public boolean pagarProduto() {
		// usar métodos que passou de pix e boleto no disc se quiser pix digite 1, 2
		// para boleto
		// pagar (s/n)? se sim transfer realiada return true // pagamento autorizado
		// chamar a função calcular despesas pedido total para descobri o valor total a
		// ser pago
		return false;
	}

	@Override
	public DadosProduto getDadosProduto() {
		// TODO Auto-generated method stub
		return null;
	}
}
