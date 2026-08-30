# Inventory IT - Sistema de Rastreamento de Equipamentos

## O que foi desenvolvido

Um sistema web completo para rastrear a alocação de equipamentos de TI em laboratórios de uma instituição.

### Funcionalidades

- **Cadastro de Laboratórios**: Nome, bloco e ID
- **Cadastro de Equipamentos**: Número de série, tipo e laboratório alocado
- **Listagem com Relacionamento**: Equipamentos exibem o laboratório ao qual pertencem
- **Select Dinâmico**: Formulário de equipamento carrega laboratórios do banco de dados

### Tecnologia

- **Backend**: Java Servlets (Jakarta EE 6), JPA/Hibernate 6, H2 Database
- **Frontend**: JSP com JSTL, Bootstrap 5
- **Framework**: Apache Tomcat 11
- **Build**: Maven (pom.xml fornecido)

## Estrutura do Projeto

```
inventory-it/
├── index.jsp                           # Página inicial (redireciona para /laboratorios)
├── laboratorios.jsp                    # Listagem e cadastro de laboratórios
├── equipamentos.jsp                    # Listagem de equipamentos
├── novo-equipamento.jsp                # Formulário para cadastrar equipamento
├── SCRIPT_APRESENTACAO.md              # Roteiro para apresentação
├── WEB-INF/
│   ├── classes/
│   │   ├── br/com/inventory/
│   │   │   ├── modelo/
│   │   │   │   ├── Laboratorio.java    # Entidade JPA
│   │   │   │   └── Equipamento.java    # Entidade JPA (relacionamento ManyToOne)
│   │   │   ├── dao/
│   │   │   │   ├── JPAUtil.java        # Factory de EntityManager
│   │   │   │   ├── LaboratorioDAO.java # CRUD de laboratórios
│   │   │   │   └── EquipamentoDAO.java # CRUD de equipamentos
│   │   │   └── servlet/
│   │   │       ├── LaboratorioServlet.java # @WebServlet("/laboratorios")
│   │   │       └── EquipamentoServlet.java # @WebServlet({"/equipamentos", "/novo-equipamento"})
│   │   └── META-INF/
│   │       └── persistence.xml         # Configuração JPA (H2 em memória)
│   └── lib/                            # Dependências (Hibernate, Jakarta, H2, JSTL)
└── META-INF/
    └── maven/...                       # Metadados Maven
```

## Como executar

### 1. Pré-requisitos

- **JDK 17+** (foi usado JDK 26.0.2.1)
- **Maven** (opcional, já está compilado)
- **Tomcat 11** (já baixado em `C:\Users\Pichau\Desktop\tomcat-11`)

### 2. Iniciar o servidor

Execute o script:
```bash
C:\Users\Pichau\Desktop\START-TOMCAT.bat
```

Ou manualmente:
```bash
set PATH=C:\Users\Pichau\Desktop\jdk-26.0.2.1\bin;%PATH%
cd C:\Users\Pichau\Desktop\tomcat-11
java -Dcatalina.home=. -Dcatalina.base=. -Djava.util.logging.config.file=conf/logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -cp "bin\bootstrap.jar;bin\tomcat-juli.jar;lib\*" org.apache.catalina.startup.Bootstrap start
```

### 3. Acessar a aplicação

- **URL**: `http://localhost:8080/inventory-it/`
- Navegue pelos laboratórios, cadastre equipamentos e veja a listagem completa

## Arquivos principais implementados

### DAOs (Persistência)

- **LaboratorioDAO.java**: `salvar()` com transação JPA, `listarTodos()` com query JPQL
- **EquipamentoDAO.java**: `salvar()` com transação JPA, `listarTodos()` com `JOIN FETCH` para evitar lazy loading

### Servlets

- **LaboratorioServlet.java**: GET exibe lista, POST salva novo laboratório e redireciona
- **EquipamentoServlet.java**: 
  - GET (`/equipamentos`): lista equipamentos
  - GET (`/novo-equipamento`): carrega formulário com laboratórios
  - POST: recebe ID do laboratório, usa `EntityManager.find()` para buscar o objeto, associa e salva

### Páginas JSP

- **laboratorios.jsp**: Formulário inline + tabela com `.table .table-striped`
- **equipamentos.jsp**: Tabela mostrando `${equipamento.laboratorio.nome}`
- **novo-equipamento.jsp**: Select dinâmico com `<c:forEach>` sobre `${laboratorios}`

## Ponto importante: Relacionamento

A chave do desafio foi fazer o equipamento persistir com o laboratório relacionado:

1. Entidade `Equipamento` tem `@ManyToOne` para `Laboratorio`
2. Servlet recebe `laboratorio_id` como String do formulário
3. Converte para Long e busca o objeto completo com `EntityManager.find()`
4. Atribui ao equipamento antes de chamar `DAO.salvar()`
5. Na listagem, JSP acessa `${equipamento.laboratorio.nome}` direto (sem lazy loading, pois usamos `JOIN FETCH` no DAO)

## Próximos passos (sugestões)

- Adicionar validação de dados (anotações `@NotNull`, `@Size`, etc.)
- Criar telas de edição e exclusão
- Implementar autenticação de usuários
- Adicionar busca/filtro de equipamentos
- Exportar relatório em PDF

---

**Desenvolvido em**: 30/08/2026  
**Status**: ✅ Completo e em execução
