# 🛍️ API Bazar

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de um sistema de bazar, permitindo o controle de usuários, roupas e movimentações de estoque.

---

## 🚀 Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL
* Maven
* Swagger (Springdoc OpenAPI)

---

## 📌 Funcionalidades

* 👤 Cadastro e autenticação de usuários
* 👕 Gerenciamento de roupas (CRUD completo)
* 🔄 Controle de movimentações (entrada e saída de produtos)
* 🔐 Criptografia de senha com BCrypt
* 📄 Paginação e ordenação de resultados
* 📑 Documentação interativa com Swagger

---

## ⚙️ Como rodar o projeto

```bash
# Clonar o repositório
git clone https://github.com/gustavogaldinojj/api-bazar.git

# Entrar na pasta
cd api-bazar

# Rodar a aplicação
./mvnw spring-boot:run
```

---

---

## 📑 Documentação da API (Swagger)

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

ou

```
http://localhost:8080/swagger-ui/index.html
```

Através do Swagger você pode:

* Visualizar todos os endpoints
* Testar requisições diretamente
* Ver parâmetros e respostas da API

---

## 🔐 Autenticação

A API possui autenticação baseada em login.

### 🔑 Login

```http
POST /auth/login
```

### Body da requisição:

```json
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```

---

## 📦 Endpoints principais

### 👤 Usuários

```http
GET /usuarios
POST /usuarios
GET /usuarios/{id}
PUT /usuarios/{id}
DELETE /usuarios/{id}
```

#### Paginação e ordenação:

```http
GET /usuarios?page=0&size=10&sort=nome,asc
```

---

### 👕 Roupas

```http
GET /roupas
POST /roupas
PUT /roupas/{id}
DELETE /roupas/{id}
```

---

### 🔄 Movimentações

```http
GET /movimentacoes
POST /movimentacoes
```

---

## 📄 Exemplo de requisição

```json
{
  "nome": "Camiseta",
  "descricao": "Camiseta preta",
  "preco": 49.90,
  "quantidade": 10
}
```

---

## 🧪 Testes

Para executar os testes unitários:

```bash
./mvnw test
```
