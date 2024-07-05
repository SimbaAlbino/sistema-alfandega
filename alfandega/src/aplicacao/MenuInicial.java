package aplicacao;

public enum MenuInicial {
	RASTREAR(1),
	CADASTRAR(2),
	LOGAR(3),
	VOLTAR(4);
	
    private final int value; // Opção atual de menu

    // Construtor
    private MenuInicial(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
