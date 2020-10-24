package seedu.flashcard.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.commons.core.LogsCenter;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Represents the in-memory model of the flashcard deck data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FlashcardDeck flashcardDeck;
    private final UserPrefs userPrefs;
    private final SortedList<Flashcard> sortedFlashcards;
    private final FilteredList<Flashcard> filteredFlashcards;

    /**
     * Initializes a ModelManager with the given flashcardDeck and userPrefs.
     */
    public ModelManager(ReadOnlyFlashcardDeck flashcardDeck, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(flashcardDeck, userPrefs);

        logger.fine("Initializing with flashcard deck: " + flashcardDeck + " and user prefs " + userPrefs);

        this.flashcardDeck = new FlashcardDeck(flashcardDeck);
        this.userPrefs = new UserPrefs(userPrefs);
        sortedFlashcards = new SortedList<>(this.flashcardDeck.getFlashcardList());
        filteredFlashcards = new FilteredList<>(this.sortedFlashcards);
    }

    public ModelManager() {
        this(new FlashcardDeck(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFlashcardDeckFilePath() {
        return userPrefs.getFlashcardDeckFilePath();
    }

    @Override
    public void setFlashcardDeckFilePath(Path flashcardDeckFilePath) {
        requireNonNull(flashcardDeckFilePath);
        userPrefs.setFlashcardDeckFilePath(flashcardDeckFilePath);
    }

    //=========== FlashcardDeck ==============================================================================

    @Override
    public void setFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck) {
        this.flashcardDeck.resetData(flashcardDeck);
    }

    @Override
    public ReadOnlyFlashcardDeck getFlashcardDeck() {
        return flashcardDeck;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcardDeck.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        flashcardDeck.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        flashcardDeck.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);

        flashcardDeck.setFlashcard(target, editedFlashcard);
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedFlashcardDeck}
     */
    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return filteredFlashcards;
    }

    @Override
    public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
    }

    @Override
    public void sortFilteredFlashcardList(Comparator<Flashcard> comparator) {
        requireNonNull(comparator);
        sortedFlashcards.setComparator(comparator);
        flashcardDeck.setFlashcards(sortedFlashcards);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return flashcardDeck.equals(other.flashcardDeck)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

}
