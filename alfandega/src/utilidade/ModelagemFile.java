package utilidade;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModelagemFile {

	public static void serializar(String caminhoFile, ArrayList<?> lista) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFile))) {
			oos.writeObject(lista);
		} catch (IOException er) {
			System.out.println("Arquivo não encontrado na serialização: " + er.getMessage());
		} 
	}

	public static ArrayList<?> desserializar(String caminhoFile) throws ClassNotFoundException {
		ArrayList<?> listaRetorno = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFile))) {
			listaRetorno = (ArrayList<?>) ois.readObject(); // É NECESSÁRIO CASTING POR CAUSA DO CURINGA
		} catch (IOException er) {
			System.out.println("Arquivo não encontrado na desserialização: " + er.getMessage());
		} catch (ClassNotFoundException er) {
			System.out.println("Exceção de Classe não encontrada na desserialização: " + er.getMessage());
		}
		return listaRetorno;
	}
	
	public static void existenciaArquivo() {
		
	}
	
	public static void deletarArquivo() {
		
	}

	public static void moverArquivo() {
		
	}
	
	public static void limparArquivo() {
		
	}
	
	
	
	
	//ler arquivo vai retornar uma array e antes usará o desserializar, ou seja, tudo que está na interface user, na verdade será somente serializar.
}

/*
 * public static ArrayList<String> lerArquivoString(String caminho) {
		try {
			ModelagemFile.desserializar(caminho);
		} catch (ClassNotFoundException e) {
			System.out.println("Problema ao desserializar arquivo: " + e.getMessage());
		}
		ArrayList<String> listagemFile = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String line = br.readLine();
			while (line != null) {
				listagemFile.add(line); //resolver problema de listar o file
			}
		} catch (IOException er) {
			System.out.println("Erro ao encontrar o arquivo para leitura: " + er.getMessage());
		}
		return listagemFile;
	}
 */ 
