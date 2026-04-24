# 🛒 API Bazar

API REST desenvolvida para gerenciamento de itens de um bazar, permitindo o controle de produtos, usuários e operações básicas de CRUD.

## 🚀 Objetivo

O projeto tem como objetivo fornecer um back-end robusto para aplicações de gestão de bazar, podendo ser integrado a front-ends (como Angular) ou outros sistemas.

---

## 🧠 Funcionalidades

- Cadastro de usuários  
- Listagem de usuários  
- Cadastro de produtos  
- Listagem de produtos  
- Atualização de dados  
- Remoção de registros  
- Estrutura RESTful  

---

## 🛠️ Tecnologias utilizadas

- Java  
- Spring Boot  
- JPA / Hibernate  
- MySQL (ou outro banco relacional)  
- Maven  

---

## 📂 Estrutura do projeto

```
api-bazar/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.seuprojeto.bazar/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       └── dto/
│   │   └── resources/
│   │       ├── application.properties
│── pom.xml
```

---

## ⚙️ Como rodar o projeto

### 🔧 Pré-requisitos

- Java 17+
- Maven
- MySQL (ou outro banco configurado)

### ▶️ Passos

1. Clone o repositório:
```
git clone https://github.com/gustavogaldinojj/api-bazar.git
```

2. Acesse o diretório:
```
cd api-bazar
```

3. Configure o banco de dados no `application.properties`

4. Execute o projeto:
```
mvn spring-boot:run
```

---

## 📡 Endpoints (exemplo)

### 👤 Usuários

| Método | Endpoint         | Descrição        |
|--------|----------------|------------------|
| GET    | /usuarios      | Lista usuários   |
| POST   | /usuarios      | Cria usuário     |
| PUT    | /usuarios/{id} | Atualiza usuário |
| DELETE | /usuarios/{id} | Remove usuário   |

### 📦 Produtos

| Método | Endpoint         | Descrição        |
|--------|----------------|------------------|
| GET    | /produtos      | Lista produtos   |
| POST   | /produtos      | Cria produto     |
| PUT    | /produtos/{id} | Atualiza produto |
| DELETE | /produtos/{id} | Remove produto   |

---

## 🔐 Segurança

- Estrutura preparada para integração com autenticação (JWT ou Spring Security)
- Validações com Bean Validation (`@Valid`)

---

## 🧪 Melhorias futuras

- Implementação de autenticação e autorização  
- Relatórios de vendas  
- Controle de estoque  
- Integração com front-end Angular  
- Testes unitários  

---

## 📄 Licença

Este projeto está sob a licença MIT.

---
