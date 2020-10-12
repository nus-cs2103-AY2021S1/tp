package seedu.address.flashcard;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.testutil.TypicalFlashcards.RANDOM1;
import static seedu.address.testutil.TypicalFlashcards.RANDOM2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

class FlashcardTest {

    @Test
    public void constructor_negativeTimesTested_throwsIllegalArgumentException() {
        // negative times tested
        assertThrows(IllegalArgumentException.class, () -> new FlashcardBuilder(RANDOM1).withTimesTested(-1).build());

        // negative times tested correct
        assertThrows(
                IllegalArgumentException.class, () -> new FlashcardBuilder(RANDOM1).withTimesTestedCorrect(-1).build());
    }

    @Test
    public void constructor_timesTestedLessThanTimesTestedCorrect_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class, () -> new FlashcardBuilder(RANDOM1).withTimesTestedCorrect(1).build());
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(RANDOM1.isSameFlashcard(RANDOM1));

        // null -> returns false
        assertFalse(RANDOM1.isSameFlashcard(null));

        // different answer -> returns false
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withAnswer(VALID_ANSWER_AMY).build();
        assertFalse(RANDOM1.isSameFlashcard(editedRandom1));

        // different question -> returns false
        editedRandom1 = new FlashcardBuilder(RANDOM1).withQuestion(VALID_QUESTION_AMY).build();
        assertFalse(RANDOM1.isSameFlashcard(editedRandom1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard random1Copy = new FlashcardBuilder(RANDOM1).build();
        assertTrue(RANDOM1.equals(random1Copy));

        // same object -> returns true
        assertTrue(RANDOM1.equals(RANDOM1));

        // null -> returns false
        assertFalse(RANDOM1.equals(null));

        // different type -> returns false
        assertFalse(RANDOM1.equals(5));

        // different person -> returns false
        assertFalse(RANDOM1.equals(RANDOM2));

        // different answer -> returns false
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withAnswer(VALID_ANSWER_AMY).build();
        assertFalse(RANDOM1.equals(editedRandom1));

        // different question -> returns false
        editedRandom1 = new FlashcardBuilder(RANDOM1).withQuestion(VALID_QUESTION_AMY).build();
        assertFalse(RANDOM1.equals(editedRandom1));
    }

    @Test
    public void getFlashcardAfterTestSuccess() {
        Flashcard updatedRandom1 = new FlashcardBuilder(RANDOM1).withTimesTestedCorrect(1).withTimesTested(1).build();
        assertTrue(RANDOM1.getFlashcardAfterTestSuccess().isSameFlashcard(updatedRandom1));
        assertTrue(RANDOM1.getFlashcardAfterTestSuccess().equals(updatedRandom1));
    }

    @Test
    public void getFlashcardAfterTestFailure() {
        Flashcard updatedRandom1 = new FlashcardBuilder(RANDOM1).withTimesTested(1).build();
        assertTrue(RANDOM1.getFlashcardAfterTestFailure().isSameFlashcard(updatedRandom1));
        assertTrue(RANDOM1.getFlashcardAfterTestFailure().equals(updatedRandom1));
    }

}
