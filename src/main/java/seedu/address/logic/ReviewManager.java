package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;

/**
 * Maintains state for the review function.
 */
public class ReviewManager {

    public static final String EXIT_MESSAGE = "Exited review mode";
    public static final String NO_NEXT_FLASHCARD_MESSAGE = "There are no more more flashcards to review";
    public static final String NO_PREVIOUS_FLASHCARD_MESSAGE = "No previous flashcards available for review";
    private ObservableList<Flashcard> flashcardList;
    private int currentIndex;

    /**
     * Creates a {@code ReviewManager} with the specified list of flashcards.
     * @param flashcardList
     */
    public ReviewManager(ObservableList<Flashcard> flashcardList) {
        this.flashcardList = flashcardList;
        currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < flashcardList.size() - 1;
    }

    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public Flashcard getNextFlashcard() {
        if (!hasNext()) {
            return null;
        }
        currentIndex++;
        return flashcardList.get(currentIndex);
    }

    public Flashcard getCurrentFlashcard() {
        return flashcardList.get(currentIndex);
    }

    public Flashcard getPrevFlashcard() {
        if (!hasPrevious()) {
            return null;
        }
        currentIndex--;
        return flashcardList.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
