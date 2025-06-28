package catgram.service;

import catgram.model.User;
import catgram.repository.UserRepository;
import java.util.Optional;

/**
 *
 * @author Ana
 */
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(String name, char[] password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException("Password is required");
        }

        Optional<User> existing = userRepo.findByCredentials(name, password);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User(name, password);
        return userRepo.save(user);
    }

    public User authenticate(String name, char[] password) {
        Optional<User> maybeUser = userRepo.findByCredentials(name, password);
        return maybeUser.orElseThrow(()
                -> new SecurityException("Invalid username or password"));
    }

    public User getUserById(long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }
}
