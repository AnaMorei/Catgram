# 😸 Catgram - Etapa 6

Este repositório contém a **Etapa 6** do Projeto Integrador: um sistema monolítico em Java que modela as regras de negócio do Catgram (plataforma para postar fotos de gatos), com código separado da interface.

---

## Tecnologias Utilizadas

* Java 11+
* NetBeans IDE
* JUnit (para testes)
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

## Testes

* O método `App.main()` executa um fluxo de testes manuais cobrindo as principais funcionalidades.
* Para testes unitários automatizados com JUnit, a estrutura já está preparada.

---

## Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/AnaMorei/Catgram.git
```

2. Abra no NetBeans como projeto Java Ant.

3. Compile e execute a classe `catgram.App`.

   * O diretório `uploads/` será criado automaticamente, se não existir.
   * Verifique que as imagens de teste estejam salvas dentro de `uploads/`.
