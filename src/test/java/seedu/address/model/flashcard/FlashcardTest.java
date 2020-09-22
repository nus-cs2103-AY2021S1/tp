package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_2;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(FLASHCARD_1.isSameQuestion(FLASHCARD_1));

        // null -> returns false
        assertFalse(FLASHCARD_1.isSameQuestion(null));

        // different question -> returns false
        Flashcard editedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).withQuestion(VALID_QUESTION_2).build();
        assertFalse(FLASHCARD_1.isSameQuestion(editedFlashcardOne));

        // different answer -> returns true
        editedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).withAnswer(VALID_ANSWER_2).build();
        assertTrue(FLASHCARD_1.isSameQuestion(editedFlashcardOne));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard flashcardOneCopy = new FlashcardBuilder(FLASHCARD_1).build();
        assertTrue(FLASHCARD_1.equals(flashcardOneCopy));

        // same object -> returns true
        assertTrue(FLASHCARD_1.equals(FLASHCARD_1));

        // null -> returns false
        assertFalse(FLASHCARD_1.equals(null));

        // different type -> returns false
        assertFalse(FLASHCARD_1.equals(5));

        // different flashcard -> returns false
        assertFalse(FLASHCARD_1.equals(FLASHCARD_2));

        // different questions -> returns false
        Flashcard editedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).withQuestion(VALID_QUESTION_2).build();
        assertFalse(FLASHCARD_1.equals(editedFlashcardOne));

        // different answer -> returns false
        editedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).withAnswer(VALID_ANSWER_2).build();
        assertFalse(FLASHCARD_1.equals(editedFlashcardOne));

        // different category -> returns false
        editedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).withCategory(VALID_CATEGORY_2).build();
        assertFalse(FLASHCARD_1.equals(editedFlashcardOne));
    }
}
