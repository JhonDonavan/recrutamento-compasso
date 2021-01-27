package br.com.recrutamentocompasso.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "cidades")
public class Cidade {
	
	@MongoId(value = FieldType.OBJECT_ID)
	private String id;
	
	private String nomeCidade;
	
	private String nomeEstado;

	public Cidade(String id, String nomeCidade, String nomeEstado) {
		super();
		this.id = id;
		this.nomeCidade = nomeCidade;
		this.nomeEstado = nomeEstado;
	}
	
	public Cidade() {
	
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getId() {
		return id;
	}


	
	
	
		
}
