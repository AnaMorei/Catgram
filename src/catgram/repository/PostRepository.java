package catgram.repository;

import catgram.model.Post;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ana
 */
public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(long id);

    List<Post> findAll();

    void delete(long id);
}
