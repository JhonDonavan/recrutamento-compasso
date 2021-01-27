package br.com.recrutamentocompasso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recrutamentocompasso.exceptions.CidadeCadastradaException;
import br.com.recrutamentocompasso.exceptions.CidadeNotFoundException;
import br.com.recrutamentocompasso.exceptions.EstadoNotFoundException;
import br.com.recrutamentocompasso.model.Cidade;
import br.com.recrutamentocompasso.repositories.CidadeRepository;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public Cidade cadastrarCidade(Cidade cidade) {
		
		Optional<Cidade> cidadeExiste = cidadeRepository.findByNomeCidade(cidade.getNomeCidade());
		
		if(cidadeExiste.isPresent()) {
			throw new CidadeCadastradaException(cidade.getNomeCidade());
		}
		return cidadeRepository.save(cidade);
	}

	@Override
	public Cidade retornarCidadePorNome(String nomeCidade) {
		return cidadeRepository.findByNomeCidade(nomeCidade).orElseThrow(() -> new  CidadeNotFoundException(nomeCidade));
	}

	@Override
	public List<Cidade> retornarCidadesPorEstado(String nomeEstado) {
		 List<Cidade> cidades = cidadeRepository.findByNomeEstado(nomeEstado);
		 
		 if(cidades.size() > 0) {
			 return cidades;
		 }
		 
		 throw new EstadoNotFoundException(nomeEstado);
	}

	@Override
	public List<Cidade> retornarTodasCidades() {
		return cidadeRepository.findAll();
	}

}
