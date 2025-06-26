package catgram.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ana
 */
public class Post {

    private long id;
    private User authorId;
    private String description;
    private int likes;
    private LocalDateTime createdAt;
    private List<Photo> photos;
    private List<Comment> comments;
}
