package br.com.recrutamentocompasso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNotFoundExceptionById extends RuntimeException {

	private static final long serialVersionUID = 5453958782331247325L;
	
	@NonNull
	private String idCliente;
	private String message;
	
	
	public ClienteNotFoundExceptionById(String idCliente) {
		this.idCliente = idCliente;
		this.message = "Não foi possível encontrar cliente com o ID: " + idCliente;
	}
	
	public String getIdCliente() {
		return idCliente;
	}

	public String getMessage() {
		return message;
	}

	
	

}
