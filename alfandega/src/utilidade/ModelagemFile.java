package utilidade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModelagemFile {

    public static <T> void serializar(String caminhoFile, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFile))) {
            oos.writeObject(lista);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado na serialização: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O na serialização: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	public static <T> ArrayList<T> desserializar(String caminhoFile) throws ClassNotFoundException {
        ArrayList<T> listaRetorno = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFile))) {
            listaRetorno = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado na desserialização: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O na desserialização: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Exceção de Classe não encontrada na desserialização: " + e.getMessage());
            throw e;
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
