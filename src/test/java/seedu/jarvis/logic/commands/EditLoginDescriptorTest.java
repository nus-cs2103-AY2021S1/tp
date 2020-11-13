package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_PROF;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_STUDENT;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_PASSWORD_2;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_USERNAME_PROF;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.edit.EditLoginCommand.EditLoginDescriptor;
import seedu.jarvis.testutil.EditLoginDescriptorBuilder;

public class EditLoginDescriptorTest {
    @Test
    public void equals() {
        EditLoginDescriptor descriptor = new EditLoginDescriptor(DESC_STUDENT);

        // same values -> returns true
        assertTrue(DESC_STUDENT.equals(descriptor));

        // same object -> returns true
        assertTrue(DESC_STUDENT.equals(DESC_STUDENT));

        // null -> returns false
        assertFalse(DESC_STUDENT.equals(null));

        // different types -> returns false
        assertFalse(DESC_STUDENT.equals(5));

        // different values -> returns false
        assertFalse(DESC_STUDENT.equals(DESC_PROF));

        // different username -> returns false
        EditLoginDescriptor editedStudent = new EditLoginDescriptorBuilder(DESC_STUDENT)
                .withUsername(VALID_USERNAME_PROF).build();
        assertFalse(DESC_STUDENT.equals(editedStudent));

        // different password -> returns false
        editedStudent = new EditLoginDescriptorBuilder(DESC_STUDENT).withPassword(VALID_PASSWORD_2).build();
        assertFalse(DESC_STUDENT.equals(editedStudent));
    }
}
