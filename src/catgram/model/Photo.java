package catgram.model;

/**
 *
 * @author Ana
 */
public class Photo {

    private long id;
    private long authorId;
    private long postId;
    private String filePath;

    public Photo(long authorId, long postId, String filePath) {
        this.authorId = authorId;
        this.postId = postId;
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
