package catgram.model;

/**
 *
 * @author Ana
 */
public class User {

    private long id;
    private String name;
    private String password;

    public User(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
