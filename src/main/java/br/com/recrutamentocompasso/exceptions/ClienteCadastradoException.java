package br.com.recrutamentocompasso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NonNull;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClienteCadastradoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1827382367811649621L;
	@NonNull
	private String nomeCliente;
	private String message;
	
	
	public ClienteCadastradoException(String nomeCliente)  {
		this.nomeCliente = nomeCliente;
		this.message = "Já existe um cliente com o nome informado: " + nomeCliente;
	}
	
	public String getIdCliente() {
		return nomeCliente;
	}

	public String getMessage() {
		return message;
	}
}
