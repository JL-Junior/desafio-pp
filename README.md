# Projeto Pedagógico de Backend

Esse projeto é desenvolvido com finalidade pedagógica e de demonstração de proficiência.

Aqui usaremos um desafio de backend para explorar o framework Quarkus no desenvolvimento de um microsserviço de transações, explorando os conceitos de boas práticas de desenvolvimento de software.

## Setup de Ambiente

Podemos observar que há um arquivo na raiz deste projeto chamado "docker-compose.yaml". Neste arquivo são definidos os seguintes serviços de infraestrutura:
- db (postgres): banco de dados necessário para persistência das informações transacionais e de parâmetros.
- app: imagem da aplicação para observarmos seu funcionamento em ambiente "produtivo".
- sonarqube: serviço de análise de cobertura de testes unitários e **code_smells**
- metabase: serviço de visualização de base de dados que nos permite validar se o funcionamento do serviço é adequado.

Para iniciar a aplicação, primeiro devemos preparar sua imagem para rodar em um container Docker. Para isso, utilize os comandos a seguir:

- Preparar o projeto:
```shell script
./gradlew build
```

- Preparar imagem para rodar em container
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/desafio.pp-jvm
```

Feito isso, terá a imagem docker da aplicação disponível para iniciar a aplicação.

Com o comando a seguir, você irá iniciar a aplicação e os serviços de infraestrutura necessários para seu funcionamento.

```shell script
docker compose up -d
```

Então utilizando a seguinte requisição, deve receber uma resposta indicando sucesso na operação:

```shell script
curl --location 'http://localhost:8080/transactions' --header 'Content-Type: application/json' --data '{ "amount": 10.00, "document_sender": 100100100,"document_receiver": 200200200}'
```

## Conceitos Abordados

### Code First
Utilizando a biblioteca **quarkus-smallrye-openapi** podemos gerar um arquivo de swagger a partir de nossas classes e entidades desenvolvidas em código Java, como podemos ver nessa [classe](./src/main/java/org/desafio/presentation/api/TransactionAPI.java)

### Repository Pattern
Utilizando a biblioteca **Panache** podemos abstrair as operações nos bancos de dados e manipular a leitura e persistência de dados de maneira fácil e intuitiva.

### Fail Fast
Utilizando a biblioteca **hibernate-validator** podemos definir regras de validações com anotações simples e eficientes, permitindo-nos validar os campos dos objetos de entrada antes mesmo de iniciarmos as funções de negócio.

### Exception Handling
Neste projeto, podemos observar a centralização de tratamento de erros, atribuindo essa tarefa a classes específicas para essa função, como podemos observar [nessa pasta](src/main/java/org/desafio/presentation/exceptionhandler)

