package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFlashcardDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Flashcard data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashcardDeckStorage flashCardDeckStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashcardDeckStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashcardDeckStorage flashCardDeckStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashCardDeckStorage = flashCardDeckStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Flashcard methods ==============================

    @Override
    public Path getFlashcardDeckFilePath() {
        return flashCardDeckStorage.getFlashcardDeckFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck() throws DataConversionException, IOException {
        return readFlashcardDeck(flashCardDeckStorage.getFlashcardDeckFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashCardDeckStorage.readFlashcardDeck(filePath);
    }

    @Override
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashCardDeck) throws IOException {
        saveFlashcardDeck(flashCardDeck, flashCardDeckStorage.getFlashcardDeckFilePath());
    }

    @Override
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashCardDeck, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashCardDeckStorage.saveFlashcardDeck(flashCardDeck, filePath);
    }

}
