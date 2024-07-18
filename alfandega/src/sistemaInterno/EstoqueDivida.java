package sistemaInterno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Cliente;
import entidades.DadosProduto;
import utilidade.ModelagemFile;

public class EstoqueDivida {

	private static long totalDividas;

	private static String caminhoEstoqueDividas = "C:\\Users\\Pedro\\Desktop\\Study\\sistema-alfandega\\files\\estocar\\estoqueDivida.txt";

	public static ArrayList<Dividas> listaDividas() {
		ArrayList<Dividas> listaDividas = ModelagemFile.desserializar(getCaminhoBanco());
		totalDividas = listaDividas.size();
		return listaDividas;
	}

	public static void addDividas(Dividas dividas) {
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
	        for (Dividas divida : listaDividas()) {
	            System.out.println("Dívida de " + divida.getDadosProduto().getCliente().getNome() + ": " + divida.getMontante());
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

	//metodo para listar todas as dividas do cliente
	//quando o cliente quiser saber quais dividas tem
	 public static ArrayList<Dividas> encontrarDividasPorCliente(Cliente cliente) {
	        List<Dividas> dividas = listaDividas().stream()
	                .filter(x -> x.getDadosProduto().getCliente().equals(cliente))
	                .collect(Collectors.toList());
	        if (dividas != null) {
	            return new ArrayList<>(dividas);
	        }
	        return null;
	    }
	 
	 
	public static long getTotalDividas() {
		return totalDividas;
	}

	public static String getCaminhoBanco() {
		return caminhoEstoqueDividas;
	}
}
