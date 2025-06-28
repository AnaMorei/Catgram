package catgram.controller;

import catgram.model.Comment;
import catgram.service.CommentService;
import java.util.List;

/**
 *
 * @author Ana
 */
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public Comment addComment(long postId, long authorId, String text) {
        return commentService.addComment(postId, authorId, text);
    }

    public List<Comment> listComments(long postId) {
        return commentService.listComments(postId);
    }

    public void deleteComment(long authorId, long commentId) {
        commentService.deleteComment(authorId, commentId);
    }
}
