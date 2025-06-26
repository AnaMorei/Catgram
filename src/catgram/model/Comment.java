package catgram.model;

import java.time.LocalDateTime;

/**
 *
 * @author Ana
 */
public class Comment {

    private long authorId;
    private long postId;
    private String comment;

    private long id = 0;
    private int likes = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Comment(long authorId, long postId, String comment) {
        this.authorId = authorId;
        this.postId = postId;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public long getPostId() {
        return postId;
    }

    public int getLikes() {
        return likes;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
