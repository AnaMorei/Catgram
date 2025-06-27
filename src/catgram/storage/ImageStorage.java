package catgram.storage;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ana
 */
public interface ImageStorage {

    String save(File image) throws IOException;

    void delete(String storageKey) throws IOException;
}
