# 🏢 Inventory IT - Rastreamento de Equipamentos

O **Inventory IT** é um sistema web desenvolvido para ajudar no controle e na organização de equipamentos de TI dentro dos laboratórios de uma instituição.

Com o sistema, é possível cadastrar laboratórios, cadastrar equipamentos e relacionar cada equipamento ao laboratório onde ele está localizado.

## ✨ Funcionalidades

* **Cadastro de Laboratórios**

  * Cadastro de laboratórios informando nome e bloco.
  * Visualização dos laboratórios cadastrados.

* **Cadastro de Equipamentos**

  * Cadastro de equipamentos informando número de série e tipo.
  * Seleção do laboratório onde o equipamento está alocado.

* **Listagem de Equipamentos**

  * Visualização dos equipamentos cadastrados.
  * Exibição do laboratório relacionado a cada equipamento.

* **Seleção dinâmica de laboratório**

  * Os laboratórios cadastrados são carregados automaticamente no formulário de cadastro de equipamentos.

## 🛠️ Tecnologias utilizadas

| Parte                    | Tecnologia            |
| ------------------------ | --------------------- |
| Linguagem                | Java                  |
| Backend                  | Jakarta EE / Servlets |
| Persistência             | JPA + Hibernate       |
| Banco de dados           | H2                    |
| Frontend                 | JSP + JSTL            |
| Estilização              | Bootstrap 5           |
| Servidor                 | Apache Tomcat 11      |
| Gerenciamento do projeto | Maven                 |

## 📁 Estrutura do projeto

```text
inventory-it/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── br/com/inventory/
│       │       ├── modelo/
│       │       │   ├── Laboratorio.java
│       │       │   └── Equipamento.java
│       │       │
│       │       ├── dao/
│       │       │   ├── JPAUtil.java
│       │       │   ├── LaboratorioDAO.java
│       │       │   └── EquipamentoDAO.java
│       │       │
│       │       └── servlet/
│       │           ├── LaboratorioServlet.java
│       │           └── EquipamentoServlet.java
│       │
│       ├── resources/
│       │   └── META-INF/
│       │       └── persistence.xml
│       │
│       └── webapp/
│           ├── index.jsp
│           ├── laboratorios.jsp
│           ├── equipamentos.jsp
│           └── novo-equipamento.jsp
│
├── pom.xml
└── README.md
```

## 🚀 Como executar

### Pré-requisitos

Para executar o projeto, é necessário ter:

* **JDK 17 ou superior**
* **Apache Tomcat 11**
* **Maven**

### 1. Clonar o projeto

No terminal, clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

Depois, entre na pasta do projeto:

```bash
cd inventory-it
```

### 2. Compilar

Para gerar o arquivo `.war`, execute:

```bash
mvn clean package -DskipTests
```

O arquivo será gerado dentro da pasta `target`.

### 3. Executar no Tomcat

Copie o arquivo `.war` gerado para a pasta `webapps` do Apache Tomcat.

Depois, inicie o Tomcat. O sistema será carregado automaticamente pelo servidor.

Após iniciar, basta acessar a aplicação pelo navegador através do endereço do Tomcat.

## 🗄️ Banco de dados

O projeto utiliza o banco **H2**, configurado para funcionar em memória.

A persistência dos dados é feita utilizando **JPA**, com o **Hibernate** como implementação.

A configuração do banco e da unidade de persistência fica no arquivo:

```text
src/main/resources/META-INF/persistence.xml
```

Como o banco está configurado em memória, os dados podem ser perdidos quando a aplicação é reiniciada.

## 🔗 Relacionamento entre equipamentos e laboratórios

O principal relacionamento do sistema é entre **Equipamento** e **Laboratorio**.

Cada equipamento pode estar associado a um laboratório por meio de um relacionamento `@ManyToOne`.

O processo funciona da seguinte forma:

1. O usuário acessa o cadastro de equipamentos.
2. O sistema carrega os laboratórios cadastrados.
3. O usuário escolhe um laboratório no formulário.
4. O sistema recebe o ID do laboratório.
5. O `EquipamentoServlet` busca o laboratório no banco.
6. O laboratório é associado ao equipamento.
7. O equipamento é salvo através do DAO.
8. Na listagem, o sistema mostra o equipamento junto com o laboratório ao qual ele pertence.

## 🗄️ DAOs

Os DAOs são responsáveis pelas operações de acesso ao banco de dados.

### LaboratorioDAO

Responsável pelas operações relacionadas aos laboratórios, como:

* Salvar um novo laboratório;
* Listar os laboratórios cadastrados.

### EquipamentoDAO

Responsável pelas operações relacionadas aos equipamentos, como:

* Salvar um novo equipamento;
* Listar os equipamentos cadastrados;
* Buscar os equipamentos junto com seus respectivos laboratórios.

## 🎯 Servlets

### LaboratorioServlet

Controla as requisições relacionadas aos laboratórios.

* `GET` → carrega os laboratórios cadastrados.
* `POST` → recebe os dados do formulário e salva um novo laboratório.

### EquipamentoServlet

Controla as requisições relacionadas aos equipamentos.

* `GET` → exibe a lista de equipamentos ou o formulário de cadastro.
* `POST` → recebe os dados do equipamento, busca o laboratório selecionado e realiza o cadastro.

## 🎨 Páginas do sistema

### `laboratorios.jsp`

Página utilizada para cadastrar e visualizar os laboratórios.

### `equipamentos.jsp`

Página que apresenta os equipamentos cadastrados e os respectivos laboratórios.

### `novo-equipamento.jsp`

Página utilizada para cadastrar um novo equipamento.

O laboratório é selecionado através de um campo de seleção que é preenchido automaticamente com os laboratórios cadastrados.


## 👨‍💻 Projeto acadêmico

Projeto desenvolvido para fins acadêmicos, com o objetivo de aplicar os conhecimentos de desenvolvimento web utilizando Java, Jakarta EE, JPA e banco de dados.

**Status:** ✅ Concluído

**Última atualização:** 30/08/2026
