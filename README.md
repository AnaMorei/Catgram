# 😸 Catgram

> **Fase concluída:** Etapa 6 \
> **Fase atual:** Etapa 7 – testes automatizados

Este repositório contém o sistema monolítico em Java para o Catgram (plataforma para postar fotos de gatos).

---

## Tecnologias Utilizadas

* Java 11+
* NetBeans IDE
* JUnit 4
* MySQL (planejado para etapas futuras)

---

## Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/
 │   │   └── catgram/
 │   │       ├── controller/   # Controladores (entry points)
 │   │       ├── model/        # Entidades de domínio
 │   │       ├── repository/   # Interfaces e implementações in-memory
 │   │       ├── service/      # Regras de negócio
 │   │       └── storage/      # Abstração de armazenamento de imagens
 │   └── resources/            # Recursos estáticos (ex.: uploads)
 └── test/
     └── java/catgram/         # Testes com JUnit4
```

---

## Funcionalidades Implementadas

* Cadastro de usuário (nome + senha)
* Criação de post com descrição e pelo menos uma foto
* Listagem de posts
* Curtir posts
* Comentários em posts
* Exclusão de posts (apenas pelo autor)

---

## Testes Automatizados (Etapa 7)

### Já implementados

* **ServiceLayerTest** (JUnit 4)

  * UserService: criação, validação de nome/senha e autenticação
  * PostService: criação de post com fotos, curtidas e exclusão autorizada
  * CommentService: adição, listagem e exclusão de comentários
* **LocalImageStorageTest** (JUnit 4)

  * save(): grava arquivo e retorna caminho válido
  * delete(): remove arquivo existente e não falha em arquivos ausentes

### Foco para futuros testes

1. **Repository Layer**

   * InMemoryUserRepository: atribuição de IDs, busca por credenciais e duplicatas
   * InMemoryPostRepository: findById, findAll, delete e métodos de fotos (se houver)
   * InMemoryCommentRepository: findAllByPostId e ordenação

2. **Edge Cases em Serviços**

   * Criar post com múltiplas fotos (verificar todas registradas)
   * Deleção em cascata: após deletePost(), findAll de fotos e comentários deve retornar vazio
   * Comentários muito longos (validação de tamanho)

3. **Concorrência e Idempotência (opcional)**

   * Simular likes concorrentes e validar contador final
   * Chamar deletePost duas vezes e confirmar exceção no segundo chamado

---

## Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/AnaMorei/Catgram.git
```

2. Abra no NetBeans como projeto Java Ant.

3. Compile e execute a classe `catgram.App`:

   * O diretório `uploads/` será criado automaticamente, se não existir.
   * Certifique‑se de que as imagens de teste estejam na pasta `uploads/`.
