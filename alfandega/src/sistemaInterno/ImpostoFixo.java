package sistemaInterno;

public class ImpostoFixo extends Impostos {
	public static final String TIPO_IMPOSTO = "Imposto Fixo";
	private static final double VALOR_IMPOSTO_FIXO = 15.0;

	public ImpostoFixo() {
		super(TIPO_IMPOSTO, VALOR_IMPOSTO_FIXO);
	}

	@Override
	public double calcularImpostoTotal() {
		adicionarValorRecolhido(VALOR_IMPOSTO_FIXO);
		return VALOR_IMPOSTO_FIXO;
	}
}