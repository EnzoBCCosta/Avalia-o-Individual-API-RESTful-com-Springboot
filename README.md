# 🐾 ONG Adoção — API REST — SERRATEC

API RESTful para gerenciamento de adoção de animais, desenvolvida com Spring Boot. 

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando localmente

> As tabelas são criadas automaticamente pelo Hibernate com `ddl-auto=update`.

A aplicação sobe na porta **8080**.

---

## 📄 Documentação Swagger

Com a aplicação rodando, acesse no navegador:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📦 Estrutura do Projeto

```
src/main/java/trabalhoindividual/
├── config/         # Configuração do Swagger
├── controller/     # Controllers REST
├── domain/         # Entidades JPA
├── dto/
│   ├── request/    # DTOs de entrada
│   └── response/   # DTOs de saída
├── exception/      # Tratamento global de exceções
├── repository/     # Interfaces JPA Repository
└── service/        # Regras de negócio
```

---

## 🗂️ Entidades e Relacionamentos

| Entidade | Descrição |
|---|---|
| `Animal` | Animal disponível para adoção |
| `Pessoa` | Pessoa interessada em adotar |
| `Endereco` | Endereço vinculado a uma pessoa |
| `Caracteristica` | Característica associada a animais |
| `InteresseAdocao` | Registro de interesse de adoção |

**Relacionamentos:**
- `Pessoa` → `Endereco`: OneToOne
- `Pessoa` → `InteresseAdocao`: OneToMany
- `Animal` → `InteresseAdocao`: OneToMany
- `Animal` → `Caracteristica`: ManyToMany

> Ciclos de serialização JSON são tratados com `@JsonManagedReference` / `@JsonBackReference` (OneToMany) e `@JsonIgnoreProperties` (ManyToMany).

---

## 🔗 Endpoints

### 🐶 Animais — `/animais`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/animais` | Lista todos os animais |
| GET | `/animais/{id}` | Busca animal por ID |
| POST | `/animais` | Cadastra novo animal |
| PUT | `/animais/{id}` | Atualiza animal |
| DELETE | `/animais/{id}` | Deleta animal |

**Exemplo de body (POST/PUT):**
```json
{
  "nome": "Rex",
  "especie": "Cachorro",
  "raca": "Labrador",
  "idade": 3,
  "sexo": "Macho",
  "porte": "Grande",
  "status": "Disponível"
}
```

---

### 👤 Pessoas — `/pessoas`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/pessoas` | Lista todas as pessoas |
| GET | `/pessoas/{id}` | Busca pessoa por ID |
| POST | `/pessoas` | Cadastra nova pessoa |
| PUT | `/pessoas/{id}` | Atualiza pessoa |
| DELETE | `/pessoas/{id}` | Deleta pessoa |
| POST | `/pessoas/{id}/enderecos` | Adiciona endereço à pessoa |
| PUT | `/pessoas/{id}/enderecos` | Atualiza endereço da pessoa |
| DELETE | `/pessoas/{id}/enderecos` | Remove endereço da pessoa |

**Exemplo de body (POST/PUT):**
```json
{
  "nome": "Maria Silva",
  "cpf": "123.456.789-09",
  "email": "maria@email.com",
  "telefone": "21987654321"
}
```

---

### 🏠 Endereços — `/enderecos`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/enderecos` | Lista todos os endereços |
| GET | `/enderecos/{id}` | Busca endereço por ID |
| POST | `/enderecos` | Cadastra novo endereço |
| PUT | `/enderecos/{id}` | Atualiza endereço |
| DELETE | `/enderecos/{id}` | Deleta endereço |

**Exemplo de body (POST/PUT):**
```json
{
  "rua": "Rua das Flores",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "Petrópolis",
  "estado": "RJ",
  "cep": "25610-100"
}
```

---

### 🏷️ Características — `/caracteristicas`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/caracteristicas` | Lista todas as características |
| GET | `/caracteristicas/{id}` | Busca característica por ID |
| POST | `/caracteristicas` | Cadastra nova característica |
| PUT | `/caracteristicas/{id}` | Atualiza característica |
| DELETE | `/caracteristicas/{id}` | Deleta característica |

**Exemplo de body (POST/PUT):**
```json
{
  "tipo": "Temperamento",
  "descricao": "Animal dócil e brincalhão"
}
```

---

### 💛 Interesses de Adoção — `/interesses-adocao`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/interesses-adocao` | Lista todos os interesses |
| GET | `/interesses-adocao/{id}` | Busca interesse por ID |
| GET | `/interesses-adocao/pessoa/{pessoaId}` | Lista interesses de uma pessoa |
| GET | `/interesses-adocao/animal/{animalId}` | Lista interesses por animal |
| POST | `/interesses-adocao/pessoa/{pessoaId}/animal/{animalId}` | Registra interesse de adoção |
| PUT | `/interesses-adocao/{id}` | Atualiza interesse |
| DELETE | `/interesses-adocao/{id}` | Deleta interesse |

**Exemplo de body (POST/PUT):**
```json
{
  "dataInteresse": "2025-05-20",
  "status": "Pendente",
  "observacao": "Tenho quintal grande"
}
```

---

## ⚠️ Tratamento de Erros

Todos os erros são tratados globalmente pelo `GlobalExceptionHandler` e retornam um JSON padronizado:

```json
{
  "timestamp": "2025-05-20T10:30:00",
  "status": 404,
  "erro": "Recurso não encontrado",
  "mensagem": "Animal com id 5 não encontrado"
}
```

| Código | Situação |
|---|---|
| 400 | Dados inválidos (validação) |
| 404 | Recurso não encontrado |
| 409 | Conflito de dados (duplicidade) |
| 500 | Erro interno do servidor |

--> muitos commits feito durante a madrugada pois o código simplesmente começou a explodir, mas finalmente esta pronto com muito desespero!
