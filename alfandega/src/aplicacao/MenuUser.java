package aplicacao;

public enum MenuUser {
    // Declara o conteúdo do tipo enum
    CLIENTE(1),
    FORNECEDOR(2),
    FUNCIONARIO(3),
    FINALIZAR(4);

    private final int value; // Opção atual de menu

    // Construtor
    private MenuUser(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
