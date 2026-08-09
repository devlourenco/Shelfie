# 📚 Shelfie

> API REST para gerenciamento de livros e acompanhamento de leituras, desenvolvida com Java e Spring Boot como projeto de estudo e evolução em desenvolvimento Back-end.

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![GitHub](https://img.shields.io/badge/GitHub-devlourenco-181717?style=flat-square&logo=github)](https://github.com/devlourenco)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=flat-square)

---

## Sobre o projeto

**Shelfie** é uma API REST criada para cadastrar, consultar, atualizar e remover livros, além de acompanhar informações relacionadas à leitura, como status e avaliação.

O projeto faz parte dos meus estudos de **Java, Spring Boot e desenvolvimento Back-end**.

Sou estudante de **Sistemas de Informação** e estou me preparando para conquistar minha **primeira oportunidade profissional como estagiário em desenvolvimento de software**.

Por isso, o objetivo do Shelfie vai além de construir um CRUD funcional. Estou utilizando o projeto para praticar conceitos encontrados no desenvolvimento de aplicações reais, melhorar minha organização de código, aprender boas práticas e desenvolver maior autonomia na resolução de problemas.

O desenvolvimento acontece de forma incremental: implemento uma funcionalidade, testo seu comportamento, reviso decisões técnicas e registro a evolução através do Git.

---

## 🎯 Objetivos de aprendizado

Com o Shelfie estou praticando:

- desenvolvimento de APIs REST;
- Java e orientação a objetos;
- Spring Boot;
- arquitetura em camadas;
- Spring Data JPA;
- persistência de dados;
- DTOs;
- mapeamento entre DTO e Entity;
- tratamento global de exceções;
- exceções personalizadas;
- códigos de status HTTP;
- contratos REST;
- regras de negócio;
- Maven;
- Git e GitHub;
- testes manuais de API com Postman;
- organização de commits;
- leitura, revisão e refatoração de código.

Mais do que fazer uma funcionalidade funcionar, procuro entender **por que cada classe, anotação e decisão existe dentro da aplicação**.

---

# ✅ Funcionalidades atuais

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
- [x] Respostas HTTP adequadas para as principais operações

---

# 🛠️ Tecnologias

| Tecnologia | Utilização |
|---|---|
| **Java** | Linguagem principal |
| **Spring Boot** | Estrutura da aplicação |
| **Spring Web MVC** | Construção dos endpoints REST |
| **Spring Data JPA** | Comunicação com a camada de persistência |
| **Hibernate** | ORM |
| **H2 Database** | Banco de dados durante o desenvolvimento |
| **Maven** | Gerenciamento de dependências e build |
| **Lombok** | Redução de código repetitivo |
| **Git / GitHub** | Versionamento |
| **Postman** | Testes manuais da API |

---

# 🏗️ Arquitetura

O projeto utiliza uma arquitetura em camadas para separar as responsabilidades da aplicação.

```text
src/main/java/br/com/Shelfie
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

### Fluxo simplificado

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

---

# 📂 Responsabilidade das camadas

### Controller

Responsável pela interface HTTP da aplicação.

Recebe as requisições, encaminha os dados para a camada de serviço e constrói as respostas HTTP.

### Service

Concentra as regras de negócio da aplicação.

É nessa camada que são tratadas operações como cadastro, verificação de duplicidade, busca, atualização e exclusão.

### Repository

Realiza a comunicação com o banco de dados utilizando Spring Data JPA.

### Entity

Representa a estrutura persistida no banco.

### DTO

É utilizado para transferência de dados entre as camadas, evitando utilizar diretamente a entidade como contrato externo da API.

### Mapper

Responsável pela conversão entre `ShelfieModel` e `ShelfieDTO`.

### Exception / Infra

Centralizam as exceções específicas da aplicação e o tratamento global dos erros HTTP.

---

# 📖 Estrutura de um livro

Exemplo:

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

# 🌐 API

URL base local:

```text
http://localhost:8080/shelfie
```

## Endpoints

| Método | Endpoint | Descrição | Status principal |
|---|---|---|---|
| `POST` | `/shelfie` | Cadastrar um livro | `201 Created` |
| `GET` | `/shelfie` | Listar todos os livros | `200 OK` |
| `GET` | `/shelfie/{id}` | Buscar livro por ID | `200 OK` |
| `GET` | `/shelfie/titulo?titulo=` | Buscar por título | `200 OK` |
| `GET` | `/shelfie/autor?autor=` | Buscar por autor | `200 OK` |
| `GET` | `/shelfie/genero?genero=` | Buscar por gênero | `200 OK` |
| `GET` | `/shelfie/status?statusDeLeitura=` | Buscar por status | `200 OK` |
| `GET` | `/shelfie/avaliacao?avaliacao=` | Buscar por avaliação | `200 OK` |
| `PATCH` | `/shelfie/{id}` | Atualizar parcialmente um livro | `200 OK` |
| `DELETE` | `/shelfie/{id}` | Excluir um livro | `204 No Content` |

---

# 📥 Cadastro

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

Exemplo:

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

# ✏️ Atualização parcial

A atualização utiliza `PATCH`.

Isso permite alterar apenas os campos necessários, mantendo os valores já existentes nos campos não enviados.

```http
PATCH /shelfie/1
```

Body:

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

# 🗑️ Exclusão

```http
DELETE /shelfie/1
```

Resposta:

```http
204 No Content
```

---

# ⚠️ Tratamento de exceções

O projeto utiliza:

```java
@RestControllerAdvice
```

para centralizar o tratamento das exceções da API.

Isso evita espalhar lógica de tratamento de erros pelos controllers.

## Livro não encontrado

Caso um livro solicitado não exista:

```http
404 Not Found
```

Exemplo:

```text
Livro com o id '999' não encontrado.
```

## Livro duplicado

Caso seja realizado um novo cadastro de um livro já existente:

```http
409 Conflict
```

Exemplo:

```text
O livro já está cadastrado.
```

---

# 🧠 Algumas decisões técnicas

## `PUT` → `PATCH`

A atualização inicialmente utilizava `PUT`.

Durante a revisão da API, identifiquei que a implementação preservava os valores existentes quando determinado campo não era enviado.

Como esse comportamento caracteriza uma **atualização parcial**, o endpoint foi alterado para `PATCH`.

---

## Códigos HTTP

Os principais endpoints foram ajustados para utilizar respostas coerentes com suas operações.

```text
POST    → 201 Created
GET     → 200 OK
PATCH   → 200 OK
DELETE  → 204 No Content
```

Erros de domínio atualmente tratados:

```text
Livro não encontrado → 404 Not Found
Livro duplicado      → 409 Conflict
```

---

## Spring MVC em vez de WebFlux

O projeto inicialmente possuía dependências de Spring MVC e WebFlux simultaneamente.

Durante a revisão da arquitetura, identifiquei que a aplicação não utilizava programação reativa, como:

```text
Mono
Flux
WebClient
Reactor
```

As dependências do WebFlux foram removidas para manter o projeto mais simples e coerente com sua arquitetura atual.

---

# 🧪 Testes atuais

Os endpoints estão sendo testados manualmente utilizando o **Postman** durante o desenvolvimento.

Alguns fluxos já validados:

```text
POST                → 201 Created
GET                 → 200 OK
PATCH               → 200 OK
DELETE              → 204 No Content
GET após exclusão   → 404 Not Found
Cadastro duplicado  → 409 Conflict
```

Testes automatizados fazem parte das próximas etapas de evolução do projeto.

---

# ▶️ Executando o projeto

## Pré-requisitos

Para executar o Shelfie localmente:

- Git
- JDK compatível com o projeto

O projeto utiliza **Maven Wrapper**, portanto não é necessário instalar Maven globalmente.

---

## Clone

```bash
git clone https://github.com/devlourenco/Shelfie.git
```

Entre no projeto:

```bash
cd Shelfie
```

---

## Executar os testes

### Windows

```bash
mvnw.cmd clean test
```

### Linux / macOS

```bash
./mvnw clean test
```

---

## Iniciar a aplicação

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Depois:

```text
http://localhost:8080
```

---

# 🗄️ Banco de dados

Atualmente o projeto utiliza **H2 Database** durante o desenvolvimento.

A utilização do H2 facilita a execução e os testes locais sem depender da configuração de um banco externo.

Uma futura versão poderá utilizar um banco persistente, como PostgreSQL.

---

# 🤖 Uso de Inteligência Artificial no aprendizado

Durante o desenvolvimento do Shelfie utilizo ferramentas de Inteligência Artificial como **apoio ao processo de estudo e revisão técnica**.

A IA é utilizada principalmente para:

- discutir possíveis soluções;
- revisar código que implementei;
- apontar problemas que posso não ter identificado;
- explicar conceitos de Java e Spring Boot;
- discutir decisões de arquitetura;
- sugerir cenários de teste;
- revisar boas práticas;
- organizar etapas de estudo;
- revisar commits e documentação.

A ferramenta não é utilizada como substituta do entendimento do projeto.

Meu processo consiste em **implementar, testar, analisar e compreender** o código. Quando uma mudança é sugerida, procuro entender o problema que ela resolve e o motivo técnico antes de incorporá-la ao projeto.

Também considero aprender a utilizar IA de forma responsável como parte da formação de um desenvolvedor atual: usando-a para aumentar a capacidade de investigação e aprendizado, sem abrir mão do domínio sobre aquilo que está sendo construído.

---

# 🔄 Processo de desenvolvimento

Tenho desenvolvido o Shelfie de maneira incremental.

Meu fluxo de estudo tem sido:

```text
Definir uma tarefa
       ↓
Implementar
       ↓
Testar
       ↓
Encontrar problemas
       ↓
Estudar e revisar
       ↓
Refatorar
       ↓
Executar o build
       ↓
Revisar o Git diff
       ↓
Criar um commit focado
```

Esse processo também é uma forma de praticar hábitos de desenvolvimento encontrados em projetos profissionais.

---

# 📈 Evolução do projeto

Algumas melhorias realizadas durante o desenvolvimento:

- organização do histórico inicial do Git;
- revisão do `.gitignore`;
- configuração do `.gitattributes`;
- normalização de line endings;
- remoção de dependências WebFlux não utilizadas;
- revisão do tipo utilizado pelo ID das entidades;
- criação de exceções personalizadas;
- implementação de tratamento global de exceções;
- melhoria dos contratos REST;
- alteração de `PUT` para `PATCH`;
- revisão dos códigos de status HTTP;
- testes manuais dos endpoints.

---

# 🗺️ Roadmap

## Próximas etapas

- [ ] Implementar Bean Validation
- [ ] Melhorar as regras de duplicidade
- [ ] Ajustar filtros para suportar múltiplos resultados
- [ ] Padronizar o corpo das respostas de erro
- [ ] Adicionar OpenAPI / Swagger
- [ ] Criar testes unitários
- [ ] Criar testes de integração
- [ ] Melhorar a documentação da API

## Possíveis evoluções futuras

- [ ] Paginação e ordenação
- [ ] PostgreSQL
- [ ] Flyway
- [ ] Docker
- [ ] GitHub Actions
- [ ] Deploy
- [ ] Autenticação
- [ ] Usuários
- [ ] Progresso de leitura
- [ ] Datas de início e conclusão
- [ ] Histórico de leituras

---

# 🚧 Status do projeto

**Em desenvolvimento.**

O CRUD principal, os contratos HTTP e o tratamento inicial de exceções já estão funcionais.

O projeto continuará sendo atualizado conforme avanço nos estudos de Java, Spring Boot, APIs REST, testes e desenvolvimento Back-end.

---

# 👨‍💻 Sobre mim

Meu nome é **Guilherme Simões Lourenço**.

Sou estudante de **Sistemas de Informação** e atualmente estou aprofundando meus conhecimentos em desenvolvimento de software, principalmente em:

- Java;
- Spring Boot;
- desenvolvimento Back-end;
- APIs REST;
- bancos de dados;
- resolução de problemas.

Estou em busca da minha **primeira oportunidade como estagiário em desenvolvimento de software**, onde possa aplicar os conhecimentos que venho adquirindo, aprender com profissionais mais experientes e contribuir com projetos reais.

O Shelfie representa não apenas uma aplicação, mas também parte do meu processo de aprendizado e preparação para entrar profissionalmente na área de tecnologia.

---

# 🔗 Links

📚 **Repositório:**  
https://github.com/devlourenco/Shelfie

💻 **GitHub:**  
https://github.com/devlourenco

💼 **LinkedIn:**  
https://www.linkedin.com/in/guilherme-simoes-lourenco/

---

# 💬 Feedback

Este é um projeto de estudo e evolução profissional.

Feedbacks sobre código, arquitetura, organização, testes, boas práticas ou decisões técnicas são muito bem-vindos.

Se você encontrou algo que pode ser melhorado, fique à vontade para abrir uma issue ou entrar em contato comigo pelo LinkedIn.