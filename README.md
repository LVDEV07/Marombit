# Marombit - API de gerenciamento de alunos

Sistema web desenvolvido para o gerenciamento de alunos, com controle de matrículas, cadastro, atualização e remoção via API REST.


## Deploy

## Tecnologias Utilizadas

### Backend
![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-green?style=flat&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-4.0-green?style=flat&logo=spring)

### Banco de Dados
![MySQL](https://img.shields.io/badge/MySQL-8.4-4479A1?style=flat&logo=mysql&logoColor=white)


## Endpoints — `/alunos`

### `GET /alunos`
Retorna todos os alunos cadastrados.

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "dtNascimento": "2005-03-15",
    "plano": "BÁSICO",
    "matriculaAtiva": true
  }
]
```


### `POST /alunos`
Cria um novo aluno.

**Request body:**
```json
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "dtNascimento": "2005-03-15",
  "plano": "BÁSICO"
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "dtNascimento": "2005-03-15",
  "plano": "BÁSICO",
  "matriculaAtiva": true
}
```


### `GET /alunos/{id}`
Retorna um aluno pelo ID.

**Path param:** `id` — ID do aluno

**Response `200 OK`:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "dtNascimento": "2005-03-15",
  "plano": "BÁSICO",
  "matriculaAtiva": true
}
```

**Response `204 No Content`:** Aluno não encontrado.


### `PUT /alunos/{id}`
Atualiza os dados de um aluno existente.

**Path param:** `id` — ID do aluno

**Request body:**
```json
{
  "nome": "João Silva Atualizado",
  "cpf": "123.456.789-00",
  "dtNascimento": "2005-03-15",
  "plano": "PREMIUM"
}
```

**Response `200 OK`:** Retorna o aluno atualizado.  
**Response `404 Not Found`:** Aluno não encontrado.


### `DELETE /alunos/{id}`
Remove um aluno pelo ID.

**Path param:** `id` — ID do aluno

**Response `204 No Content`:** Aluno removido com sucesso.  
**Response `404 Not Found`:** Aluno não encontrado.


### `GET /alunos/{id}/status`
Verifica se a matrícula de um aluno está ativa ou inativa.

**Path param:** `id` — ID do aluno

**Response `200 OK`:**
```
Matricula_Ativa
```
ou
```
Matricula_Inativa
```

**Response `204 No Content`:** Aluno não encontrado.


## Resumo dos Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/alunos` | Lista todos os alunos |
| `POST` | `/alunos` | Cria um novo aluno |
| `GET` | `/alunos/{id}` | Busca aluno por ID |
| `PUT` | `/alunos/{id}` | Atualiza dados do aluno |
| `DELETE` | `/alunos/{id}` | Remove um aluno |
| `GET` | `/alunos/{id}/status` | Verifica status da matrícula |


## Como Rodar Localmente

### Pré-requisitos

![Java](https://img.shields.io/badge/Java-21+-orange?style=flat&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=flat&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8+-4479A1?style=flat&logo=mysql&logoColor=white)

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/marombit
cd marombit
```

2. Configure o banco em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/marombit?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute:
```bash
./mvnw spring-boot:run
```

4. Acesse em `http://localhost:8080`


## Rodando com Docker

```bash
docker build -t marombit .
docker run -p 8080:8080 marombit
```


<div align="center">

Desenvolvido por

[![Lucas](https://img.shields.io/badge/LVDEV07-181717?style=flat&logo=github&logoColor=white)](https://github.com/LVDEV07)

</div>