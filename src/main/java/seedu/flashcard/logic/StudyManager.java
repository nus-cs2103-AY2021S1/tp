package seedu.flashcard.logic;

import javafx.collections.ObservableList;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Maintains state for the review function.
 */
public class StudyManager {
    public static final String NO_NEXT_FLASHCARD_MESSAGE = "There are no more flashcards to review";
    public static final String NO_PREVIOUS_FLASHCARD_MESSAGE = "No previous flashcards available for review";
    private ObservableList<Flashcard> flashcardList;
    private int currentIndex;

    /**
     * Creates a {@code ReviewManager} with the specified list of flashcards.
     * @param flashcardList
     */
    public StudyManager(ObservableList<Flashcard> flashcardList) {
        this.flashcardList = flashcardList;
        currentIndex = 0;
    }

    public boolean hasNextFlashcard() {
        return currentIndex < flashcardList.size() - 1;
    }

    public boolean hasPreviousFlashcard() {
        return currentIndex > 0;
    }

    public Flashcard getNextFlashcard() {
        if (!hasNextFlashcard()) {
            return null;
        }
        currentIndex++;
        return flashcardList.get(currentIndex);
    }

    public Flashcard getCurrentFlashcard() {
        return flashcardList.get(currentIndex);
    }

    public Flashcard getPrevFlashcard() {
        if (!hasPreviousFlashcard()) {
            return null;
        }
        currentIndex--;
        return flashcardList.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
