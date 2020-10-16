package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.DESC_THREE;
import static quickcache.logic.commands.CommandTestUtil.DESC_TWO;
import static quickcache.logic.commands.CommandTestUtil.VALID_ANSWER_THREE;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_THREE;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.EditCommand.EditFlashcardDescriptor;
import quickcache.testutil.EditFlashcardDescriptorBuilder;


public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_TWO);
        assertTrue(DESC_TWO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TWO.equals(DESC_TWO));

        // null -> returns false
        assertFalse(DESC_TWO.equals(null));

        // different types -> returns false
        assertFalse(DESC_TWO.equals(5));

        // different values -> returns false
        assertFalse(DESC_TWO.equals(DESC_THREE));

        // different question -> returns false
        EditFlashcardDescriptor editedDescTwo = new EditFlashcardDescriptorBuilder(DESC_TWO)
            .withQuestion(VALID_QUESTION_THREE).build();
        assertFalse(DESC_TWO.equals(editedDescTwo));

        // different answer -> returns false
        editedDescTwo = new EditFlashcardDescriptorBuilder(DESC_TWO).withAnswer(VALID_ANSWER_THREE).build();
        assertFalse(DESC_TWO.equals(editedDescTwo));


    }
}
