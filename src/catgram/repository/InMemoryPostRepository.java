package catgram.repository;

import catgram.model.Post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Ana
 */
public class InMemoryPostRepository implements PostRepository {

    private final Map<Long, Post> db = new HashMap();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(sequence.getAndIncrement());
        }

        db.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> findById(long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<Post>(db.values());
    }

    @Override
    public void delete(long id) {
        db.remove(id);
    }

}
