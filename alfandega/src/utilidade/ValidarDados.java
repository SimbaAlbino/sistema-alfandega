package utilidade;

public class ValidarDados {

	// Método para validar CEP
	public static boolean validarCEP(String cep) {
		// Verifica se o CEP não é nulo e se possui exatamente 9 dígitos numéricos
		return cep != null && cep.matches("\\d{8}");
	}
	
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF não é nulo e se possui exatamente 11 dígitos numéricos
		return cpf != null && cpf.matches("\\d{11}");
	}
	
	public static boolean validarEmail(String email) {
		return email != null && email.contains("@");
	}
	
	public static boolean validarSenha(String senha) {
		return senha != null && (senha.length() >= 6);
	}
	
	public static boolean validarIdProduto(Integer id) {
		String idProduto = "" + id;
		return idProduto != null && idProduto.matches("\\d{6}");
	}
	
	public static boolean validarNome(String nome) {
		return nome != null;
	}
}