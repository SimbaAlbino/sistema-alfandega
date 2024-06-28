package reserva;

public class IPI extends Impostos {
    private final float ipi = 10.00f; // Valor fixo do IPI

    // Construtor que recebe o produto ao qual o imposto será aplicado
    public IPI(Produto objetoTaxa) {
        this.objetoTaxa = objetoTaxa;
    }

    // Implementação do método abstrato para calcular o valor do IPI
    @Override
    public double impostoTotal() {
        return objetoTaxa.getPrecoUnico() * ipi; // Calcula o IPI com base no preço do produto
    }
}