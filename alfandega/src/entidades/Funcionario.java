package entidades;

import java.io.Serializable;
import java.util.Scanner;

public class Funcionario implements Usuario<Funcionario>, Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeAdm;
	private String email;
	private String senha;

	private String caminhoFuncionariosFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFuncionarios.txt";

	private Funcionario(String nomeAdm, String email, String senha) {
		this.nomeAdm = nomeAdm;
		this.email = email;
		this.senha = senha;
	}
	
	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getNomeAdm() {
		return nomeAdm;
	}

	public String getCaminhoFuncionariosFile() {
		return caminhoFuncionariosFile;
	}

	public void cadastrarFuncionario(String nomeAdm, String email, String senha) {
		// instanciamos o funcionario com o construtor
		
	}

	public void atualizarCatalogo() {

	}

	public void editarProcesso() {

	}

	public void editarDivida() {

	}

	@Override
	public void listarProdutos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void avisosCanal(DadosProduto produto) {
		// TODO Auto-generated method stub

	}

}
