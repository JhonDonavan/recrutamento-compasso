package br.com.recrutamentocompasso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CidadeCadastradaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1827382367811649621L;
	@NonNull
	private String nomeCidade;
	private String message;
	
	
	public CidadeCadastradaException(String nomeCidade)  {
		this.nomeCidade = nomeCidade;
		this.message = "Já existe uma cidade cadastrada com o nome: " + nomeCidade;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}

	public String getMessage() {
		return message;
	}
}
