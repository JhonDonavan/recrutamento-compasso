package br.com.recrutamentocompasso.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recrutamentocompasso.model.Cidade;
import br.com.recrutamentocompasso.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("REST API RECRUTAMENTO COMPASSO")
@RestController
@RequestMapping("api/cidades")
public class CidadesController {
	
	@Autowired
	CidadeService cidadeService;
	
	@ApiOperation(value = "Criar cidade")
	@PostMapping("/")
	public ResponseEntity<Cidade> CriarCidade(@Valid @RequestBody Cidade cidade){
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.cadastrarCidade(cidade));
	}
	
	@ApiOperation(value = "Retorna cidades por estado")
	@GetMapping("/cidade/{cidadeNome}")
	public ResponseEntity<Cidade> RetornaCidadePorNome(@PathVariable("cidadeNome") String cidadeNome){
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.retornarCidadePorNome(cidadeNome));
	}
	
	
	@ApiOperation(value = "Retorna cidades por estado")
	@GetMapping("/estado/{estadoNome}")
	public ResponseEntity<List<Cidade>> retornaCidadesPorEstados(@PathVariable("estadoNome") String estadoNome){
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.retornarCidadesPorEstado(estadoNome));
	}
	
	public ResponseEntity<List<Cidade>> retornarTodasCidades(){
		return ResponseEntity.status(HttpStatus.OK).body(cidadeService.retornarTodasCidades());
	}
	
	

}
