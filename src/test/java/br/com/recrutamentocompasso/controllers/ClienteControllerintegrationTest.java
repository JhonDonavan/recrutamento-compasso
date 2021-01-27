package br.com.recrutamentocompasso.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.recrutamentocompasso.model.Cliente;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteControllerintegrationTest {
	
	@Autowired
	ClientesController clienteController;
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mock;
	
	private static final String NOT_FOUND = "Not found";

	private static final String ID_NOT_FOUND = "600b05f7c774f41f03cf4904";
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testConsultaClientePorNome() throws Exception {
		Cliente clienteRandom = retornarRandomCliente();
		String url = "/api/clientes/nome?nomeCompleto=".concat(clienteRandom.getNomeCompleto());
		mock.perform(get(url))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testConsultaClientePorNomeNotFound() throws Exception {
				
		String url = "/api/clientes/nome?nomeCompleto=".concat(NOT_FOUND);
		
		mock.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testConsultaClientePorId() throws Exception {
		
		Cliente clienteRandom = retornarRandomCliente();
		
		String url = "/api/clientes/id?id=".concat(clienteRandom.getId());
		
		mock.perform(get(url))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testConsultaClientePorIdNotFound() throws Exception {
				
		String url = "/api/clientes/id?id=".concat(ID_NOT_FOUND);
		
		mock.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void cadastrarCliente() throws Exception {
				
		String nomeAleatorio = "João das Couves ".concat(Integer.toString(geraNumeroAleatorio()));
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = (Date)formatter.parse("02/01/87");
				
		String url = "/api/clientes/";
		
		mock.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Cliente(null, nomeAleatorio, "Masculino", dataNascimento, 0 , null, "Duque de Caxias"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(notNullValue())));
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
	
	private static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
