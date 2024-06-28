package sistemaInterno;

public class ICMS extends Impostos {
    private final float icms = 20.00f; // Valor fixo do icms

    // Construtor que recebe o produto ao qual o imposto será aplicado
    public ICMS(Produto objetoTaxa) {
        this.objetoTaxa = objetoTaxa;
    }
    // Implementação do método abstrato para calcular o valor do ICMS
    @Override
    public double impostoTotal() {
        return objetoTaxa.getPrecoUnico() * icms; // Calcula o ICMS com base no preço do produto
    }
        
    }
}