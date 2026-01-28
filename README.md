# 🚢 Import Control API

> Sistema de gerenciamento de processos de importação via API REST.

## 📖 Sobre o Projeto

O **Import Control** é uma solução Backend desenvolvida para modernizar o controle de processos de importação, substituindo o uso de planilhas manuais e descentralizadas.

O objetivo principal da aplicação é garantir a integridade dos dados, evitar a duplicidade de lançamentos e fornecer uma interface padronizada e segura para o cadastro e consulta de invoices, fornecedores e previsões de embarque.

### 🚀 Principais Funcionalidades

- **Cadastro Inteligente:** Registro de processos com validação automática de duplicidade (impede o cadastro do mesmo nº de processo duas vezes).
- **Gestão Completa:** Listagem, busca detalhada por ID e exclusão de processos.
- **Tratamento de Erros:** Respostas HTTP semânticas (ex: `409 Conflict` para duplicidade, `400 Bad Request` para dados inválidos) com mensagens limpas para o Frontend.
- **Segurança de Infraestrutura:** Configuração de credenciais sensíveis via variáveis de ambiente (`.env`), protegendo senhas do banco de dados.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído seguindo as melhores práticas de mercado:

- **Linguagem:** Java 17 (LTS)
- **Framework:** Spring Boot 3.2.2 (Web, Data JPA)
- **Banco de Dados:** PostgreSQL 15
- **Infraestrutura:** Docker (Containerização do Banco)
- **Segurança:** Java Dotenv 5.2 (Gestão de Variáveis de Ambiente)
- **Build:** Maven

---

## ⚙️ Como Executar o Projeto

Siga os passos abaixo para rodar a aplicação em sua máquina local.

### 1. Pré-requisitos
Certifique-se de ter instalado:
- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven](https://maven.apache.org/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Git](https://git-scm.com/)

### 2. Clonar o Repositório
```bash
git clone [https://github.com/Klaudio0707/Import-Control---Sistema-de-Gest-o-de-Importa-o-.git](https://github.com/Klaudio0707/Import-Control---Sistema-de-Gest-o-de-Importa-o-.git)
cd import-control
3. Configurar Variáveis de Ambiente
Na raiz do projeto (junto ao arquivo pom.xml), crie um arquivo chamado .env e adicione o seguinte conteúdo:

Snippet de código
# Configurações do Banco de Dados
DB_URL=jdbc:postgresql://localhost:5432/importdb
DB_USER=postgres
DB_PASSWORD=admin

# Configuração da API
PORT=8080
Nota: O arquivo .env não é versionado pelo Git por motivos de segurança.

4. Subir o Banco de Dados (Docker)
Abra o terminal e execute o comando abaixo para criar e rodar o container do PostgreSQL:

Bash
docker run --name import-db \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=importdb \
  -p 5432:5432 \
  -d postgres:15-alpine
5. Executar a Aplicação
Com o banco rodando, inicie a aplicação via terminal:

Bash
mvn spring-boot:run
Ou execute a classe principal App.java através da sua IDE (VS Code / IntelliJ). O servidor iniciará em: http://localhost:8080

🔌 Documentação da API (Endpoints)
Abaixo estão os exemplos de requisições para testar no Postman ou Insomnia.

1. Criar Processo (POST)
URL: http://localhost:8080/processos Body (JSON):

JSON
{
  "numeroProcesso": "IMP-2026-001",
  "identificadorInvoice": "INV-9988",
  "fornecedor": "Tech Imports Global",
  "produto": "Placas de Vídeo RTX",
  "quantidade": 50.0,
  "precoPorQuilo": 120.50,
  "dataEmbarque": "2026-02-20"
}
2. Listar Todos (GET)
URL: http://localhost:8080/processos

3. Buscar por ID (GET)
URL: http://localhost:8080/processos/{id}

4. Deletar Processo (DELETE)
URL: http://localhost:8080/processos/{id}

🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar Pull Requests.

📝 Licença
Este projeto está desenvolvido para fins de estudo e portfólio.