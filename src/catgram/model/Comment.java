package catgram.model;

import java.time.LocalDateTime;

/**
 *
 * @author Ana
 */
public class Comment {

    private long id;
    private long authorId;
    private long postId;
    private int likes;
    private String comment;
    private LocalDateTime createdAt;
    
}
