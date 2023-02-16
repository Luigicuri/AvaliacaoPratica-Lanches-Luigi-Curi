# Avaliacao-Projeto-Lanches-API
Teste prático - RESTful API para registro de venda de lanches

## 1. Tecnologias utilizadas:
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Spring Security
- Swagger OpenAPI
- Postman para requisições HTTP(recomendado)

## 2. Requisitos para o building e o running da aplicação:
- PostgreSQL para uso do banco de dados
- JDK 18
- IntelliJ, Eclipse ou outra IDE
- Apache Maven

Para Autenticação login Spring Security
> Usuário: admin
> 
> Senha: lanches

## 2.1. Configurando o Banco de Dados

### Passo 1
Para que a aplicação funcione por completo, é necessário criar uma Database através do PostgreSQL e inserir o nome da mesma no arquivo de configuração ***/src/main/resources/application.properties***
Crie uma nova database com o nome que desejar:

![image](https://user-images.githubusercontent.com/74516086/219408531-c960a10f-563d-47cd-94f0-d3453c0329f5.png)

Neste exemplo será criada a database com o nome "lanches":

![image](https://user-images.githubusercontent.com/74516086/219408990-b5293574-f772-42eb-a944-a04302aafd7b.png)

### Passo 2
Abra o arquivo de configuração do projeto spring /src/main/resources/application.properties , copie e cole estes campos de configuração abaixo. Caso a porta utilizada e o nome da database sejam diferentes, altere para os nomes e portas utilizadas no seu PostgreSQL.

``` 
spring.datasource.url=jdbc:postgresql://localhost:5432/lanches
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto = create

logging.level.org.springframework.security=debug 
```
Caso necessário, altere os campos para seus respectivos nomes de usuário e senha:
>spring.datasource.username=postgres
>
>spring.datasource.password=root
>
### Passo 3
Execute o boot da aplicação, ao executar a aplicação serão criadas as tabelas e colunas no banco de dados.

## 3. Endpoints da API para Requisições HTTP

Será necessário primeiro realizar autenticação no Spring Security, explicado no próximo item "4. Acessando a API"

***Lanches***

POST - Fazer pedido Lanche - POST http://localhost:8080/api/lanches

GET - Listar TODOS os pedidos Lanche - GET http://localhost:8080/api/lanches

GET - Buscar pedido Lanche pelo ID - GET http://localhost:8080/api/lanches/{id do Lanche}

PUT - Atualizar dados do pedido Lanche caso necessário - PUT http://localhost:8080/api/lanches/{id do Lanche}

DELETE - Deletar determinado lanche - DELETE http://localhost:8080/api/lanches/{id do Lanche}

DELETE - Deletar TODOS os lanches - DELETE http://localhost:8080/api/lanches

### 3.1. Exemplo de retorno

```
{
    "id": 1312,
    "cliente": "Luigi",
    "nomeLanche": "X_EGG",
    "adicional": [
        "QUEIJO",
        "OVO"
    ],
    "valorTotal": 7.6
}
```

## 4. Acessando a API:

### 4.1. Acessando API pelo Swagger
Para consultar os endpoints através do Swagger, acesse: http://localhost:8080/swagger-ui/index.html
#### 4.1.1. Autenticação Spring Security
É necessário realizar autenticação de login na página
> Usuário: admin
> 
> Senha: lanches


Após autenticar, será redirecionado para a página onde estarão os endpoints

![image](https://user-images.githubusercontent.com/74516086/219416470-9305d03e-4fe0-4df5-b8d1-e1a4eb4b8ed0.png)

### 4.2. Acessando API pelo Postman
Abra o Postman e insira um dos endpoints da API de lanches
#### 4.2.1. Configurando Basic Auth
Clique na aba "Authorization", selecione o tipo "Basic auth" e então, insira nos campos Username e Password os respectivos usuários e senha do Spring Security

> Usuário: admin
> 
> Senha: lanches

![image](https://user-images.githubusercontent.com/74516086/219418439-d007f019-d4b2-47d9-b13f-187ea533ee1e.png)

## 5. Testando API. Exemplo: fazendo pedido de Lanche

Após configurarmos tudo acima, realizaremos uma requisição POST para cadastrar um lanche. É possível solicitar ingredientes adicionais para personalizar o lanche caso o cliente desejar, acrescentando com o valor padrão do lanche.

Corpo do envio:

```
{
    "cliente" : "Luigi",
    "nomeLanche" : "X_EGG",
    "adicional" : [
       "QUEIJO", "OVO"
    ]
}
``` 

Após enviar a requisição. Será gerado um Id referente ao Lanche.
Será gerado tamém o valor total a pagar, o mesmo é calculado com base no valor padrão do lanche + quantidade de ingredientes adicionais com seus respectivos valores.

Resposta:
```
{
    "id": 1652,
    "cliente": "Luigi",
    "nomeLanche": "X_EGG",
    "adicional": [
        "QUEIJO",
        "OVO"
    ],
    "valorTotal": 7.6
}
```
