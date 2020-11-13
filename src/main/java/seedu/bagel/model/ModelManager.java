package seedu.bagel.model;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Bagel bagel;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;
    // private final FilteredList<FlashcardSet> filteredSetList;

    /**
     * Initializes a ModelManager with the given Bagel and userPrefs.
     */
    public ModelManager(ReadOnlyBagel bagel, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(bagel, userPrefs);

        logger.fine("Initializing with Bagel: " + bagel + " and user prefs " + userPrefs);

        this.bagel = new Bagel(bagel);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.bagel.getFlashcardList());
    }

    public ModelManager() {
        this(new Bagel(), new UserPrefs());
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
    public Path getBagelFilePath() {
        return userPrefs.getBagelFilePath();
    }

    @Override
    public void setBagelFilePath(Path bagelFilePath) {
        requireNonNull(bagelFilePath);
        userPrefs.setBagelFilePath(bagelFilePath);
    }

    //=========== Bagel ================================================================================

    @Override
    public void setBagel(ReadOnlyBagel bagel) {
        this.bagel.resetData(bagel);
    }

    @Override
    public ReadOnlyBagel getBagel() {
        return bagel;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return bagel.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        bagel.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        bagel.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);
        bagel.setFlashcard(target, editedFlashcard);
    }

    @Override
    public void viewFlashcard(Predicate<Flashcard> predicate) {
        updateFilteredFlashcardList(predicate);
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedBagel}
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
    public void sortFlashcardList(Comparator<Flashcard> comparator) {
        requireNonNull(comparator);
        ObservableList<Flashcard> sortedList = bagel.getFlashcardList().sorted(comparator);
        bagel.setFlashcards(sortedList);
    }

    //=========== Set of FlashcardSets Accessors =============================================================

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
        return bagel.equals(other.bagel)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

    @Override
    public boolean isEmpty() {
        return bagel.getSetOfFlashcardSets().get().isEmpty();
    }

    @Override
    public boolean hasFlashcardSet(FlashcardSet flashcardSet) {
        requireNonNull(flashcardSet);
        return bagel.getSetOfFlashcardSets().get().contains(flashcardSet);
    }

}
