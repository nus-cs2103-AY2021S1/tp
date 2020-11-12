package seedu.address.logic.commands.lessons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.testutil.EditLessonDescriptorBuilder;

public class EditLessonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditLessonDescriptor descriptorWithSameValues = new EditLessonDescriptor(DESC_CS2030);
        assertTrue(DESC_CS2030.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2030.equals(DESC_CS2030));

        // null -> returns false
        assertFalse(DESC_CS2030.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2030.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2030.equals(DESC_CS2106));

        // different lesson -> returns false
        EditLessonDescriptor editedCS2030 = new EditLessonDescriptorBuilder(DESC_CS2030)
                .withName(VALID_LESSON_NAME_CS2106).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different tags -> returns false
        editedCS2030 = new EditLessonDescriptorBuilder(DESC_CS2030).withTags(VALID_LESSON_TAG_EASY).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));
    }
}
