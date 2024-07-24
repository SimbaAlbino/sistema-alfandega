package utilidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModelagemFile {

    public static <T> void serializar(String caminhoFile, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFile))) {
            oos.writeObject(lista);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado na serialização: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O na serialização: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> desserializar(String caminhoFile) {
        ArrayList<T> listaRetorno = new ArrayList<>();
        File file = new File(caminhoFile);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFile))) {
                listaRetorno = (ArrayList<T>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado na desserialização: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro de I/O na desserialização: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Exceção de Classe não encontrada na desserialização: " + e.getMessage());
            }
        } else {
        	return new ArrayList<T>();
        }
        return listaRetorno;
    }
    
    public static void criarArquivo(String caminhoFile) throws IOException {
    	File file = new File(caminhoFile);
    	try {
            if (file.createNewFile()) {
                System.out.println("Arquivo criado: " + file.getName());
            } else {
                System.out.println("O arquivo já existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }
	
    public static void limparArquivo(String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Escreve uma string vazia para limpar o conteúdo do arquivo
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
