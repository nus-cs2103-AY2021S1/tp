package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyModuleBook;


public class JsonModuleBookStorage implements ModuleBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleBookStorage.class);

    private Path filePath;

    public JsonModuleBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleBook> readModuleBook() throws DataConversionException {
        return readModuleBook(filePath);
    }

    /**
     * Similar to {@link #readModuleBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModuleBook> readModuleBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleBook> jsonModuleBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleBook.class);
        if (!jsonModuleBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonModuleBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleBook(ReadOnlyModuleBook moduleBook) throws IOException {
        saveModuleBook(moduleBook, filePath);
    }

    /**
     * Similar to {@link #saveModuleBook(ReadOnlyModuleBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModuleBook(ReadOnlyModuleBook moduleBook, Path filePath) throws IOException {
        requireNonNull(moduleBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleBook(moduleBook), filePath);
    }

}


