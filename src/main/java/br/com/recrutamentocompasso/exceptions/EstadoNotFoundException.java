package br.com.recrutamentocompasso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EstadoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5453958782331247325L;
	
	@NonNull
	private String nomeEstado;
	private String message;
	
	
	public EstadoNotFoundException(String nomeEstado) {
		this.nomeEstado = nomeEstado;
		this.message ="Não há cidades cadastradas para este estado: " + nomeEstado;
	}
	
	public String getNomeEstado() {
		return nomeEstado;
	}

	public String getMessage() {
		return message;
	}

}
