package catgram.controller;

import catgram.model.User;
import catgram.service.UserService;

/**
 *
 * @author Ana
 */
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User register(String name, char[] password) {
        return userService.createUser(name, password);
    }

    public User login(String name, char[] password) {
        return userService.authenticate(name, password);
    }

    public User getUser(long userId) {
        return userService.getUserById(userId);
    }
}
