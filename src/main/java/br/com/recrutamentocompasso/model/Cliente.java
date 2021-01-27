package br.com.recrutamentocompasso.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "clientes")
public class Cliente {
	
	@MongoId(value = FieldType.OBJECT_ID)
	private String id;
	
	@NotBlank(message = "{message.cliente.nomeCompleto.notblank}")
	private String nomeCompleto;

	@NotBlank(message = "{message.cliente.sexo.notblank}")
	private String sexo;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;
		
	@NotBlank(message = "{message.cliente.nomeCidade.notblank}")
	private String nomeCidade;
	
	private int idade;
	private Cidade cidade;
	
	public Cliente(String id, String nomeCompleto, String sexo, Date dataNascimento, int idade, Cidade cidade, String nomeCidade) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidade = cidade;
		this.nomeCidade = nomeCidade;
	}
	
	public Cliente() {

	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getId() {
		return id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
}
