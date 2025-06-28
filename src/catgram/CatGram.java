package catgram;

import catgram.controller.UserController;
import catgram.controller.PostController;
import catgram.controller.CommentController;
import catgram.model.Post;
import catgram.model.Comment;
import catgram.model.User;
import catgram.repository.InMemoryUserRepository;
import catgram.repository.InMemoryPostRepository;
import catgram.repository.InMemoryCommentRepository;
import catgram.service.UserService;
import catgram.service.PostService;
import catgram.service.CommentService;
import catgram.storage.ImageStorage;
import catgram.storage.LocalImageStorage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Ana
 */
public class CatGram {

    public static void main(String[] args) throws Exception {
        Path uploadDir = Paths.get("uploads");

        if (Files.notExists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir);
                System.out.println("Diretório 'uploads' criado em: " + uploadDir.toAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível criar o diretório 'uploads': " + e.getMessage(), e);
            }
        }

        ImageStorage storage = new LocalImageStorage(uploadDir);
        var userRepo = new InMemoryUserRepository();
        var postRepo = new InMemoryPostRepository();
        var commentRepo = new InMemoryCommentRepository();

        var userService = new UserService(userRepo);
        var postService = new PostService(postRepo, storage);
        var commentService = new CommentService(commentRepo, postRepo);

        var userController = new UserController(userService);
        var postController = new PostController(postService);
        var commentController = new CommentController(commentService);

        System.out.println("== Cadastro de usuários ==");
        User u1 = userController.register("alice", "pwd123".toCharArray());
        User u2 = userController.register("bob", "secret".toCharArray());
        System.out.println("Usuários: " + List.of(u1, u2));

        System.out.println("\n== Criação de posts ==");
        File image1 = uploadDir.resolve("gato1.jpg").toFile();
        Post p1 = postController.createPost(u1.getId(),
                "Meu gato fofo",
                List.of(image1));
        File image2 = uploadDir.resolve("gato2.jpg").toFile();
        Post p2 = postController.createPost(u2.getId(), "Gatinho dormindo", List.of(image2));
        System.out.println("Posts criados: " + List.of(p1, p2));

        System.out.println("\n== Listar posts ==");
        postController.listPosts().forEach(System.out::println);

        System.out.println("\n== Curtir post ==");
        postController.likePost(p1.getId());
        System.out.println("Likes de p1: " + postController.listPosts().stream()
                .filter(p -> p.getId() == p1.getId()).findFirst().get().getLikes());

        System.out.println("\n== Comentários ==");
        Comment c1 = commentController.addComment(p1.getId(), u2.getId(), "Que lindo!");
        List<Comment> comments = commentController.listComments(p1.getId());
        System.out.println("Comentários em p1: " + comments);

        System.out.println("\n== Teste de autorização de deleção ==");
        try {
            postController.deletePost(u2.getId(), p1.getId());
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n== Deletar post pelo autor ==");
        postController.deletePost(u1.getId(), p1.getId());
        System.out.println("Posts após deleção: " + postController.listPosts());
    }
}
