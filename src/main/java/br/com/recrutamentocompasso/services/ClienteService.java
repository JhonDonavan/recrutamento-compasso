package br.com.recrutamentocompasso.services;

import java.util.List;

import br.com.recrutamentocompasso.model.Cliente;

public interface ClienteService {

	Cliente cadastrarCliente(Cliente cliente) throws Exception;
	
	Cliente consultaClientePorNome(String nomeCliente) throws Exception;
	
	Cliente consultaclintePorId(String id) throws Exception;
	
	void removerCliente(String id) throws Exception;
	
	Cliente alterarNomeCliente(String idCliente, Cliente cliente);

	List<Cliente> retornarTodosClientes();
}
