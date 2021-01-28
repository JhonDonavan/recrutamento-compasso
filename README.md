# Projeto recrutamento-compasso


# Tecnologias utilizadas
- JAVA 8
- SPRING BOOT
- SWAGGER
- MAVEN
- NOSQL
- DOCKER (Não concluído - não tive tempo de ver o erro, por isso não há garantia de que vá funcionar utilizando docker. Corrigirei mais a frente)

.

#### Regras de negocios:
Para a classe Cliente, não é necessário informar a idade, uma vez que é informado data nascimento, a aplicação já calcula a idade do cliente com base nada data informada.

Existe um relacionamento entre as classes Cliente e Cidade. A Entidade Cliente possui um campo do tipo String chamado *nomeCidade*, ao informar um valor para o campo *nomeCidade*, ao persistir um cliente, é verificado se existe alguma cidade com o nome informado, caso contrario, será necessário cadastrar a cidade desejada.

#### Observações:   
 - A base de dados utilizada, foi uma base NoSQL hospedado na nuvem. Ou seja, já há massa para realização dos testes.
   Para maiores informações sobre onde está hospedado, segue link: https://www.mongodb.com/cloud/atlas

# Tutorial para execução e testes da api.

### Para execução dos testes unitários e de integração, executar o comando abaixo na raíz do projeto:
```sh
$ mvn test
```
### Para "startar" a aplicação, executar o comando abaixo na raiz do projeto:
```sh
$./mvnw spring-boot:run
```

### Para acessar a documentação dos endpoints gerado pelo SWAGGER, acessar a url:
    url http://localhost:8080/swagger-ui.html


# ENDPOINTS CIDADES:
| Método | Endpoint | Exemplos |
| ------ | ------   | ------   |
| POST | /api/cidades/                      | localhost:8080/api/cidades/|
| GET  | /api/cidades/cidade/{cidadeNome} | [localhost:8080/api/cidades/cidade/Rio de Janeiro |
| GET  |  /api/cidades/estado/{estadoNome}  |[localhost:8080/api/cidades/estado/Rio de Janeiro |


# ENDPOINTS CLIENTES:
| Método | Endpoint | Exemplos |
| ------ | ------   | ------   |
| POST | /api/clientes/ | localhost:8080/api/clientes/ |
| PUT  |  /api/clientes/{id}|localhost:8080/api/cidades/cidade/Rio de Janeiro |
| DELETE  |  /api/clientes/{id}   |localhost:8080/api/clientes/600b1b67d5e28d29946f996b |
| GET | /api/clientes/all        | localhost:8080/api/clientes/all|
| GET  |  /api/clientes/id      | localhost:8080/api/clientes/id?id=600b1b67d5e28d29946f996b |
| GET  |  /api/clientes/nome     |localhost:8080/api/clientes/nome?nomeCompleto=Diego Gomes |


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
##### Alterar nome do cliente:    
    url: localhost:8080/api/clientes/6010202bee6bd909657fa888
    {
        "id" : "6010202bee6bd909657fa888",
        "nomeCompleto" : "Douglas Oliveira da Silva",
        "sexo" : "Masculino",
        "dataNascimento" : "09-06-1987",
        "nomeCidade" : "Teresopolis"
    }
    
    
    
### TODO
 - Corrigir erro na criação de image docker.
 - Subir nos servidores da heroku e fazer a integração continua com o github.
 - Alterar o processo de cadastor de clientes, caso não existe a cidade do cliente, persistir junto com o cliente.
