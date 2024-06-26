package aplicacao;

public enum MenuOption {
    // Declara o conteúdo do tipo enum
    Cliente(1),
    Funcionario(2),
    Vendedor(3);

    private final int value; // Opção atual de menu

    // Construtor
    private MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
