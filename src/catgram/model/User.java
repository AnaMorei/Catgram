package catgram.model;

import java.time.LocalDateTime;

/**
 *
 * @author Ana
 */
public class User {

    private long id;
    private String name;
    private String password;
    private LocalDateTime createdAt;

    public User(long id, String name, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }

}
