package sistemaInterno;

public interface Pagamento {
	boolean dividaPendente();

	boolean liberarDivida();

	boolean pagarGeral(double valor);

}
