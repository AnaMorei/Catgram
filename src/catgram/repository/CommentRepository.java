package catgram.repository;

import catgram.model.Comment;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ana
 */
public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByUser(long userId);

    void delete(long id);

    public List<Comment> findAllByPostId(long postId);
}
