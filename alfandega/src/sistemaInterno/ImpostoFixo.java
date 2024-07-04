package sistemaInterno;

public class ImpostoFixo extends Impostos {
	public static final String TIPO_MONSTRO = "Imposto Fixo";
	private static final double VALOR_IMPOSTO_FIXO = 15.0; // exemplo - alterar valor

	public ImpostoFixo() {
		super(TIPO_MONSTRO, VALOR_IMPOSTO_FIXO);

	}

	@Override
	public double calcularImpostoTotal() {
		return getValorImposto() * VALOR_IMPOSTO_FIXO;
	}

}
