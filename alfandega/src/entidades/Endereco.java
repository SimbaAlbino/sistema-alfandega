package entidades;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos da classe
	private String cep; // Código de Endereçamento Postal (CEP)
	private short numeroResidencia; // Número da residência

	// Construtor com parâmetros para inicializar o endereço
	public Endereco(String cep, short numeroResidencia) {
		this.cep = cep;
		this.numeroResidencia = numeroResidencia;
	}

	// Getter para o atributo 'cep'
	public String getCep() {
		return cep;
	}

	// Setter para o atributo 'cep'
	public void setCep(String cep) {
		this.cep = cep;
	}

	// Getter para o atributo 'numeroResidencia'
	public short getNumeroResidencia() {
		return numeroResidencia;
	}

	// Setter para o atributo 'numeroResidencia'
	public void setNumeroResidencia(short numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}
}
