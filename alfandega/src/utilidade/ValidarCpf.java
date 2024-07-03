package utilidade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarCpf {

	// Método para validar CPF
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF não é nulo e se possui exatamente 11 dígitos numéricos
		return cpf != null && cpf.matches("\\d{11}");
	}

}