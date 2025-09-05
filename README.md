# 🏥 VollMed API

API REST para gerenciamento médico, desenvolvida com **Java + Spring Boot**.  
O sistema possibilita o cadastro de médicos e pacientes, autenticação de usuários, além de funcionalidades de agendamento e cancelamento de consultas, com regras de negócio e validações específicas.  

---

## 📚 Funcionalidades

- **Cadastro de médicos e pacientes** (CRUD completo)
- **Agendamento de consultas**
- **Cancelamento de consultas**
- **Autenticação e autorização de usuários** com JWT
- **Validações de regras de negócio**, como horários permitidos (08h às 18h, segunda a sexta)
- **Documentação da API** com Swagger
- **Testes automatizados** (controllers e queries no banco)

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Auth0**
- **JWT (JSON Web Token)**
- **Hibernate / JPA**
- **Flyway**
- **Lombok**
- **Swagger + JavaDoc**
- **JUnit & Mockito**
- **Maven**

---

## ⚡ Como Rodar o Projeto

1. Clone o repositório:
   git clone https://github.com/SEU-USUARIO/NOME-DO-REPO.git 

2. Entre na pasta do projeto:
  cd NOME-DO-REPO

3. Gere o build:
  mvn clean install

4. Execute o arquivo .jar:  \
  java -Dspring.profiles.active=prod \
       -DDATASOURCE_URL=URL_DO_SEU_BANCO \
       -DDATASOURCE_USERNAME=USERNAME_DO_SEU_BANCO \
       -DDB_PASSWORD=SENHA_DO_SEU_BANCO \
       -jar target/NOME_DO_ARQUIVO.jar

5. Acesse a documentação no Swagger:
  http://localhost:8080/swagger-ui.html

📦 Arquivo .jar
➡️  [Baixar .jar](https://github.com/olavoneves/VollMed/releases/download/v1.0.0/vollmed-0.0.1-SNAPSHOT.jar)

src/main/java/br/com/vollmed  <br>
 ├── controller     # Controllers da aplicação <br>
 ├── dto            # Objetos de transferência de dados <br>
 ├── model          # Entidades e modelos JPA <br>
 ├── repository     # Interfaces de acesso ao banco (Spring Data JPA) <br>
 ├── service        # Regras de negócio <br>
 └── infra          # Configurações de segurança (Spring Security, JWT, Auth0) <br>

 # 📡 Exemplos de Requisições
## 🔑 Autenticação (Login)
  POST /login
  Content-Type: application/json
  
 {
    "login" : "jose@outlook.com",
    "senha" : "123456"
 }
  
  
  Resposta:
  
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
  }

## ➕ Criar Médico
  POST /medicos
  Authorization: Bearer SEU_TOKEN_AQUI
  Content-Type: application/json
  
  {
    "nome": "Dr. João Silva",
    "email": "joao.silva@vollmed.com",
    "crm": "123456",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
      "logradouro": "Rua A",
      "bairro": "Centro",
      "cep": "12345000",
      "cidade": "São Paulo",
      "uf": "SP"
    }
  }

## 📅 Agendar Consulta
  POST /consultas
  Authorization: Bearer SEU_TOKEN_AQUI
  Content-Type: application/json
  
  {
    "idMedico": 1,
    "idPaciente": 1,
    "data": "2025-09-10T14:00:00"
  }

## ❌ Cancelar Consulta
  DELETE /consultas/{id}
  Authorization: Bearer SEU_TOKEN_AQUI

## 📌 Licença

Este projeto não possui licença de uso comercial.
