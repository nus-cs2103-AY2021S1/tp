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
import seedu.address.model.ReadOnlyFlashcardDeck;

/**
 * A class to access FlashCardDeck data stored as a json file on the hard disk.
 */
public class JsonFlashcardDeckStorage implements FlashcardDeckStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFlashcardDeckStorage.class);

    private Path filePath;

    public JsonFlashcardDeckStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashcardDeckFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck() throws DataConversionException {
        return readFlashcardDeck(filePath);
    }

    /**
     * Similar to {@link #readFlashcardDeck()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFlashcardDeck> jsonFlashcardDeck = JsonUtil.readJsonFile(
                filePath, JsonSerializableFlashcardDeck.class);
        if (!jsonFlashcardDeck.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFlashcardDeck.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashCardDeck) throws IOException {
        saveFlashcardDeck(flashCardDeck, filePath);
    }

    /**
     * Similar to {@link #saveFlashcardDeck(ReadOnlyFlashcardDeck)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashCardDeck, Path filePath) throws IOException {
        requireNonNull(flashCardDeck);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFlashcardDeck(flashCardDeck), filePath);
    }

}
