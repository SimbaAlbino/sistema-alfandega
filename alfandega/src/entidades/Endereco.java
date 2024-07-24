package entidades;

import java.io.Serializable;

public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String cep;
	private short numeroResidencia;

	public Endereco(String cep, short numeroResidencia) {
		this.cep = cep;
		this.numeroResidencia = numeroResidencia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public short getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(short numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}
	
	

}
