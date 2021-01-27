package br.com.recrutamentocompasso.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recrutamentocompasso.exceptions.CidadeNotFoundException;
import br.com.recrutamentocompasso.exceptions.ClienteCadastradoException;
import br.com.recrutamentocompasso.exceptions.ClienteNotFoundExceptionById;
import br.com.recrutamentocompasso.exceptions.ClienteNotFoundExceptionByNome;
import br.com.recrutamentocompasso.model.Cidade;
import br.com.recrutamentocompasso.model.Cliente;
import br.com.recrutamentocompasso.repositories.CidadeRepository;
import br.com.recrutamentocompasso.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;

	@Override
	public Cliente cadastrarCliente(Cliente cliente) throws Exception {
		Optional<Cliente> ClienteExiste  = clienteRepository.findByNomeCompleto(cliente.getNomeCompleto());
		
		if(ClienteExiste.isPresent()) {
			throw new ClienteCadastradoException(cliente.getNomeCompleto());
		}

		Cidade cidade = cidadeRepository.findByNomeCidade(cliente.getNomeCidade()).orElseThrow(() -> new CidadeNotFoundException("Cidade não encontrada: " + cliente.getNomeCidade()));
		cliente.setCidade(cidade);
		
		cliente.setIdade(calcularIdade(cliente.getDataNascimento()));
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente consultaClientePorNome(String nomeCliente) throws Exception {
		return clienteRepository.findByNomeCompleto(nomeCliente).orElseThrow(() -> new ClienteNotFoundExceptionByNome(nomeCliente));
	}

	@Override
	public Cliente consultaclintePorId(String idCliente) throws Exception {
		return clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteNotFoundExceptionById(idCliente));
		
	}

	@Override
	public void removerCliente(String idCliente) throws Exception {
		clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteNotFoundExceptionById(idCliente));
		clienteRepository.deleteById(idCliente);
	}
	
	@Override
	public Cliente alterarNomeCliente(String idCliente, Cliente cliente) {
		Cliente clienteRetorno = clienteRepository.findById(cliente.getId())
				.orElseThrow(() -> new ClienteNotFoundExceptionById(cliente.getId()));
		
		clienteRetorno.setNomeCompleto(cliente.getNomeCompleto());
		return clienteRepository.save(clienteRetorno);
	}
	
	private int calcularIdade(Date dataNascimento) {
		Calendar nascimento = new GregorianCalendar();
		nascimento.setTime(dataNascimento);
		Calendar dataAtual = Calendar.getInstance();
		
		int idade = dataAtual.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
		
		if(dataAtual.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) idade--;
		
		return idade;
	}

	@Override
	public List<Cliente> retornarTodosClientes() {
		return clienteRepository.findAll();
	}
}
