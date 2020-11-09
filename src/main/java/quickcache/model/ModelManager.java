package quickcache.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import quickcache.commons.core.GuiSettings;
import quickcache.commons.core.LogsCenter;
import quickcache.commons.util.CollectionUtil;
import quickcache.model.flashcard.Flashcard;

/**
 * Represents the in-memory model of the QuickCache data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final QuickCache quickCache;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;


    /**
     * Initializes a ModelManager with the given QuickCache and userPrefs.
     */
    public ModelManager(ReadOnlyQuickCache quickCache, ReadOnlyUserPrefs userPrefs) {
        super();
        CollectionUtil.requireAllNonNull(quickCache, userPrefs);

        logger.fine("Initializing with quick cache: " + quickCache + " and user prefs " + userPrefs);

        this.quickCache = new QuickCache(quickCache);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.quickCache.getFlashcardList());

    }

    public ModelManager() {
        this(new QuickCache(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public Path getQuickCacheFilePath() {
        return userPrefs.getQuickCacheFilePath();
    }

    @Override
    public void setQuickCacheFilePath(Path quickCacheFilePath) {
        requireNonNull(quickCacheFilePath);
        userPrefs.setQuickCacheFilePath(quickCacheFilePath);
    }

    //=========== QuickCache ================================================================================

    @Override
    public ReadOnlyQuickCache getQuickCache() {
        return quickCache;
    }

    @Override
    public void setQuickCache(ReadOnlyQuickCache quickCache) {
        this.quickCache.resetData(quickCache);
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return quickCache.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        quickCache.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        quickCache.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        CollectionUtil.requireAllNonNull(target, editedFlashcard);

        quickCache.setFlashcard(target, editedFlashcard);
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return filteredFlashcards;
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
        return quickCache.equals(other.quickCache)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

}
