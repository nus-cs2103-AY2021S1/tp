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
import seedu.address.model.ReadOnlyHospifyBook;

/**
 * A class to access HospifyBook data stored as a json file on the hard disk.
 */
public class JsonHospifyStorage implements HospifyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHospifyStorage.class);

    private Path filePath;

    public JsonHospifyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHospifyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHospifyBook> readHospifyBook() throws DataConversionException {
        return readHospifyBook(filePath);
    }

    /**
     * Similar to {@link #readHospifyBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHospifyBook> readHospifyBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHospify> jsonHospifyBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableHospify.class);
        if (!jsonHospifyBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHospifyBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHospifyBook(ReadOnlyHospifyBook addressBook) throws IOException {
        saveHospifyBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveHospifyBook(ReadOnlyHospifyBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHospifyBook(ReadOnlyHospifyBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHospify(addressBook), filePath);
    }

}
