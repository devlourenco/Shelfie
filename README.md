# Shelfie

API REST para gerenciamento de livros e acompanhamento de leitura, desenvolvida com **Java e Spring Boot**.

O projeto foi criado com o objetivo de praticar desenvolvimento Back-end utilizando conceitos presentes em aplicações reais, como arquitetura em camadas, persistência de dados, DTOs, regras de negócio, tratamento de exceções, documentação de API e testes automatizados.

---

## Funcionalidades

- [x] Cadastro de livros
- [x] Listagem de todos os livros
- [x] Busca por ID
- [x] Busca por título
- [x] Busca por autor
- [x] Busca por gênero
- [x] Busca por status de leitura
- [x] Busca por avaliação
- [x] Atualização parcial de livros
- [x] Exclusão de livros
- [x] Identificação de livros duplicados
- [x] Exceções personalizadas
- [x] Tratamento global de exceções
- [x] Validação dos dados de cadastro
- [x] Documentação com Swagger / OpenAPI
- [x] Testes unitários da camada de serviço

---

## Tecnologias

| Tecnologia | Utilização |
|---|---|
| **Java** | Linguagem principal |
| **Spring Boot** | Estrutura da aplicação |
| **Spring Web MVC** | Construção dos endpoints REST |
| **Spring Data JPA** | Acesso e persistência dos dados |
| **Hibernate** | ORM |
| **H2 Database** | Banco de dados utilizado no desenvolvimento |
| **Bean Validation** | Validação dos dados recebidos |
| **Maven** | Build e gerenciamento de dependências |
| **Lombok** | Redução de código repetitivo |
| **Swagger / OpenAPI** | Documentação interativa da API |
| **JUnit / Mockito** | Testes unitários |
| **Git / GitHub** | Versionamento do projeto |

---

## Arquitetura

O Shelfie utiliza uma arquitetura em camadas para separar as responsabilidades da aplicação.

```text
src/main/java/br/com/Shelfie
│
├── config
│   └── OpenApiConfig.java
│
├── controller
│   └── ShelfieController.java
│
├── dto
│   └── ShelfieDTO.java
│
├── entity
│   └── ShelfieModel.java
│
├── enums
│   ├── Avaliacao.java
│   └── StatusDeLeitura.java
│
├── exception
│   ├── LivroDuplicadoException.java
│   └── LivroNaoEncontradoException.java
│
├── infra
│   └── RestExceptionHandler.java
│
├── mapper
│   └── ShelfieMapper.java
│
├── repository
│   └── ShelfieRepository.java
│
└── services
    └── ShelfieService.java
```

### Fluxo principal

```text
Request HTTP
     │
     ▼
 Controller
     │
     ▼
  Service
     │
     ▼
 Repository
     │
     ▼
 Database
```

A resposta percorre o caminho inverso até ser devolvida ao cliente.

### Responsabilidade das camadas

**Controller**

Responsável pela interface HTTP da aplicação. Recebe as requisições, encaminha os dados para a camada de serviço e constrói as respostas HTTP.

**Service**

Concentra as regras de negócio, como cadastro, verificação de duplicidade, consultas, atualização e exclusão.

**Repository**

Realiza a comunicação com o banco de dados utilizando Spring Data JPA.

**Entity**

Representa a estrutura persistida no banco de dados.

**DTO**

Define os dados transferidos pela API, evitando utilizar diretamente a entidade de persistência como contrato externo.

**Mapper**

Centraliza a conversão entre `ShelfieModel` e `ShelfieDTO`.

**Exception / Infra**

Centralizam as exceções específicas da aplicação e o tratamento global dos erros HTTP.

**Config**

Contém configurações adicionais da aplicação, incluindo a configuração da documentação OpenAPI.

---

## Estrutura de um livro

Exemplo de representação de um livro:

```json
{
  "id": 1,
  "titulo": "O Hobbit",
  "autor": "J. R. R. Tolkien",
  "genero": "Fantasia",
  "numPaginas": 310,
  "statusDeLeitura": "NAO_INICIADO",
  "avaliacao": null
}
```

---

## API

URL base local:

```text
http://localhost:8080/shelfie
```

### Endpoints

| Método | Endpoint | Retorno | Descrição |
|---|---|---|---|
| `POST` | `/shelfie` | `ShelfieDTO` | Cadastrar um livro |
| `GET` | `/shelfie` | `List<ShelfieDTO>` | Listar todos os livros |
| `GET` | `/shelfie/{id}` | `ShelfieDTO` | Buscar livro por ID |
| `GET` | `/shelfie/titulo?titulo=` | `ShelfieDTO` | Buscar livro por título |
| `GET` | `/shelfie/autor?autor=` | `List<ShelfieDTO>` | Buscar livros por autor |
| `GET` | `/shelfie/genero?genero=` | `List<ShelfieDTO>` | Buscar livros por gênero |
| `GET` | `/shelfie/status?statusDeLeitura=` | `List<ShelfieDTO>` | Buscar livros por status de leitura |
| `GET` | `/shelfie/avaliacao?avaliacao=` | `List<ShelfieDTO>` | Buscar livros por avaliação |
| `PATCH` | `/shelfie/{id}` | `ShelfieDTO` | Atualizar parcialmente um livro |
| `DELETE` | `/shelfie/{id}` | Sem corpo | Excluir um livro |

---

## Cadastro

### Request

```http
POST /shelfie
Content-Type: application/json
```

```json
{
  "titulo": "O Hobbit",
  "autor": "J. R. R. Tolkien",
  "genero": "Fantasia",
  "numPaginas": 310,
  "statusDeLeitura": "NAO_INICIADO",
  "avaliacao": null
}
```

### Response

```http
201 Created
```

```json
{
  "id": 1,
  "titulo": "O Hobbit",
  "autor": "J. R. R. Tolkien",
  "genero": "Fantasia",
  "numPaginas": 310,
  "statusDeLeitura": "NAO_INICIADO",
  "avaliacao": null
}
```

---

## Filtros

Além da consulta geral e da busca por ID, a API permite consultar livros utilizando diferentes critérios.

### Título

```http
GET /shelfie/titulo?titulo=O Hobbit
```

A busca por título retorna o livro correspondente ao título informado.

### Autor

```http
GET /shelfie/autor?autor=J. R. R. Tolkien
```

Pode retornar múltiplos livros cadastrados para o mesmo autor.

### Gênero

```http
GET /shelfie/genero?genero=Fantasia
```

Retorna os livros pertencentes ao gênero informado.

### Status de leitura

```http
GET /shelfie/status?statusDeLeitura=EM_ANDAMENTO
```

Retorna os livros que possuem o status informado.

### Avaliação

```http
GET /shelfie/avaliacao?avaliacao=EXCELENTE
```

Retorna os livros que possuem a avaliação informada.

---

## Atualização parcial

O Shelfie utiliza `PATCH` para atualização de livros.

Isso permite alterar somente os campos enviados na requisição, mantendo os valores existentes nos campos não informados.

Exemplo:

```http
PATCH /shelfie/1
```

```json
{
  "statusDeLeitura": "EM_ANDAMENTO"
}
```

Resposta:

```http
200 OK
```

---

## Exclusão

```http
DELETE /shelfie/1
```

Resposta:

```http
204 No Content
```

---

## ⚠️ Tratamento de exceções

A aplicação possui tratamento global de exceções, permitindo centralizar a conversão de erros de domínio em respostas HTTP.

Entre os principais casos tratados estão:

```text
Livro não encontrado → 404 Not Found
Livro duplicado       → 409 Conflict
```

As exceções de domínio possuem classes específicas:

```text
LivroNaoEncontradoException
LivroDuplicadoException
```

O tratamento centralizado evita espalhar lógica relacionada a erros pelos controllers.

---

## Códigos HTTP

Os principais endpoints utilizam respostas coerentes com suas respectivas operações:

```text
POST    → 201 Created
GET     → 200 OK
PATCH   → 200 OK
DELETE  → 204 No Content
```

Erros de domínio tratados:

```text
Livro não encontrado → 404 Not Found
Livro duplicado       → 409 Conflict
```

---

## 📘 Swagger / OpenAPI

A API possui documentação interativa utilizando **Swagger/OpenAPI**.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

A interface permite visualizar:

- endpoints disponíveis;
- métodos HTTP;
- parâmetros;
- modelos utilizados pela API;
- operações disponíveis.

Também é possível executar requisições diretamente pela interface do Swagger.

---

## Testes

O projeto possui testes automatizados para a camada de serviço.

Os testes unitários verificam comportamentos relacionados às regras de negócio da aplicação e ajudam a garantir que alterações futuras não quebrem funcionalidades existentes.

Estrutura atual:

```text
src/test/java/br/com/Shelfie
│
├── ShelfieModelApplicationTests.java
│
└── services
    └── ShelfieServiceTest.java
```

### Executando os testes

Windows:

```bash
.\mvnw.cmd clean test
```

Linux / macOS:

```bash
./mvnw clean test
```

O build da versão atual é concluído com sucesso.

---

## Decisões técnicas

### `PATCH` em vez de `PUT`

A atualização inicialmente utilizava `PUT`.

Durante a revisão da API, foi identificado que a implementação preservava os valores existentes quando determinado campo não era enviado.

Como esse comportamento representa uma **atualização parcial**, o endpoint passou a utilizar `PATCH`.

### Spring MVC em vez de WebFlux

Durante o desenvolvimento, o projeto chegou a possuir dependências de Spring MVC e WebFlux simultaneamente.

Como a aplicação não utiliza programação reativa, as dependências relacionadas ao WebFlux foram removidas.

Isso mantém o projeto mais simples e coerente com sua arquitetura atual.

### DTO em vez de expor diretamente a Entity

A entidade representa a estrutura persistida no banco de dados, enquanto o DTO representa os dados transferidos pela aplicação.

Essa separação evita utilizar diretamente o modelo de persistência como contrato externo da API.

### Mapper

A conversão entre DTO e Entity é centralizada no `ShelfieMapper`.

Isso evita espalhar lógica de conversão pelas outras camadas da aplicação.

### Tratamento global de erros

As exceções específicas da aplicação são tratadas de maneira centralizada.

Dessa forma, os controllers permanecem focados no recebimento das requisições e construção das respostas HTTP.

---

## ▶️ Executando o projeto

### Pré-requisitos

Para executar o Shelfie localmente:

- Git
- JDK compatível com o projeto

O projeto utiliza **Maven Wrapper**, portanto não é necessário instalar Maven globalmente.

### Clone

```bash
git clone https://github.com/devlourenco/Shelfie.git
```

Entre na pasta:

```bash
cd Shelfie
```

### Executar os testes

Windows:

```bash
.\mvnw.cmd clean test
```

Linux / macOS:

```bash
./mvnw clean test
```

### Iniciar a aplicação

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Linux / macOS:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada localmente.

API:

```text
http://localhost:8080/shelfie
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Banco de dados

Atualmente o Shelfie utiliza **H2 Database**.

A utilização do H2 facilita a execução e os testes locais sem exigir a configuração de um servidor externo de banco de dados.

Em uma futura evolução, o projeto poderá utilizar um banco persistente como PostgreSQL.

---

## Aprendizados

Durante o desenvolvimento do Shelfie foram praticados conceitos como:

- desenvolvimento de APIs REST;
- Java e orientação a objetos;
- Spring Boot;
- arquitetura em camadas;
- Spring Web MVC;
- Spring Data JPA;
- persistência de dados;
- DTOs;
- mapeamento entre DTO e Entity;
- regras de negócio;
- tratamento global de exceções;
- exceções personalizadas;
- códigos de status HTTP;
- contratos REST;
- Bean Validation;
- documentação com Swagger/OpenAPI;
- testes unitários;
- JUnit e Mockito;
- Maven;
- Git e GitHub;
- organização de commits;
- revisão e refatoração de código.

O projeto também foi desenvolvido de maneira incremental, utilizando um ciclo de implementação, teste, revisão e refatoração.

```text
Definir uma tarefa
       ↓
Implementar
       ↓
Testar
       ↓
Revisar
       ↓
Refatorar
       ↓
Executar o build
       ↓
Revisar alterações
       ↓
Criar um commit focado
```

---

## Próximas evoluções

A versão atual do Shelfie está concluída.

Possíveis melhorias para versões futuras:

- [ ] Melhorar as regras de duplicidade
- [ ] Padronizar o corpo das respostas de erro
- [ ] Criar testes de integração
- [ ] Adicionar paginação e ordenação
- [ ] Utilizar PostgreSQL
- [ ] Adicionar migrations com Flyway
- [ ] Containerizar a aplicação com Docker
- [ ] Criar pipeline com GitHub Actions
- [ ] Realizar deploy da API
- [ ] Adicionar autenticação
- [ ] Implementar usuários
- [ ] Adicionar progresso de leitura
- [ ] Registrar datas de início e conclusão
- [ ] Criar histórico de leituras

---

## Status do projeto

**Versão atual concluída.**

O Shelfie possui atualmente:

- CRUD de livros;
- filtros de consulta;
- arquitetura em camadas;
- DTO e Mapper;
- persistência com JPA;
- regras de negócio;
- Bean Validation;
- tratamento global de exceções;
- documentação Swagger/OpenAPI;
- testes unitários.

Novas funcionalidades poderão ser adicionadas futuramente em novas versões do projeto.

---

## Autor

**Guilherme Simões Lourenço**

Estudante de Sistemas de Informação, aprofundando conhecimentos em desenvolvimento de software, Java, Spring Boot, APIs REST e bancos de dados.

[GitHub](https://github.com/devlourenco)

[LinkedIn](https://www.linkedin.com/in/guilherme-simoes-lourenco/)

---

## Feedback

Feedbacks sobre código, arquitetura, testes, organização e boas práticas são bem-vindos.

Caso encontre algo que possa ser melhorado, fique à vontade para abrir uma issue no repositório.