package utilidade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarDados {

	// Método para validar CEP
	public static boolean validarCEP(String cep) {
		// Verifica se o CEP não é nulo e se possui exatamente 5 dígitos numéricos
		return cep != null && cep.matches("\\d{5}");
	}
	
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF não é nulo e se possui exatamente 11 dígitos numéricos
		return cpf != null && cpf.matches("\\d{11}");
	}
}