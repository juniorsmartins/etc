# INTRODUÇÃO

- Nome: Portfolio;
- Data de Início: 00/00/0000;
- Previsão de Término: 00/00/0000;

Descrição: ...

# ÍNDICE

1. Documentação de Arquitetura do Projeto;
2. Documentação de Desenvolvimento do Projeto;
3. Documentação de Infraestrutura do Projeto;
4. Documentação de Utilização do Projeto;


## Documentação de Arquitetura do Projeto

1. Domínios;
2. Linguagem Ubíqua;
3. Linguagem Pictográfica (Domain Storytelling);
4. Event Storming;
5. Diagramas.

## Documentação de Desenvolvimento do Projeto


## Documentação de Infraestrutura do Projeto


## Documentação de Utilização do Projeto



# CHECKLIST

### Back-end

1. Arquiteturas:
> - [ ] Microsserviços na aplicação;
> - [ ] Explorar outras arquiteturas nas APIs Rest (camadas, hexagonal e etc);

1. Geral:
> - [ ] README detalhado;
> - [ ] Metodologia Ágil (Scrum Board no Jira);
> - [ ] Versionamento de código (Git, GitHub e Gitflow);
> - [ ] Repositório Monorepo;
> - [ ] UML - Unified Modeling Language (Use Case);
> - [ ] Docker.

2. POO - Programação Orientada a Objeto:
> - [ ] Polimorfismo; 
> - [ ] Herança; 
> - [ ] Encapsulamento; 
> - [ ] Abstração.

3. Boas práticas:
> - [ ] DDD - Domain Driven Design (entidades, objetos de valor, agregados, serviços, fábricas, linguagem ubíqua, contextos delimitados e etc);
> - [ ] TDD - Test Driven Development (JUnit e Mockito, JMeter e JavaFaker);
> - [ ] Clean Architecture;
> - [ ] Design Pattern;
> - [ ] Refactor;
> - [ ] Solid (SRP, OCP, LSP, ISP, DIP);
> - [ ] Clean Code;
> - [ ] Object Calisthenics;
> - [ ] Kiss, Dry, Yagni e Soc;
> - [ ] Sun Checks;
> - [ ] Google Checks;
> - [ ] Análise Estática de Código (SonarLint);
> - [ ] Inversão de Controle e Injeção de Dependência.

4. Spring:
> - [ ] Spring Boot 3.0.0;
> - [ ] Web;
> - [ ] Persistência (com Spring Data JPA - Derived Queries, JPQL, JPQL em XML, JPQL com SDJ, JPQL Dinâmico, Criteria API, Native Query e Specifications DDD);
> - [ ] Persistência (com Spring Data MongoDB;
> - [ ] Bean Validation;
> - [ ] Migration (Flyway e ou Liquibase);
> - [ ] Security; 
> - [ ] Hateoas; 
> - [ ] Batch; 
> - [ ] Social (redes sociais);
> - [ ] Cloud (Eureka, Gateway, balanceamento de carga, circuit breaker, Sleuth, Resilience4J e Open Feign); 
> - [ ] GraphQL; 
> - [ ] Java Mail Sender;
> - [ ] Mensageria AMQP (RabbitMQ ou Kafka);
> - [ ] Flo; 
> - [ ] Shell;
> - [ ] OpenAPI 3 (SpringDoc - Swagger - documentação unificada no Gateway);
> - [ ] Cache;
> - [ ] Actuator;
> - [ ] Admin;
> - [ ] GraalVM;
> - [ ] Lombok; 
> - [ ] DevTools.

5. API Restful (3 pilares):

Seis Constraints de Roy Fielding:
> - [ ] A API deve ser Cliente/servidor;
> - [ ] A aplicação deve ser Stateless (não guardar estado);
> - [ ] Ter capacidade de realizar cache para reduzir tráfego;
> - [ ] Interface uniforme (recursos bem definidos, hipermídias e utilizar métodos Http e códigos de retorno);
> - [ ] O sistema deve ser construído em camadas;
> - [ ] Deve possuir a capacidade de evoluir sem quebrar.

Modelo de maturidade de Leonard Richardson:
> - [ ] Nível de maturidade 0;
> - [ ] Nível de maturidade 1;
> - [ ] Nível de maturidade 2;
> - [ ] Nível de maturidade 3;

Hypertext Transfer Protocol - RFC 2616
> - [ ] ...
> - [ ] ...
> - [ ] ...
> - [ ] ...

6. Microservices: 
> - [ ] micro_api-gateway;
> - [ ] micro_service-registry;
> - [ ] micro_config-server;
> - [ ] micro_usuarios;
> - [ ] micro_cursos;
> - [ ] micro_notícias;
> - [ ] micro_pagamentos;
> - [ ] micro_email;
> - [ ] micro_dashboard; 
> - [ ] micro_etc;

7. Java: 
> - [ ] Java (17 LTS);
> - [ ] Tratamento de Exceções (Global e ou Local);
> - [ ] Funcional;
> - [ ] Stream;
> - [ ] Lambda;
> - [ ] Optional;
> - [ ] Method Reference;
> - [ ] Generics;
> - [ ] Threads;
> - [ ] Interface Funcional;
> - [ ] Estrutura de Dados (lista, pilha, fila e coleção);
> - [ ] Collections Framework;
> - [ ] Math;
> - [ ] Text Blocks;
> - [ ] Comparable e Comparator;
> - [ ] JDBC;
> - [ ] Enums;
> - [ ] Logback (Escolhido nível Info com saída para console - Há seis níveis: trace, debug, info, warn, error e fatal);
> - [ ] Content Negotiation;
> - [ ] Padrão GMT/UTC de Data e Hora (ISO-8601);
> - [ ] Objects;
> - [ ] ObjectMapper;
> - [ ] BeanUtils;
> - [ ] ReflectionUtils;
> - [ ] StringUtils;
> - [ ] Cors;
> - [ ] Matcher e ExampleMatcher (Get - pesquisa);
> - [ ] Padrão Repository com EntityManager;
> - [ ] DTOs - Data Transfer Object (Class Record);
> - [ ] Regular Expressions (Class Pattern);
> - [ ] Eventos;
> - [ ] Entrada e saída I/O;
> - [ ] XStream XML;
> - [ ] Javadoc (somente no service);
> - [ ] Multi-tenancy;
> - [ ] Jasper Reports;
> - [ ] Bibliotecas Desconhecidas (WordUtils, JavaFaker, Blaze Persistence e etc).

### Banco de Dados Relacional e Não Relacional (NoSQL)
- [ ] Banco de dados PostgreSQL;
- [ ] Banco de dados MySQL;
- [ ] Banco de dados MariaDB;
- [ ] Banco de dados MongoDB (NoSQL);
- [ ] DER - Diagrama Entidade Relacionamento.


# DATABASE

> __Microservice Client__

__Modelo Conceitual__

![ConceptualModel_client_5](https://user-images.githubusercontent.com/64662590/206908468-922ca5fd-3b2f-4abf-8c4c-0fa1c13ffab7.png)

__Modelo Lógico__

![LogicModel_client_5](https://user-images.githubusercontent.com/64662590/206908482-91caf7a1-cb18-4b47-96bb-e4870abad26a.png)

__Modelo Físico__

![PhysicalModel_client_5](https://user-images.githubusercontent.com/64662590/206908491-bfffbad3-af8c-4263-b308-1de165fa6a27.png)

# FERRAMENTAS

- IntelliJ IDEA;
- Maven;
- Jira;
- Git e GitHub;
- Postman e Insomnia;
- BRModelo;
- SQL Power Architect;
- StarUML.


