package catgram.repository;

import catgram.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Ana
 */
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> db = new HashMap();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(sequence.getAndIncrement());
        }

        db.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findByCredentials(String username, char[] password) {
        return db.values().stream()
                .filter(user -> user.getName().equals(username)
                && Arrays.equals(user.getPassword(), password))
                .findFirst();
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(db.values());
    }

    @Override
    public void delete(long id) {
        db.remove(id);
    }
}
