package br.com.recrutamentocompasso.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.recrutamentocompasso.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByNomeCompleto(String nomeCompleto);
}


