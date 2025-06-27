package catgram.storage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class LocalImageStorageTest {

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    private ImageStorage storage;

    @Before
    public void setUp() throws IOException {
        Path base = tempDir.getRoot().toPath();
        storage = new LocalImageStorage(base);
    }

    @Test
    public void save_createsFileAndReturnsPath() throws IOException {
        File dummy = tempDir.newFile("pic.png");
        try (FileWriter w = new FileWriter(dummy)) {
            w.write("fake-image-bytes");
        }

        String storedKey = storage.save(dummy);

        Path storedPath = Path.of(storedKey);
        assertTrue("Should save under base dir", storedPath.startsWith(tempDir.getRoot().toPath()));
        assertTrue("File should exist after save", Files.exists(storedPath));
        assertTrue("Saved file should not be empty", Files.size(storedPath) > 0);
    }

    @Test
    public void delete_existingFile_removesIt() throws IOException {
        File dummy = tempDir.newFile("pic2.png");
        try (FileWriter w = new FileWriter(dummy)) {
            w.write("content");
        }
        String key = storage.save(dummy);
        Path path = Path.of(key);
        assertTrue("Precondition: file must exist", Files.exists(path));

        storage.delete(key);

        assertFalse("File should be deleted", Files.exists(path));
    }

    @Test
    public void delete_nonexistentFile_doesNotThrow() {
        String fakeKey = tempDir.getRoot().toPath().resolve("no-such-file.png").toString();

        try {
            storage.delete(fakeKey);
        } catch (IOException e) {
            fail("Deleting a non-existent file should not throw, but got: " + e.getMessage());
        }
    }
}
