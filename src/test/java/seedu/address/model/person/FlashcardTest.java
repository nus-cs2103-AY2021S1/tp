package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_2;
import static seedu.address.testutil.TypicalFlashcards.ONE;
import static seedu.address.testutil.TypicalFlashcards.TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(ONE.isSameQuestion(ONE));

        // null -> returns false
        assertFalse(ONE.isSameQuestion(null));

        // different question -> returns false
        Flashcard editedFlashcardOne = new FlashcardBuilder(ONE).withQuestion(VALID_QUESTION_2).build();
        assertFalse(ONE.isSameQuestion(editedFlashcardOne));

        // different answer -> returns true
        editedFlashcardOne = new FlashcardBuilder(ONE).withAnswer(VALID_ANSWER_2).build();
        assertTrue(ONE.isSameQuestion(editedFlashcardOne));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard flashcardOneCopy = new FlashcardBuilder(ONE).build();
        assertTrue(ONE.equals(flashcardOneCopy));

        // same object -> returns true
        assertTrue(ONE.equals(ONE));

        // null -> returns false
        assertFalse(ONE.equals(null));

        // different type -> returns false
        assertFalse(ONE.equals(5));

        // different flashcard -> returns false
        assertFalse(ONE.equals(TWO));

        // different questions -> returns false
        Flashcard editedFlashcardOne = new FlashcardBuilder(ONE).withQuestion(VALID_QUESTION_2).build();
        assertFalse(ONE.equals(editedFlashcardOne));

        // different answer -> returns false
        editedFlashcardOne = new FlashcardBuilder(ONE).withAnswer(VALID_ANSWER_2).build();
        assertFalse(ONE.equals(editedFlashcardOne));

        // different category -> returns false
        editedFlashcardOne = new FlashcardBuilder(ONE).withCategory(VALID_CATEGORY_2).build();
        assertFalse(ONE.equals(editedFlashcardOne));
    }
}
