package aplicacao;

public enum MenuOption {
    // Declara o conteúdo do tipo enum
    ZERO_BALANCE(1),
    CREDIT_BALANCE(2),
    DEBIT_BALANCE(3),
    END(4);

    private final int value; // Opção atual de menu

    // Construtor
    private MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
