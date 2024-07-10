package sistemaInterno;

public interface Pagamento {
	boolean dividaPendente();

	boolean liberarDivida();

	boolean pagarPorPix(double valor);

	boolean pagarPorBoleto(double valor);
}
