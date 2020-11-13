package seedu.bagel.model.flashcard;

import static java.lang.Integer.parseInt;

import java.util.HashSet;
import java.util.Set;

/**
 * Maintains a set that keeps track of the current number of flashcardSets in use.
 */

public class SetOfFlashcardSets {

    private Set<FlashcardSet> setOfFlashcardSets;

    /* keeps track of the number of flashcards that have the same flashcardSet in an array.
    e.g. if 10 flashcards have flashcardSet 1, numOfFlashcardsWithFSet[0] = 10.
    e.g. 2 flashcards have flashcardSet 3, numOfFlashcardsWithFSet[2] = 2.
    */
    private int[] numOfFlashcardsWithFSet;

    /**
     * Initialises a set of flashcardSets, and keeps count of the number of flashcards with each flashcardSet.
     */
    public SetOfFlashcardSets() {
        setOfFlashcardSets = new HashSet<>();
        numOfFlashcardsWithFSet = new int[20];
    }

    /**
     * Updates the flashcardSets in the set after an add command.
     * @param flashcard to be added.
     */
    public void add(Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcard.getFlashcardSet();
        setOfFlashcardSets.add(flashcardSet);

        //updating count
        int flashcardSetValue = parseInt(flashcardSet.value) - 1;
        numOfFlashcardsWithFSet[flashcardSetValue]++;
    }

    /**
     * Updates the flashcardSets in the set after an edit command.
     * @param target flashcard's flashcardSet to be removed if it is the only flashcard with that flashcardSet.
     * @param edited flashcard's flashcardSet to be added.
     */
    public void edit(Flashcard target, Flashcard edited) {
        FlashcardSet targetFlashcardSet = target.getFlashcardSet();
        FlashcardSet editedFlashcardSet = edited.getFlashcardSet();
        setOfFlashcardSets.add(editedFlashcardSet);

        //updating count
        int editedFlashcardSetValue = parseInt(editedFlashcardSet.value) - 1;
        numOfFlashcardsWithFSet[editedFlashcardSetValue]++;
        int targetFlashcardSetValue = parseInt(targetFlashcardSet.value) - 1;
        numOfFlashcardsWithFSet[targetFlashcardSetValue]--;

        //updating Set of flashcardSets
        if (numOfFlashcardsWithFSet[targetFlashcardSetValue] == 0) {
            setOfFlashcardSets.remove(targetFlashcardSet);
        }
    }

    /**
     * Updates the flashcardSets in the set after a delete command.
     * @param flashcard 's flashcardSet to be removed if it is the only flashcard with that flashcardSet.
     */
    public void remove(Flashcard flashcard) {
        FlashcardSet flashcardSet = flashcard.getFlashcardSet();

        //updating count
        int flashcardSetValue = parseInt(flashcardSet.value) - 1;
        numOfFlashcardsWithFSet[flashcardSetValue]--;

        if (numOfFlashcardsWithFSet[flashcardSetValue] == 0) {
            setOfFlashcardSets.remove(flashcardSet);
        }
    }

    /**
     * Returns a copy of the set of flashcardSets to ensure the set remains immutable.
     */
    public Set<FlashcardSet> get() {
        return new HashSet<>(setOfFlashcardSets);
    }


}
