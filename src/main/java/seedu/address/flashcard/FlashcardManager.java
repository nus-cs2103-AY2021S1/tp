package seedu.address.flashcard;

import java.util.ArrayList;
import java.util.List;

/**
 * FlashcardManager handles all the operations related to flashcards.
 */
public class FlashcardManager {
    private final List<Flashcard> flashcards;

    public FlashcardManager() {
        flashcards = new ArrayList<>();
    }

    /**
     * Adds a flashcard
     * @param flashcard the flashcard to be added.
     * @return {@code true}
     */
    public boolean addFlashcard(Flashcard flashcard) {
        return flashcards.add(flashcard);
    }

    /**
     * Deletes a flashcard at the index in the list.
     * @param index the index of the flashcard to be deleted.
     * @return the {@code Flashcard} that has been deleted.
     */
    public Flashcard deleteFlashcard(int index) {
        return flashcards.remove(index);
    }

    /**
     * Lists out the flashcards in a string format.
     * @return the list of flashcards in a {@code String}.
     */
    public String listFlashcards() {
        StringBuilder sb = new StringBuilder();
        for (Flashcard flashcard: flashcards) {
            sb.append(flashcard).append("\n");
        }
        return sb.toString();
    }
}
