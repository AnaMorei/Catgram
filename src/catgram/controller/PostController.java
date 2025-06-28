package catgram.controller;

import catgram.model.Post;
import catgram.service.PostService;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Ana
 */
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public Post createPost(long authorId, String description, List<File> images) throws IOException {
        return postService.createPost(authorId, description, images);
    }

    public List<Post> listPosts() {
        return postService.listAllPosts();
    }

    public void likePost(long postId) {
        postService.likePost(postId);
    }

    public void deletePost(long authorId, long postId) {
        postService.deletePost(authorId, postId);
    }
}
