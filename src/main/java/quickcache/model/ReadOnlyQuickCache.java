package quickcache.model;

import javafx.collections.ObservableList;
import quickcache.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an QuickCache
 */
public interface ReadOnlyQuickCache {

    ObservableList<Flashcard> getFlashcardList();

}
