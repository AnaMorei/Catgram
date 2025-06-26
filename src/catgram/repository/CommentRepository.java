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

    List<Comment> findAllByPost(long postId);

    void delete(long id);
}
