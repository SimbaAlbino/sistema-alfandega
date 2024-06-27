package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import utilidade.ModelagemFile;

public interface Usuario <T> {

	void listarProdutos();
	void avisosCanal(DadosProduto produto);

	default void apagarUser(String caminho, T classChamada) {
		ArrayList<?> pessoas = new ArrayList<>();
		try {
			pessoas = (ArrayList<?>) ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (pessoas.contains(classChamada)) {
			pessoas.remove(classChamada);
		}
		// ver como faz para remover uma linha do arquivo
		//act.serializar();
		// fazer em cada classe usuario, vendedor e funcionario umaa arraylist e chamar esse método apagarUser
	}

	default boolean podeAdicionarUser(ArrayList<? extends T> pessoas, T classChamada) {
		// colocar classe abstrata e verificar cliente
		//act. desserializar();
		if (!pessoas.contains(classChamada))
			return true;
		else {
			System.out.println("Usuário já existente nos dados.");
			return false;
		}
		//utilidade.serializar
	}

	default void listarUser(ArrayList<? extends T> pessoas, T classChamada) {
		// tirar a repetição dos ifs para um método
		String caminho = null;
		if (classChamada instanceof Cliente) {
			caminho = "";
		} else if (classChamada instanceof Vendedor) {
			caminho = "";
		} else {
			caminho = "";
		}
		//utilidade. desserializar();
		System.out.printf("Lista de todos os %s: %n", classChamada.getClass().getSimpleName());
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String line = br.readLine();
			int contador = 1;
			while (line != null) {
				String[] dadosUser = line.split(",");
				System.out.printf("%d %s: %s%n", contador++, classChamada.getClass().getSimpleName(), dadosUser[0]);
				br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao listar o arquivo clientes: " + e.getMessage());
		}
		//utilidade.serializar()
	}
}
	
/*
 *String caminho = null;
		if (classChamada instanceof Cliente) {
			caminho = "";
		} else if (classChamada instanceof Vendedor) {
			caminho = "";
		} else {
			caminho = "";
		}		
 * */

