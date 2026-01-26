# 📚 Sistema de Gerenciamento de Acervo (Biblioteca)

Este projeto é uma aplicação Java Web completa, desenvolvida para gerenciar o acervo de uma biblioteca (Livros e Autores). O objetivo principal deste repositório foi consolidar o conhecimento nos fundamentos do ecossistema Java Enterprise, explorando a fundo o funcionamento de Servlets e o ciclo de vida de requisições HTTP.

---

## 🎯 Objetivo do Projeto

Antes de avançar para as abstrações de alto nível proporcionadas pelo **Spring Framework**, este projeto foi construído utilizando **Java Servlets puros** e **JSP**. 

**O que aprendi com este projeto:**
- **Ciclo de vida de Servlets:** Gerenciamento de requisições `GET` e `POST`.
- **Comunicação Web:** Diferença prática entre `Forward` (servidor) e `Redirect` (cliente).
- **Persistência com JDBC:** Implementação do padrão **DAO (Data Access Object)** com gestão de recursos via *try-with-resources*.
- **Arquitetura MVC:** Separação clara entre Entidades (Model), Telas (View - JSP) e Controle (Servlets).
- **UX com JS Vanilla:** Validações de formulário personalizadas, modais de confirmação e notificações dinâmicas (Toasts) sem dependência de frameworks externos.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Servidor de Aplicação:** Apache Tomcat 10
* **View:** JSP (JavaServer Pages) & JSTL
* **Banco de Dados:** MySQL (JDBC)
* **Relatórios:** iText (Geração de PDF)
* **Frontend:** HTML5, CSS3 e JavaScript (Vanilla)

---

## 🚀 Funcionalidades

- [x] **Cadastro de Autores:** Registro de novos escritores no sistema.
- [x] **Gestão de Livros:** CRUD completo (Criar, Listar, Editar e Deletar).
- [x] **Relatório em PDF:** Geração dinâmica de lista de acervo para download.
- [x] **Validação Avançada:** Sistema de mensagens de erro inline e toasts de feedback.
- [x] **Confirmação de Exclusão:** Modal customizado para evitar deleções acidentais.

---

## 📸 Demonstração da Interface

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/ac27ba41-7549-48a9-8aae-8affcf131c6a" />

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/930af734-2b76-46db-906d-6b8dae1028ec" />

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/a671a2a5-9192-4347-acfd-1d8a3276bafb" />

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/ab46d670-6555-417e-be12-d67268b6511b" />

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/d77d596e-7a11-40a0-92d8-8a0561d74549" />

<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/545a6855-9d50-4798-9a70-71de65b3e628" />

---

## ⚙️ Como executar o projeto

Este é um **Dynamic Web Project** desenvolvido no Eclipse. Siga os passos abaixo para rodar localmente:

1.  **Clone o repositório:**
    
    ```bash
    git clone https://github.com/stefanopaulo/crud-acervo-livros.git
    ```
    
2.  **Build do projeto com Maven:**
    - Na raiz do projeto, execute:

    ```bash
    mvn clean package
    ```

3. **Banco de Dados:**

    - Execute os scripts SQL disponíveis na pasta `/sql` (criação e povoamento).
    - Crie um arquivo chamado `db.properties` no diretório:
      ```
      src/main/resources
      ```
    - Exemplo de configuração (utilize valores compatíveis com seu ambiente local):
      ```properties
      driver=com.mysql.cj.jdbc.Driver
      url=jdbc:mysql://127.0.0.1:3306/[seu_banco_aqui]?useTimezone=true&serverTimezone=UTC
      user=[seu_usuario]
      password=[sua_senha]
      ```
    - A aplicação carrega automaticamente essas configurações no momento da conexão com o banco de dados.
    
4. **Deploy no Tomcat:**

    - Copie o arquivo .war gerado para a pasta webapps do Tomcat
   
    ou
    
    - Configure o projeto em uma IDE de sua preferência utilizando um servidor Tomcat.
    - Inicie o Tomcat

5. **Acesse à aplicação:**

    - Acesse no navegador: `http://localhost:8080/acervo-livros`

---

## 👨‍💻 Autor

**Stefano Paulo** *Desenvolvedor focado em Java e ecossistema Spring.*

---
