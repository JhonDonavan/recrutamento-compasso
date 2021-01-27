# Projeto recrutamento-compasso

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

# Tecnologias utilizadas
- JAVA 8
- SPRING BOOT
- SWAGGER
- MAVEN
- NOSQL
- DOCKER (Não concluído - não tive tempo de ver o erro, por isso não há garantia de que vá funcionar utilizando docker. Corrigirei mais a frente)

.

##### Regras de negocios:
Para a classe Cliente, não é necessário informar a idade, uma vez que é informado data nascimento, a aplicação já calcula a idade do cliente com base nada data informada.

Existe um relacionamento entre as classes Cliente e Cidade. A Entidade Cliente possui um campo do tipo String chamado *nomeCidade*, ao informar um valor para o campo *nomeCidade*, ao persistir um cliente, é verificado se existe alguma cidade com o nome informado, caso contrario, será necessário cadastrar a cidade desejada.

##### Observações:   
 - A base de dados utilizada, foi uma base NoSQL hospedado na nuvem. Ou seja, já há massa para realização dos testes.
   para maiores informações sobre onde está hospedado, segue link: https://www.mongodb.com/cloud/atlas

# Tutorial para execução e testes da api.

### Para execução dos testes unitários e de integração executar o comando abaixo na raízdo projeto:
```sh
$ mvn test
```
### Para "startar" a aplicação , executar o comando abaixo, na raiz do projeto:
```sh
$./mvnw spring-boot:run
```


# ENDPOINTS CIDADES:
| Método | Endpoint | Exemplos |
| ------ | ------   | ------   |
| POST | [/api/cidades/][PlDb]                      | [localhost:8080/api/cidades/][PlDb] |
| GET  |  [/api/cidades/cidade/{cidadeNome}] [PlDb] | [localhost:8080/api/cidades/cidade/Rio de Janeiro][PlGh] |
| GET  |  [/api/cidades/estado/{estadoNome}][PlDb]  |[localhost:8080/api/cidades/estado/Rio de Janeiro][PlGd] |


# ENDPOINTS CLIENTES:
| Método | Endpoint | Exemplos |
| ------ | ------   | ------   |
| POST | [/api/clientes/][PlDb]           | [localhost:8080/api/clientes/][PlDb] |
| PUT  |  [/api/clientes/{id}] [PlDb]     | [localhost:8080/api/cidades/cidade/Rio de Janeiro][PlGh] |
| DELETE  |  [/api/clientes/{id}][PlDb]   |[localhost:8080/api/clientes/600b1b67d5e28d29946f996b][PlGd] |
| GET | [/api/clientes/all][PlDb]         | [localhost:8080/api/clientes/all][PlDb] |
| GET  |  [/api/clientes/id] [PlDb]       | [localhost:8080/api/clientes/id?id=600b1b67d5e28d29946f996b][PlGh] |
| GET  |  [/api/clientes/nome][PlDb]      |[localhost:8080/api/clientes/nome?nomeCompleto=Diego Gomes][PlGd] |


#### Exemplos de requisição POST E PUT
##### POST
##### Cadastrar cidade:
    url:  localhost:8080/api/cidades/
    {
        "nomeCidade" : "São Jose do Rio Preto",
        "nomeEstado" : "Sao Paulo"
    }
##### POST    
##### Cadastrar Cliente:    
    url:  localhost:8080/api/clientes/
    {
        "nomeCompleto" : "Douglas Oliveira",
        "sexo" : "Masculino",
        "dataNascimento" : "09-06-1987",
        "nomeCidade" : "Teresopolis"
    }

##### PUT
##### Alterar nome do cliente Cliente:    
    url: localhost:8080/api/clientes/6010202bee6bd909657fa888
    {
        "id" : "6010202bee6bd909657fa888",
        "nomeCompleto" : "Douglas Oliveira da Silva",
        "sexo" : "Masculino",
        "dataNascimento" : "09-06-1987",
        "nomeCidade" : "Teresopolis"
    }
    
    
    
### TODO
 - Corrigir erro na criação de image docker
 - Subir nos servidores da heroku e fazer a integração continua com o github
 - Alterar o processo de cadastor de clientes, caso não existe a cidade do cliente, persistir junto com o cliente.
