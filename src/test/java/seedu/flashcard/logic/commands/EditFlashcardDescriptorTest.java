package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.logic.commands.CommandTestUtil.DESC_FLASHCARD_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.DESC_FLASHCARD_2;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_ANSWER_2;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_CATEGORY_2;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_NOTE_2;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_QUESTION_2;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_RATING_2;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.flashcard.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_FLASHCARD_1);
        assertTrue(DESC_FLASHCARD_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FLASHCARD_1.equals(DESC_FLASHCARD_1));

        // null -> returns false
        assertFalse(DESC_FLASHCARD_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_FLASHCARD_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_FLASHCARD_1.equals(DESC_FLASHCARD_2));

        // different question -> returns false
        EditFlashcardDescriptor editedFlashcard = new EditFlashcardDescriptorBuilder(DESC_FLASHCARD_1)
                .withQuestion(VALID_QUESTION_2).build();
        assertFalse(DESC_FLASHCARD_1.equals(editedFlashcard));

        // different answer -> returns false
        editedFlashcard = new EditFlashcardDescriptorBuilder(DESC_FLASHCARD_1).withAnswer(VALID_ANSWER_2).build();
        assertFalse(DESC_FLASHCARD_1.equals(editedFlashcard));

        // different category -> returns false
        editedFlashcard = new EditFlashcardDescriptorBuilder(DESC_FLASHCARD_1).withCategory(VALID_CATEGORY_2).build();
        assertFalse(DESC_FLASHCARD_1.equals(editedFlashcard));

        // different note -> returns false
        editedFlashcard = new EditFlashcardDescriptorBuilder(DESC_FLASHCARD_1).withNote(VALID_NOTE_2).build();
        assertFalse(DESC_FLASHCARD_1.equals(editedFlashcard));

        // different rating -> returns false
        editedFlashcard = new EditFlashcardDescriptorBuilder(DESC_FLASHCARD_1).withRating(VALID_RATING_2).build();
        assertFalse(DESC_FLASHCARD_1.equals(editedFlashcard));
    }
}
