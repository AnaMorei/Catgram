package catgram.service;

import catgram.model.Photo;
import catgram.model.Post;
import catgram.repository.PostRepository;
import catgram.storage.ImageStorage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class PostService {

    private final PostRepository postRepo;
    private final ImageStorage storage;

    public PostService(PostRepository postRepo, ImageStorage storage) {
        this.postRepo = postRepo;
        this.storage = storage;
    }

    public Post createPost(long authorId, String description, List<File> imageFiles) throws IOException {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (imageFiles == null || imageFiles.isEmpty()) {
            throw new IllegalArgumentException("At least one image file is required");
        }

        List<Photo> photos = new ArrayList<Photo>();
        for (File img : imageFiles) {
            String path = storage.save(img);
            photos.add(new Photo(authorId, path));
        }

        Post post = new Post(authorId, description, photos);

        return postRepo.save(post);
    }

    public List<Post> listAllPosts() {
        return postRepo.findAll();
    }

    public void likePost(long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));
        post.like();
        postRepo.save(post);
    }

    public void deletePost(long authorId, long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));

        if (post.getAuthorId() != authorId) {
            throw new SecurityException(
                    "User " + authorId + " is not the owner of post " + postId);
        }

        for (Photo photo : post.getPhotos()) {
            try {
                storage.delete(photo.getFilePath());
            } catch (IOException e) {
                throw new RuntimeException(
                        "Failed to delete image for photo " + photo.getId() + ": " + e.getMessage(), e);
            }
        }

        postRepo.delete(postId);
    }

}
