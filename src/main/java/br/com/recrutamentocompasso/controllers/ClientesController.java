package br.com.recrutamentocompasso.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.recrutamentocompasso.model.Cliente;
import br.com.recrutamentocompasso.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("REST API RECRUTAMENTO COMPASSO")
@RestController
@RequestMapping("api/clientes")
public class ClientesController {
	
	@Autowired
	ClienteService clientesService;
	
	@ApiOperation(value = "Cadastrar Cliente")
	@PostMapping("/")
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente cliente) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.cadastrarCliente(cliente));
	}
	
	
	@ApiOperation(value = "Retorna clientes por nome")
	@GetMapping("/nome")
	public ResponseEntity<Cliente> consultarClientePorNome(@RequestParam(value = "nomeCompleto") String nomeCompleto) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.consultaClientePorNome(nomeCompleto));
	}
	
	
	@ApiOperation(value = "Retorna clientes por id")
	@GetMapping("/id")
	public ResponseEntity<Cliente> consultarClientePorId(@RequestParam(value = "id") String id) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.consultaclintePorId(id));
	}
	
	@ApiOperation(value = "Remover Cliente")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerCliente(@PathVariable String id) throws Exception{
		clientesService.removerCliente(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@ApiOperation(value = "Alterar nome de cliente")
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarNomeCliente(@PathVariable String id, @RequestBody Cliente cliente) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.alterarNomeCliente(id, cliente));
	}
	
	
	@ApiOperation(value = "Retorna todos os clientes")
	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> retornarTodosClientes() throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.retornarTodosClientes());
	}
	
	
}
	