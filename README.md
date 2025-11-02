# Estrutura de Pacotes utilizada

- `io.github.nutria.nutria.model`: Entidades que representam as tabelas do banco de dados.
- `io.github.nutria.nutria.dao`: Objetos de acesso a dados (DAO) responsáveis pelo CRUD.
- `io.github.nutria.nutria.service`: Camada de serviço, contém as regras de negócio e validações.
- `io.github.nutria.nutria.util`: Classes utilitarias para facilitar e otimizar o desenvolvimento.
- `io.github.nutria.nutria.exceptions`: Exceções personalizadas utilizadas para facilitar o envio de erros e tratamento.
- `src/main/webapp/WEB-INF/views`: Páginas JSP (views) para interação com o usuário.

# Configuração de Variáveis de Ambiente (.env)

O projeto utiliza um arquivo `.env` para armazenar variáveis sensíveis, como configurações de banco de dados.  
⚠️ **Nunca versionar o `.env` no repositório.**
:

```dotenv
# AIVEN
DB_URL=
DB_USER=
DB_PASSWORD=

# MONGODB
MONGODB_URI=

# GEMINI, REDIS E MONGO
GEMINI_API_KEY=
MONGO_USER=
MONGO_PWD=
REDIS_URI=

SMTP_USER=
APP_PASS=
```
⚠️ **Nunca versionar o `.env` no repositório.**



# Padrão de Commits - Conventional Commits

Este projeto adota o padrão [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/) para mensagens de commit. Isso facilita a leitura do histórico de commits e futuras soluções.

### Formato da mensagem

Cada mensagem de commit deve seguir o formato:
`<tipo>[escopo]: descrição`

Exemplo:
```
feat(usuario): adicionar validação de email
```

### Tipos mais comuns

- `feat`: Nova funcionalidade
- `fix`: Correção de bug
- `docs`: Mudanças na documentação
- `style`: Formatação, sem alteração de código (espaços, ponto e vírgula, etc)
- `refactor`: Refatoração de código, sem alterar funcionalidade
- `test`: Adição ou ajuste de testes
- `chore`: Tarefas de build, dependências ou configurações

### Escopo (opcional)

O escopo indica a área afetada, por exemplo: `usuario`, `dao`, `controller`.

### Descrição

Breve e no infinitivo, explicando o que foi feito.

## Exemplos

- `feat(service): implementar lógica de cadastro`
- `fix(controller): tratar erro de validação`
- `docs: criar seção de contribuição`
- `chore: atualizar dependências do maven`

## Referência

- [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/)
