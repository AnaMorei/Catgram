package catgram.repository;

import catgram.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ana
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByCredentials(String username, char[] password);

    List<User> findAll();

    void delete(long id);
}
