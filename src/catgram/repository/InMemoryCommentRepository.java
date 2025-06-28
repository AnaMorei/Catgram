package catgram.repository;

import catgram.model.Comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 *
 * @author Ana
 */
public class InMemoryCommentRepository implements CommentRepository {

    private final Map<Long, Comment> db = new HashMap();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            comment.setId(sequence.getAndIncrement());
        }

        db.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Comment> findAllByUser(long userId) {
        return db.values()
                .stream()
                .filter(c -> c.getAuthorId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAllByPostId(long postId) {
        return db.values()
                .stream()
                .filter(c -> c.getPostId() == postId)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        db.remove(id);
    }
}
