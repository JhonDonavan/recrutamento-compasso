package br.com.recrutamentocompasso.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.recrutamentocompasso.exceptions.ClienteCadastradoException;
import br.com.recrutamentocompasso.exceptions.ClienteNotFoundExceptionById;
import br.com.recrutamentocompasso.exceptions.ClienteNotFoundExceptionByNome;
import br.com.recrutamentocompasso.model.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteControllerTest {

	@Autowired
	ClientesController clienteController;

	private static final String NOT_FOUND = "Not found";

	private static final String ID_NOT_FOUND = "600b05f7c774f41f03cf4904";

	
	@Test
	public void testConsultaClientePorNome() throws Exception {

		Cliente clienteRandom = retornarRandomCliente();
		ResponseEntity<Cliente> clientePorNome = clienteController.consultarClientePorNome(clienteRandom.getNomeCompleto());

		assertThat(clientePorNome.getBody().getId()).isEqualTo(clienteRandom.getId());
		assertThat(clientePorNome.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	

	@Test
	public void testConsultaClientePorNomeNotFound() throws Exception {
		try {
			clienteController.consultarClientePorNome(NOT_FOUND);
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (ClienteNotFoundExceptionByNome e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}
	}

	@Test
	public void testConsultaClientePorId() throws Exception {

		Cliente clienteRandom = retornarRandomCliente();

		ResponseEntity<Cliente> clientePorId = clienteController.consultarClientePorId(clienteRandom.getId());

		assertThat(clientePorId.getBody().getId()).isEqualTo(clienteRandom.getId());
		assertThat(clientePorId.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testConsultaClientePorIdNotFound() throws Exception {
		try {
			clienteController.consultarClientePorId(ID_NOT_FOUND);
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (ClienteNotFoundExceptionById e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}
	}

	@Test
	public void testCadastrarCliente() throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = (Date)formatter.parse("02/01/87");
				
		String nomeAleatorio = "João das Couves ".concat(Integer.toString(geraNumeroAleatorio()));
		
		Cliente cliente = new Cliente();

		cliente.setDataNascimento(date);
		cliente.setNomeCompleto(nomeAleatorio);
		cliente.setSexo("masculino");
		cliente.setNomeCidade("Duque de Caxias");
		
		ResponseEntity<Cliente> clienteSalvo = clienteController.cadastrarCliente(cliente);

		assertThat(clienteSalvo.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<Cliente> clienteCadastrado = clienteController.consultarClientePorId(clienteSalvo.getBody().getId());

		assertThat(clienteSalvo.getBody().getId()).isEqualTo(clienteCadastrado.getBody().getId());
		assertThat(clienteSalvo.getBody().getDataNascimento()).isEqualTo(clienteCadastrado.getBody().getDataNascimento());
		assertThat(clienteSalvo.getBody().getIdade()).isEqualTo(clienteCadastrado.getBody().getIdade());
		assertThat(clienteSalvo.getBody().getNomeCompleto()).isEqualTo(clienteCadastrado.getBody().getNomeCompleto());
		assertThat(clienteSalvo.getBody().getSexo()).isEqualTo(clienteCadastrado.getBody().getSexo());
		assertThat(clienteSalvo.getBody().getCidade().getNomeCidade()).isEqualTo(clienteCadastrado.getBody().getCidade().getNomeCidade());
		assertThat(clienteSalvo.getBody().getCidade().getNomeEstado()).isEqualTo(clienteCadastrado.getBody().getCidade().getNomeEstado());
		assertThat(clienteSalvo.getBody().getCidade().getId()).isEqualTo(clienteCadastrado.getBody().getCidade().getId());

	}
	
	@Test
	public void testCadastrarClienteNomeRepetido() throws Exception {
	
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = (Date)formatter.parse("02/01/87");
			
			Cliente cliente = retornarRandomCliente();

			cliente.setDataNascimento(date);
			cliente.setNomeCompleto(cliente.getNomeCompleto());
			cliente.setSexo("masculino");
			cliente.setNomeCidade("Duque de Caxias");
			
			clienteController.cadastrarCliente(cliente);
			
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (ClienteCadastradoException e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}
	}
	
	@Test
	public void TestRemoverCliente() throws Exception {
		Cliente clienteRandom = retornarRandomCliente();

		try {
			ResponseEntity<?> clienteRemovido = clienteController.removerCliente(clienteRandom.getId());
			assertThat(clienteRemovido.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
			clienteController.consultarClientePorId(clienteRandom.getId());
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (ClienteNotFoundExceptionById e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}
	}
	

	@Test
	public void TestAlterarNomeCliente() throws Exception {
		Cliente clienteRandom = retornarRandomCliente();
		String nomeAntigo = clienteRandom.getNomeCompleto(); 
				
		clienteRandom.setNomeCompleto("João da Couves Neto ".concat(Integer.toString(geraNumeroAleatorio())));
		
		ResponseEntity<?> responseEntity = clienteController.alterarNomeCliente(clienteRandom.getId(), clienteRandom);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
				
		try {			
			clienteController.consultarClientePorNome(nomeAntigo);
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (ClienteNotFoundExceptionByNome e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}
	}
	
	private int geraNumeroAleatorio() {
		Random random = new Random();
		int numeroAleatorio = random.nextInt(1000);
		return numeroAleatorio;
	}
	
	private Cliente retornarRandomCliente() throws Exception {
		ResponseEntity<List<Cliente>> todosClientes = clienteController.retornarTodosClientes();
		Random random = new Random();
		int resultado = random.nextInt(todosClientes.getBody().size());

		return todosClientes.getBody().get(resultado);
	}

}
