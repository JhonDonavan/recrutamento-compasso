package br.com.recrutamentocompasso.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CidadeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5453958782331247325L;
	
	@NonNull
	private String nomeCidade;
	private String message;
	

	public CidadeNotFoundException(String nomeCidade) {
		this.nomeCidade = nomeCidade;
		this.message = "Não foi possivel encontrar a cidade: " + nomeCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public String getMessage() {
		return message;
	}	
}
