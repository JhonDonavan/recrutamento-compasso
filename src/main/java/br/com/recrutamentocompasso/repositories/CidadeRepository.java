package br.com.recrutamentocompasso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.recrutamentocompasso.model.Cidade;

@Repository
public interface CidadeRepository extends MongoRepository<Cidade, String> {
	
	Optional<Cidade> findByNomeCidade(String nomeCidade);

	List<Cidade> findByNomeEstado(String nomeEstado);
}


