# SUBiP-MG Backend

Backend em Spring Boot para o Sistema Unico de Bibliotecas Publicas de Minas Gerais.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- MySQL
- Maven

## Como executar

Configure o banco MySQL conforme `src/main/resources/application.properties` e execute:

```bash
mvn spring-boot:run
```

A API usa a base:

```text
http://localhost:8080
```

Para desenvolvimento com Angular, o backend libera CORS para:

```text
http://localhost:4200
```

## Entidades principais

- Pessoa
- Genero
- Livro
- Biblioteca
- Exemplar
- Emprestimo
- Reserva
- Usuario

## Endpoints CRUD

| Metodo | Rota | Descricao |
| --- | --- | --- |
| GET | `/pessoas` | Lista pessoas |
| GET | `/pessoas/{id}` | Busca pessoa por id |
| POST | `/pessoas` | Cadastra pessoa |
| PUT | `/pessoas/{id}` | Altera pessoa |
| DELETE | `/pessoas/{id}` | Exclui pessoa, se nao houver emprestimos, reservas ou usuario vinculados |
| GET | `/usuarios` | Lista usuarios |
| GET | `/usuarios/{id}` | Busca usuario por id |
| POST | `/usuarios` | Cadastra usuario vinculado a uma pessoa |
| PUT | `/usuarios/{id}` | Altera usuario |
| DELETE | `/usuarios/{id}` | Exclui usuario |
| GET | `/generos` | Lista generos |
| GET | `/generos/{id}` | Busca genero por id |
| POST | `/generos` | Cadastra genero |
| PUT | `/generos/{id}` | Altera genero |
| DELETE | `/generos/{id}` | Exclui genero, se nao houver livros vinculados |
| GET | `/livros` | Lista livros, com filtros opcionais por titulo e ISBN |
| GET | `/livros/{id}` | Busca livro por id |
| POST | `/livros` | Cadastra livro |
| PUT | `/livros/{id}` | Altera livro |
| DELETE | `/livros/{id}` | Exclui livro, se nao houver exemplares vinculados |
| GET | `/bibliotecas` | Lista bibliotecas |
| GET | `/bibliotecas/{id}` | Busca biblioteca por id |
| POST | `/bibliotecas` | Cadastra biblioteca |
| PUT | `/bibliotecas/{id}` | Altera biblioteca |
| DELETE | `/bibliotecas/{id}` | Exclui biblioteca, se nao houver exemplares vinculados |
| GET | `/exemplares` | Lista exemplares |
| GET | `/exemplares/{id}` | Busca exemplar por id |
| POST | `/exemplares` | Cadastra exemplar |
| PUT | `/exemplares/{id}` | Altera exemplar |
| DELETE | `/exemplares/{id}` | Exclui exemplar, se nao houver emprestimos ou reservas vinculados |

## Usuarios e perfis

`Usuario` representa o perfil operacional vinculado a uma `Pessoa`. Nesta etapa ele ainda nao faz login real e nao possui senha.
O campo `login` e apenas um identificador unico do usuario dentro do sistema.

Perfis disponiveis:

- `LEITOR`
- `ATENDENTE`
- `ADMIN`

Regras principais:

- Login deve ser unico.
- Uma pessoa so pode ter um usuario.
- Perfil e pessoa sao obrigatorios.
- Senha, BCrypt, JWT e Spring Security ficam como etapa futura.

## Escopo atual sem seguranca

Esta versao nao possui endpoint de autenticacao, filtro JWT, BCrypt ou protecao de rotas por perfil.
Os perfis de `Usuario` foram mantidos apenas para organizar os tipos de uso do sistema sem aplicar autorizacao real.

## Endpoints de emprestimo

| Metodo | Rota | Descricao |
| --- | --- | --- |
| GET | `/emprestimos` | Lista emprestimos |
| GET | `/emprestimos/{id}` | Busca emprestimo por id |
| GET | `/emprestimos/pessoa/{pessoaId}` | Lista emprestimos de uma pessoa/leitor |
| GET | `/emprestimos/leitor/{pessoaId}` | Alias para o frontend listar emprestimos de um leitor |
| POST | `/emprestimos` | Registra emprestimo de exemplar disponivel e calcula devolucao prevista em 15 dias |
| PUT | `/emprestimos/{id}/renovacao` | Renova emprestimo aberto e atualiza a data prevista |
| PUT | `/emprestimos/{id}/devolucao` | Registra devolucao e libera o exemplar |

As respostas de emprestimo tambem trazem `tituloLivro`, `tombo`, `biblioteca` e `pessoaNome` para facilitar as telas do frontend.

Regras principais:

- Emprestimo comum so aceita exemplar `DISPONIVEL`.
- Exemplar `RESERVADO` so pode ser emprestado para a pessoa da reserva ativa.
- Ao atender a reserva, a reserva vira `ATENDIDA` e o exemplar vira `EMPRESTADO`.
- Na devolucao, o exemplar volta para `DISPONIVEL` se nao houver reserva ativa.
- Na devolucao, o exemplar vira `RESERVADO` se houver reserva ativa.

## Endpoints de reserva

| Metodo | Rota | Descricao |
| --- | --- | --- |
| GET | `/reservas` | Lista reservas |
| GET | `/reservas/{id}` | Busca reserva por id |
| GET | `/reservas/pessoa/{pessoaId}` | Lista reservas de uma pessoa/leitor |
| GET | `/reservas/leitor/{pessoaId}` | Alias para o frontend listar reservas de um leitor |
| POST | `/reservas` | Registra reserva para exemplar emprestado |
| PUT | `/reservas/{id}/cancelamento` | Cancela uma reserva ativa |

As respostas de reserva tambem trazem `tituloLivro`, `tombo`, `biblioteca` e `pessoaNome` para facilitar as telas do frontend.

Regras principais:

- So e possivel reservar exemplar `EMPRESTADO`.
- Exemplar `INDISPONIVEL` nao pode ser reservado.
- Uma pessoa nao pode ter duas reservas ativas para o mesmo exemplar.
- Um exemplar nao pode ter mais de uma reserva ativa.
- Enquanto o exemplar esta emprestado, ele continua `EMPRESTADO` mesmo tendo reserva ativa.
- A reserva pertence a uma pessoa e a um exemplar.

## Situacoes do exemplar

- `DISPONIVEL`: pode ser emprestado normalmente.
- `EMPRESTADO`: esta com uma pessoa; pode receber uma reserva.
- `RESERVADO`: foi devolvido, mas existe reserva ativa aguardando retirada.
- `INDISPONIVEL`: nao pode ser emprestado nem reservado.

## Endpoints de consulta

| Metodo | Rota | Descricao |
| --- | --- | --- |
| GET | `/acervo` | Lista acervo com livro, exemplar, biblioteca e situacao |
| GET | `/acervo?titulo=dom` | Filtra acervo pelo titulo do livro |
| GET | `/acervo?generoId=1` | Filtra acervo pelo genero do livro |
| GET | `/acervo?bibliotecaId=1` | Filtra acervo pela biblioteca |
| GET | `/acervo?situacao=DISPONIVEL` | Filtra acervo pela situacao do exemplar |
| GET | `/livros?titulo=dom` | Busca livros por titulo |
| GET | `/livros?isbn=9788535910663` | Busca livros por ISBN |
| GET | `/pessoas/{id}/emprestimos` | Lista emprestimos de uma pessoa |
| GET | `/emprestimos/pessoa/{pessoaId}` | Lista emprestimos de uma pessoa/leitor |
| GET | `/emprestimos/leitor/{pessoaId}` | Alias usado pelo frontend para listar emprestimos do leitor |
| GET | `/reservas/pessoa/{pessoaId}` | Lista reservas de uma pessoa/leitor |
| GET | `/reservas/leitor/{pessoaId}` | Alias usado pelo frontend para listar reservas do leitor |
| GET | `/livros/{id}/exemplares` | Lista exemplares de um livro |

Observacao: `/livros` e a rota padrao. O backend tambem aceita `/Livros` como alias temporario porque o frontend recebido ainda usa essa grafia em um service.

## Exemplos de JSON

Cadastro de pessoa:

```json
{
  "nome": "Diego Costa",
  "cpf": "44444444444",
  "email": "diego.costa@email.com"
}
```

Cadastro de genero:

```json
{
  "descricao": "Romance"
}
```

Cadastro de usuario:

```json
{
  "login": "ana",
  "perfil": "LEITOR",
  "pessoaId": 1
}
```

Cadastro de livro:

```json
{
  "titulo": "Vidas Secas",
  "isbn": "9788535926138",
  "generoId": 1
}
```

Registro de emprestimo:

```json
{
  "dataRetirada": "2026-06-12",
  "exemplarId": 1,
  "pessoaId": 1
}
```

A data de devolucao prevista e calculada automaticamente pelo backend como 15 dias apos a data de retirada.

Registro de reserva:

```json
{
  "pessoaId": 2,
  "exemplarId": 1
}
```

Renovacao:

```json
{
  "dataDevolucaoPrevista": "2026-07-04"
}
```

Devolucao:

```json
{
  "dataDevolucao": "2026-07-06"
}
```

## Insomnia

A collection esta em:

```text
insomnia_subip_mg_crud.yaml
```
