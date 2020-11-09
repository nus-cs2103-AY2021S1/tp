package seedu.flashcard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashcard.commons.exceptions.DataConversionException;
import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;

/**
 * Represents a storage for {@link FlashcardDeck}.
 */
public interface FlashcardDeckStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFlashcardDeckFilePath();

    /**
     * Returns FlashcardDeck data as a {@link ReadOnlyFlashcardDeck}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFlashcardDeck> readFlashcardDeck() throws DataConversionException, IOException;

    /**
     * @see #getFlashcardDeckFilePath()
     */
    Optional<ReadOnlyFlashcardDeck> readFlashcardDeck(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFlashcardDeck} to the storage.
     * @param flashcardDeck cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck) throws IOException;

    /**
     * @see #saveFlashcardDeck(ReadOnlyFlashcardDeck)
     */
    void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck, Path filePath) throws IOException;

}
