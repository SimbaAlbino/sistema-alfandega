package sistemaInterno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Cliente;
import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class EstoqueDivida {

	private static long totalDividas;

	private transient static String caminhoEstoqueDividas = "C:\\Users\\Pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDivida.txt";

	public static ArrayList<Dividas> listaDividas() {
		ArrayList<Dividas> listaDividas = ModelagemFile.desserializar(getCaminhoBanco());
		if (listaDividas != null) {
			totalDividas = listaDividas.size();
		} else {
			return new ArrayList<>();			
		}
		return listaDividas;
	}

	public void addDividas(Dividas dividas) {
		ArrayList<Dividas> estoqueGeral = listaDividas();
		estoqueGeral.add(dividas);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);
		//
		
		
	}

	public synchronized static void removerDivida(Dividas dividas) {
		ArrayList<Dividas> estoqueGeral = listaDividas();
		estoqueGeral.remove(dividas);
		ModelagemFile.serializar(getCaminhoBanco(), estoqueGeral);

	}

	 public static void lerEstoqueDividas() {
		 if (! listaDividas().isEmpty()) {
			 for (Dividas divida : listaDividas()) {
				 System.out.println("Dívida de " + divida.getDadosProduto().getCliente().getNome() + ": " + divida.getMontante());
			 }
		 } else {
			 System.out.println("Estoque de dívidas vazio.");
		 }
	    }
	
	//metodo para encontrar uma divida a partir de um produto
	public static boolean encontrarDivida(DadosProduto produto) {
        for (Dividas divida : listaDividas()) {
            if (divida.getDadosProduto().equals(produto)) {
                return true;
            }
        }
        return false;
    }
	
	public static Dividas encontrarDividaProduto(DadosProduto produto) {
        for (Dividas divida : listaDividas()) {
            if (divida.getDadosProduto().equals(produto)) {
                return divida;
            }
        }
        return null;
    }	

	//metodo para listar todas as dividas do cliente
	//quando o cliente quiser saber quais dividas tem
	public static List<Dividas> encontrarDividasPorCliente(Cliente cliente) {
	    if (cliente == null) {
	        throw new IllegalArgumentException("Cliente não pode ser null");
	    }
	    
	    List<Dividas> dividas = listaDividas();
	    if (dividas == null) {
	        return Collections.emptyList(); // Retorna uma lista vazia se não houver dívidas
	    }
	    
	    return dividas.stream()
	                  .filter(x -> x.getDadosProduto().getCliente().equals(cliente))
	                  .collect(Collectors.toList());
	}

	 
	 
	public static long getTotalDividas() {
		return totalDividas;
	}

	public static String getCaminhoBanco() {
		return caminhoEstoqueDividas;
	}
}
