package utilidade;

public class ValidarDados {

	// Método para validar CEP
	public static boolean validarCEP(String cep) {
		// Verifica se o CEP não é nulo e se possui exatamente 8 dígitos numéricos
		return cep != null && cep.matches("\\d{8}");
	}

	// Método para validar CPF
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF não é nulo e se possui exatamente 11 dígitos numéricos
		return cpf != null && cpf.matches("\\d{11}");
	}

	// Método para validar e-mail
	public static boolean validarEmail(String email) {
		return email != null && email.contains("@");
	}

	// Método para validar senha
	public static boolean validarSenha(String senha) {
		return senha != null && (senha.length() >= 6);
	}

	// Método para validar ID do produto
	public static boolean validarIdProduto(Integer id) {
		String idProduto = "" + id;
		return idProduto != null && idProduto.matches("\\d{6}");
	}

	// Método para validar nome
	public static boolean validarNome(String nome) {
		return nome != null;
	}
}