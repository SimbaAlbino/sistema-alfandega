package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface Usuario {

	void listarProdutos();
	void avisosCanal();

	default void apagarUser(ArrayList<? extends Usuario> pessoas, T) {
		if (T instanceof Cliente) {
			String caminho = "";
		} else if (T instanceof Vendedor) {
			String caminho = "";
		} else {
			String caminho = "";
		}
		if (pessoas.contains(T)) {
			int idpessoa = pessoas.indexOf(T);
			pessoas.remove(idpessoa);
		}
	}

	default void adicionarUser(ArrayList<? extends Usuario> pessoas, T) {
		// colocar classe abstrata e verificar cliente
		//act. desserializar();
		if (T instanceof Cliente) {
			String caminho = "";
		} else if (T instanceof Vendedor) {
			String caminho = "";
		} else {
			String caminho = "";
		}
		Cliente cliente = null;
		if (!pessoas.contains(cliente))
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
				String line = bw.newLine();
				pessoas.add(cliente);
			} catch (IOException e) {
				System.out.println("Erro ao listar o arquivo clientes: " + e.getMessage());
			}
		
		else {
			System.out.println("Usuário já existente nos dados.");
		}
	}

	static void listarUser(ArrayList<? extends Usuario> pessoas, T) {
		//act. desserializar();
		if (T instanceof Cliente) {
			String caminho = "";
		} else if (T instanceof Vendedor) {
			String caminho = "";
		} else {
			String caminho = "";
		}
		System.out.println("Lista de todos os clientes:");
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String line = br.readLine();
			int contador = 1;
			while (line != null) {
				String[] dadosCliente = line.split(",");
				System.out.printf("%d Cliente: %s", contador++, dadosCliente[0]);
				br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao listar o arquivo clientes: " + e.getMessage());
		}
}

