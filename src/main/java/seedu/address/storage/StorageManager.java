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
    private FlashcardDeckStorage flashcardDeckStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashcardDeckStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashcardDeckStorage flashcardDeckStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashcardDeckStorage = flashcardDeckStorage;
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
        return flashcardDeckStorage.getFlashcardDeckFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck() throws DataConversionException, IOException {
        return readFlashcardDeck(flashcardDeckStorage.getFlashcardDeckFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashcardDeck> readFlashcardDeck(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashcardDeckStorage.readFlashcardDeck(filePath);
    }

    @Override
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck) throws IOException {
        saveFlashcardDeck(flashcardDeck, flashcardDeckStorage.getFlashcardDeckFilePath());
    }

    @Override
    public void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashcardDeckStorage.saveFlashcardDeck(flashcardDeck, filePath);
    }

}
