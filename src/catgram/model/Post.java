package catgram.model;

import java.util.List;

/**
 *
 * @author Ana
 */
public class Post {

    private long id;
    private User userId;
    private String description;
    private int likes;
    private List<Photo> photos;
    private List<Comment> comments;
}
