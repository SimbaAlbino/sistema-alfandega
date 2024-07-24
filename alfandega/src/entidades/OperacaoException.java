package entidades;

/**
 * Classe que define uma exceção personalizada chamada OperacaoException. Esta
 * exceção é usada para sinalizar erros específicos em operações dentro do
 * sistema.
 */
public class OperacaoException extends Exception {

	private static final long serialVersionUID = 1L; // Serial version UID para controle de versão da serialização

	/**
	 * Construtor que aceita uma mensagem de erro.
	 * 
	 * @param msg A mensagem de erro associada à exceção.
	 */
	public OperacaoException(String msg) {
		super(msg); // Chama o construtor da superclasse (Exception) passando a mensagem de erro.
	}
}