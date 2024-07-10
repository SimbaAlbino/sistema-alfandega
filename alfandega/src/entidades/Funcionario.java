package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario implements Usuario<Funcionario>, Serializable {

	private static final long serialVersionUID = 1L;

	static Scanner sc = new Scanner(System.in);

	private String nomeAdm;
	private String email;
	private String senha;
	private String cpf;
	
	//Pensar em criar um sistema de pagamento para o funcionário.

	private String caminhoFuncionariosFile = "C:\\Users\\pedro\\Desktop\\Study\\sistema-alfandega\\files\\login\\fileFuncionarios.txt";

	// Construtor para o menu
	public Funcionario() {
	}

	public Funcionario(String nomeAdm, String email, String senha, String cpf) {
		this.nomeAdm = nomeAdm;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	@Override
	public String getNome() {
		return nomeAdm;
	}

	public String getCaminhoFileUser() {
		return caminhoFuncionariosFile;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	@Override
	public boolean confirmarUser(String[] dadosEntrada) {
		ArrayList<Funcionario> funcionarios = listarUsuarios(getCaminhoFileUser());
		if (funcionarios == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Funcionario funcionario = new Funcionario(dadosEntrada[0], dadosEntrada[1]);
			for (Funcionario pessoa : funcionarios) {
				if (pessoa.equalsByEmailAndSenha(funcionario.getEmail(), funcionario.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

	public void cadastrarFuncionario(String nomeAdm, String email, String senha, String cpf) {
		// instanciamos o funcionario com o construtor
		
	}

	
	/*
	public void opreadorFuncionario() {
		System.out.println("Reformulação de Estoque: ");
		System.out.println("1 - editar Produto\n2 - editar Cliente\n3 - editar Fornecedor\n4 - editarDivida");
		short op = sc.nextShort();
		switch (op) {
		case 1:
			editarDadoProduto();
		case 2:
			editarCliente();
		case 3:
			editarFornecedor();
		}
	}
	*/

	public void editarDadoProduto() {

	}
	
	public boolean equalsByEmailAndSenha(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

	public void editarProcesso() {

	}

	public void editarDivida() {

	}

	@Override
	public ArrayList<DadosProduto> avisosCanal(DadosProduto produto) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public void listarProdutos(ArrayList<DadosProduto> produtosFiltrados) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastro() {
		condicaoCadastro(this, caminhoFuncionariosFile);
	}

	@Override
	public void operacoesUser() {	
	}

	// criar metodo cadastrar funcionario, pois ele só podera ser criado a partir de
	// outro funcionario.

}
