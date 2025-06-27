package catgram.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Ana
 */
public class LocalImageStorage implements ImageStorage {

    private final Path baseDir;

    public LocalImageStorage(Path baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public String save(File image) throws IOException {
        if (!Files.exists(baseDir)) {
            Files.createDirectories(baseDir);
        }

        String filename = System.currentTimeMillis() + "_" + image.getName();
        Path target = baseDir.resolve(filename);

        Files.copy(image.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        return target.toString();
    }

    @Override
    public void delete(String storageKey) throws IOException {
        Path path = Path.of(storageKey);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
