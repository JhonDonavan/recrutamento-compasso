package br.com.recrutamentocompasso.services;

import java.util.List;

import br.com.recrutamentocompasso.model.Cidade;

public interface CidadeService {
	Cidade cadastrarCidade(Cidade cidade);
	
	Cidade retornarCidadePorNome(String nomeCidade);
	
	List<Cidade> retornarCidadesPorEstado(String nomeEstado);

	List<Cidade> retornarTodasCidades();
		
}
