package catgram.service;

import catgram.model.Comment;
import catgram.model.Post;
import catgram.model.User;
import catgram.repository.CommentRepository;
import catgram.repository.InMemoryCommentRepository;
import catgram.repository.InMemoryPostRepository;
import catgram.repository.InMemoryUserRepository;
import catgram.repository.PostRepository;
import catgram.repository.UserRepository;
import catgram.storage.ImageStorage;
import catgram.storage.LocalImageStorage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import org.junit.Assert;

import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Ana
 */
public class ServiceLayerTest {

    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    @Before
    public void setUp() {
        UserRepository userRepository = new InMemoryUserRepository();
        PostRepository postRepository = new InMemoryPostRepository();
        CommentRepository commentRepository = new InMemoryCommentRepository();
        ImageStorage storage = new LocalImageStorage(Path.of("uploads"));

        userService = new UserService(userRepository);
        postService = new PostService(postRepository, storage);
        commentService = new CommentService(commentRepository, postRepository);
    }

//  User service    
    @Test
    public void createUserGoodArgument() {
        String userName = "clara";
        User user = userService.createUser(userName, "aralc".toCharArray());
        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(userName, user.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUserBadArgument() {
        userService.createUser("", "password".toCharArray());
    }

    @Test(expected = SecurityException.class)
    public void authenticateBadCredentials() {
        userService.createUser("ana", "nan".toCharArray());
        userService.authenticate("ana", "ana".toCharArray());
    }

//  Post service
    @Test
    public void createAndLikePost() throws Exception {
        User user = userService.createUser("d", "d".toCharArray());
        File dummy = Path.of("uploads", "gato1.jpg").toFile();
        Post post = postService.createPost(user.getId(), "desc", List.of(dummy));

        Assert.assertEquals(0, post.getLikes());

        postService.likePost(post.getId());

//      Acabei usando search de novo para simular
//      uma segunda chamada no banco de dados  
//      e ter total certeza que o objeto foi atualizado
        Post reloadedPost = postService.listAllPosts()
                .stream()
                .filter(pp -> pp.getId() == post.getId())
                .findFirst().get();

        Assert.assertEquals(1, reloadedPost.getLikes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPostNoImages() throws Exception {
        User user = userService.createUser("c", "c".toCharArray());
        postService.createPost(user.getId(), "desc", List.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void likeNonExistentPost() {
        postService.likePost(999999L);
    }

    @Test(expected = SecurityException.class)
    public void deletePostByNomAuthor() throws Exception {
        User user1 = userService.createUser("e1", "x".toCharArray());
        User user2 = userService.createUser("e2", "x".toCharArray());
        File dummy = Path.of("uploads", "gato1.jpg").toFile();

        Post post = postService.createPost(user1.getId(), "z", List.of(dummy));
        postService.deletePost(user2.getId(), post.getId());
    }

//  Comment service
    @Test
    public void createAndDeleteComment() throws Exception {
        User user = userService.createUser("f", "f".toCharArray());
        File dummy = Path.of("uploads", "gato1.jpg").toFile();
        Post post = postService.createPost(user.getId(), "y", List.of(dummy));

        Comment comment = commentService.addComment(post.getId(), user.getId(), "ok");
        Assert.assertTrue(comment.getId() > 0);

        commentService.deleteComment(user.getId(), comment.getId());
        List<Comment> comments = commentService.listComments(post.getId());
        Assert.assertTrue(comments.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCommentNomExistentPost() {
        commentService.addComment(999999L, 99999L, "testing123");
    }
}
