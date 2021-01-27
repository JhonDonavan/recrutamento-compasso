package br.com.recrutamentocompasso.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.recrutamentocompasso.exceptions.CidadeCadastradaException;
import br.com.recrutamentocompasso.exceptions.CidadeNotFoundException;
import br.com.recrutamentocompasso.exceptions.EstadoNotFoundException;
import br.com.recrutamentocompasso.model.Cidade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadeControllerTest {
	
	@Autowired
	private CidadesController cidadeController;
		
	private static final String NOT_FOUND = "Not found";

	
	@Test
	public void testConsultaCidadePorNome() {	
		Cidade cidade = retornarRandonCidade();
		
		ResponseEntity<Cidade> cidadePorNome = cidadeController.RetornaCidadePorNome(cidade.getNomeCidade());
		
		assertThat(cidadePorNome.getBody().getId()).isEqualTo(cidade.getId());
		assertThat(cidadePorNome.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	
	@Test
	public void testConsultaCidadePorNomeNotFound() {		
		try {
			cidadeController.RetornaCidadePorNome(NOT_FOUND);
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (CidadeNotFoundException e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}		
	}
	
	
	@Test
	public void testConsultaCidadePorEstados() {	
		Cidade cidade = retornarRandonCidade();
				
		ResponseEntity<List<Cidade>> cidadesPorEstado = cidadeController.retornaCidadesPorEstados(cidade.getNomeEstado());
		
		assertThat(cidadesPorEstado.getBody().size()).isGreaterThan(0);
		assertThat(cidadesPorEstado.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	
	@Test
	public void testConsultaCidadePorEstadosNotFound() {		
		try {
			cidadeController.retornaCidadesPorEstados(NOT_FOUND);
			assertTrue("Exceção falhou ao ser lançada.", false);
		} catch (EstadoNotFoundException e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}		
	}
	
	@Test
	public void testSalvarCidade() {
		Cidade cidade = new Cidade();
		
		String nomeCidadeAleatorio = "Gotham City ".concat(Integer.toString(geraNumeroAleatorio()));
		
		
		cidade.setNomeCidade(nomeCidadeAleatorio);
		cidade.setNomeEstado(nomeCidadeAleatorio);
		
		ResponseEntity<Cidade> CidadeSalva = cidadeController.CriarCidade(cidade);
		
		assertThat(CidadeSalva.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		ResponseEntity<Cidade> cidadeCriada = cidadeController.RetornaCidadePorNome(cidade.getNomeCidade());
		
		assertThat(CidadeSalva.getBody().getNomeCidade()).isEqualTo(cidadeCriada.getBody().getNomeCidade());
		assertThat(CidadeSalva.getBody().getNomeEstado()).isEqualTo(cidadeCriada.getBody().getNomeEstado());
		
	}
	
	@Test
	public void testSalvarCidadeCadastrada() {
		Cidade cidade = new Cidade();
		
		Cidade cidadeExistente = retornarRandonCidade();
		
		cidade.setNomeCidade(cidadeExistente.getNomeCidade());
		cidade.setNomeEstado(cidadeExistente.getNomeEstado());
		
		try {
		cidadeController.CriarCidade(cidade);
		assertTrue("Exceção falhou ao ser lançada.", false);
		}catch (CidadeCadastradaException e) {
			assertTrue("Exceção lançada com sucesso.", true);
		}	
	}
	
	private Cidade retornarRandonCidade() {
		ResponseEntity<List<Cidade>> todasCidades = cidadeController.retornarTodasCidades();
		Random random = new Random();
		int resultado = random.nextInt(todasCidades.getBody().size());
		
		return todasCidades.getBody().get(resultado);
	}
	
	private int geraNumeroAleatorio() {
		Random random = new Random();
		int numeroAleatorio = random.nextInt(1000);
		return numeroAleatorio;
	}
	

}
