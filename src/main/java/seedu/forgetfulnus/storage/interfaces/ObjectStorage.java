package seedu.forgetfulnus.storage.interfaces;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.forgetfulnus.commons.exceptions.DataConversionException;

public interface ObjectStorage<T> {

    /**
     * Returns the file path of the data file.
     */
    Path getFilePath();
    Optional<T> readFile() throws DataConversionException, IOException;
    Optional<T> readFile(Path filePath) throws DataConversionException, IOException;
    void saveFile(T file) throws IOException;
    void saveFile(T file, Path filePath) throws IOException;
}
