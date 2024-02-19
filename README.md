<br><br>

<h1 align="center">
	<img src="https://i.imgur.com/USFFl6m.png"  alt="Logo"  width="200"><br><br>
    Gerenciador de Tarefas
</h1>


## Table of Contents

<p align="center">
 <a href="#about">About</a> •
 <a href="#features">Features</a> •
 <a href="#technologies">Technologies</a> • 
 <a href="#documentation">Documentation</a> •
 <a href="#installation ">Installation </a> •
 <a href="#getting-started">Get Started</a> •
 <a href="#postgresql">PostgreSQL</a>
 
</p>

<br>

## 📌About

<div>
    <p align="center">
    API-REST de gerenciamento de tarefas, com testes unitarios.
    </p>
</div>

<br>

## 🚀Features
- Adicionar um pessoa (post/pessoas)			
- Alterar um pessoa (put/pessoas/{id})			
- Remover pessoa (delete/pessoas/{id})			
- Adicionar um tarefa (post/tarefas)			
- Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})			
- Finalizar a tarefa (put/tarefas/finalizar/{id})			
- Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)			
- Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)			
- Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)			
- Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
- Criar, Editar e Apagar (Pessoa, Tarefa e Departamento)
<br>

## 🌐Technologies

- Java 8
- JPA
- Gradle
- Spring Boot
- Mockito
- Junit
- PostgreSQL
- Postman


## 📕Installation

**Recomendações**
- É recomendável que você tenha instalado o Google Chrome ou Edge
- Eu recomendo usar o Eclipse (STS) como IDE de desenvolvimento

**A instalação e inicialização são 4 etapas!**
1. Clone este repositório
2. Entre na pasta descompactada
3. Build com Gradle
4. Rode o projeto com a propria IDE.

### 1. Clone this repository
```
git clone https://github.com/NKLzT/Teste-para-BackEnd.git
```
---

### 3. Acesse o projeto descompactada pela IDE
```
e altere o banco de dados para o seu e rode como Spring Boot APP
```
---



<br>

## 🎮Getting Started
- Abra o navegador e entre no seguinte link: http://localhost:8080
- Teste os endpoints com os testes unitarios ou pelo postman
