package seedu.flashcard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashcard.commons.exceptions.DataConversionException;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.ReadOnlyUserPrefs;
import seedu.flashcard.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FlashcardDeckStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFlashcardDeckFilePath();

    @Override
    Optional<ReadOnlyFlashcardDeck> readFlashcardDeck() throws DataConversionException, IOException;

    @Override
    void saveFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck) throws IOException;

}
