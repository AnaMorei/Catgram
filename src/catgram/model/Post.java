package catgram.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ana
 */
public class Post {

    private long authorId;
    private String description;
    private List<Photo> photos;
    private List<Comment> comments;

    private long id = 0;
    private int likes = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(long authorId, String description, List<Photo> photos) {
        this.authorId = authorId;
        this.description = description;
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
