
<h1 align="center">
  Customer API
</h1>

<p align="center">
<img align="center" src="https://img.shields.io/static/v1?label=Type&message=Demo&color=8257E5&labelColor=000000" alt="Demo"/>
</p>

<p align="center">
  <i>API para gerenciar clientes.</i>
</p>

---

## Description

A **Customer API** é uma aplicação que permite gerenciar clientes de maneira eficiente. Através desta API, é possível realizar operações de cadastro, consulta, atualização e exclusão de clientes. O sistema utiliza **MongoDB** como banco de dados, garantindo escalabilidade e alta performance, além de implementar resiliência nas integrações com APIs externas.

---

## Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)&nbsp;
![Spring Boot](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)&nbsp;
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)&nbsp;
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)&nbsp;

---

## Features

- [x] Cadastro de clientes com validação de dados.
- [x] Consulta de clientes por CPF.
- [x] Atualização de informações de clientes.
- [x] Exclusão de clientes.
- [x] Documentação com Swagger/OpenAPI.

---

## Roadmap

- Adicionar autenticação e autorização com JWT.
- Implementar suporte para múltiplos idiomas.
- Criar mais testes automatizados (unitários e de integração).
- Adicionar monitoramento de métricas com Prometheus/Grafana.

---

## How to Run

### Pré-requisitos

- **Java 17** ou superior.
- **Docker** (para o MongoDB).
- **Maven** (caso não utilize o wrapper).

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/genorchiomento/customer-api.git
```

2. Suba o banco de dados MongoDB utilizando Docker Compose:

```bash
docker-compose up -d
```

3. Compile o projeto:

```bash
./mvnw clean package
```

4. Execute o JAR gerado:

```bash
java -jar target/customer-api-0.0.1.jar
```

5. Acesse a documentação da API no navegador:

```
http://localhost:8081/swagger-ui.html
```

---

## TODO

- [ ] Adicionar suporte a múltiplos idiomas.
- [ ] Melhorar a documentação da API com exemplos mais detalhados.
- [ ] Criar mais testes automatizados.
- [ ] Implementar autenticação e autorização.

---

## API Endpoints

### **Cadastro de Cliente**

**POST** `/api/customers`

**Body**:
```json
{
  "cpf": "12345678900",
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "phone": "9999999999",
  "address": "Rua das Flores, 123"
}
```

**Response**:
```json
{
  "id": "64c9f0b565d0f9e7c02893bf",
  "cpf": "12345678900",
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "phone": "9999999999",
  "address": "Rua das Flores, 123"
}
```

---

## Test

- Use ferramentas como **Postman** ou **Swagger** para testar os endpoints.
- Exemplos de requisições estão documentados no Swagger UI.

---

## License

Este projeto está licenciado sob a [Licença MIT](LICENSE).
