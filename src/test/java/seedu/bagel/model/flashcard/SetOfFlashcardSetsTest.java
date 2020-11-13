package seedu.bagel.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.ALICE;
import static seedu.bagel.testutil.TypicalFlashcards.AMY;
import static seedu.bagel.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

public class SetOfFlashcardSetsTest {

    private final SetOfFlashcardSets setOfFlashcardSets = new SetOfFlashcardSets();

    @Test
    public void add_correctFlashcard_returnsTrue() {
        setOfFlashcardSets.add(ALICE);
        assertTrue(setOfFlashcardSets.get().contains(ALICE.getFlashcardSet()));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> setOfFlashcardSets.add(null));
    }

    @Test
    public void edit_correctFlashcards_returnsTrue() {
        setOfFlashcardSets.add(AMY);
        setOfFlashcardSets.edit(AMY, BOB);
        assertTrue(setOfFlashcardSets.get().contains(BOB.getFlashcardSet()));
        assertFalse(setOfFlashcardSets.get().contains(AMY.getFlashcardSet()));
    }

    @Test
    public void edit_nullTargetFlashcard_throwNullPointerException() {
        setOfFlashcardSets.add(ALICE);
        assertThrows(NullPointerException.class, () ->setOfFlashcardSets.edit(null, BOB));
    }

    @Test
    public void remove_correctFlashcard_returnsTrue() {
        setOfFlashcardSets.add(ALICE);
        setOfFlashcardSets.remove(ALICE);
        assertTrue(setOfFlashcardSets.get().isEmpty());
    }

    @Test
    public void remove_nullFlashcard_throwsFlashcardNotFoundException() {
        assertThrows(NullPointerException.class, () -> setOfFlashcardSets.remove(null));
    }

    @Test
    public void numberOfSetsIsCorrect() {
        setOfFlashcardSets.add(AMY);
        setOfFlashcardSets.add(BOB);
        // AMY belongs to set 2, BOB belongs to set 1
        assertEquals(setOfFlashcardSets.get().size(), 2);
    }
}
