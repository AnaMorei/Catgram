package catgram.model;

import java.time.LocalDateTime;

/**
 *
 * @author Ana
 */
public class Photo {

    private long id;
    private long authorId;
    private long postId;
    private String filePath;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public Photo(long authorId, String filePath) {
        this.authorId = authorId;
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
