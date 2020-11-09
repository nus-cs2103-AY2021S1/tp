package seedu.flashcard.logic;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.flashcard.logic.commands.IncrementStatsCommand;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Maintains state for a study mode.
 */
public class StudyManager {
    private ObservableList<Flashcard> flashcardList;
    private Logic logic;
    private HashMap<Flashcard, Boolean> reviewedStatusMap;
    private int currentIndex;


    /**
     * Creates a {@code StudyManager} with the specified list of flashcards and logic manager
     * @param logic
     */
    public StudyManager(Logic logic) {
        this.flashcardList = logic.getFilteredFlashcardList();
        this.logic = logic;
        initialiseHashMap();
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

    private void initialiseHashMap() {
        reviewedStatusMap = new HashMap<>();
        for (Flashcard flashcard : flashcardList) {
            reviewedStatusMap.put(flashcard, false);
        }
    }

    public void markCurrentFlashcardAsReviewed() {
        reviewedStatusMap.replace(getCurrentFlashcard(), true);
    }

    public boolean isCurrentFlashcardReviewed() {
        return reviewedStatusMap.get(getCurrentFlashcard());
    }

    /**
     * Increments review frequency and success frequency in Statistics.
     *
     * @param isCorrect Represents the correctness of the answer to the flashcard.
     * @throws CommandException
     */
    public void incrementCurrentFlashcardStatistics(boolean isCorrect) throws CommandException {
        logic.executeCommand(new IncrementStatsCommand(getCurrentFlashcard(), isCorrect));
    }

    public int getNumberOfCards() {
        return flashcardList.size();
    }

}
