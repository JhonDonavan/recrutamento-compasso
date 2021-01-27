package br.com.recrutamentocompasso.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNotFoundExceptionByNome extends RuntimeException {

	private static final long serialVersionUID = 5453958782331247325L;
	
	@NonNull
	private String nomeCliente;
	private String message;
	
	
	public ClienteNotFoundExceptionByNome(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		this.message = "Não foi possivel encontrar o cliente com o nome: " + nomeCliente;

	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getMessage() {
		return message;
	}
}
