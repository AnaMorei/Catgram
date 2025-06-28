package catgram.service;

import catgram.model.Comment;
import catgram.repository.CommentRepository;
import catgram.repository.PostRepository;
import java.util.List;

/**
 *
 * @author Ana
 */
public class CommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public Comment addComment(long postId, long authorId, String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Comment text is required");
        }

        postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));

        Comment c = new Comment(postId, authorId, text);
        return commentRepo.save(c);
    }

    public List<Comment> listComments(long postId) {
        postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));
        return commentRepo.findAllByPostId(postId);
    }

    public void deleteComment(long authorId, long commentId) {
        Comment c = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
        if (c.getAuthorId() != authorId) {
            throw new SecurityException("User " + authorId + " is not the owner of comment " + commentId);
        }
        commentRepo.delete(commentId);
    }
}
