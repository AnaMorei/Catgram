package catgram.model;

import java.time.LocalDateTime;

/**
 *
 * @author Ana
 */
public class User {

    private String name;
    private char[] password;

    private long id = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User(String name, char[] password) {
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char[] getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
