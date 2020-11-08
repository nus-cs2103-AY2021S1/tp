package seedu.address.testutil.todolist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2105;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.model.task.Task;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_LAB05);
        assertTrue(DESC_LAB05.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LAB05.equals(DESC_LAB05));

        // null -> returns false
        assertFalse(DESC_LAB05.equals(null));

        // different types -> returns false
        assertFalse(DESC_LAB05.equals(5));

        // different values -> returns false
        assertFalse(DESC_LAB05.equals(DESC_LAB07));

        // different name -> returns false
        EditTaskDescriptor editedLab05 = new EditTaskDescriptorBuilder(DESC_LAB05).withName(VALID_NAME_LAB07).build();
        assertFalse(DESC_LAB05.equals(editedLab05));

        // different tags -> returns false
        editedLab05 = new EditTaskDescriptorBuilder(DESC_LAB05).withTags(VALID_TAG_CS2105).build();
        assertFalse(DESC_LAB05.equals(editedLab05));

        // different priority -> returns false
        editedLab05 = new EditTaskDescriptorBuilder(DESC_LAB05).withPriority(VALID_PRIORITY_NORMAL).build();
        assertFalse(DESC_LAB05.equals(editedLab05));

        // different date -> returns false
        editedLab05 = new EditTaskDescriptorBuilder(DESC_LAB05).withDate(VALID_DATE2).build();
        assertFalse(DESC_LAB05.equals(editedLab05));
    }

    @Test
    public void isAnyFieldEdited() {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        // all field specified -> true
        assertTrue(descriptor.isAnyFieldEdited());

        // should not call withTags() since this the same as editing the tags
        // use empty constructor for EditDescriptor to make tags null
        descriptor = new EditTaskDescriptorBuilder().withName(null)
                .withPriority(null).withDate(null)
                .build();
        // all field not specified -> false
        assertFalse(descriptor.isAnyFieldEdited());

        descriptor = new EditTaskDescriptorBuilder().withName(null)
            .withTags().withPriority(null)
            .withDate(null).build();
        // tags are empty but specified -> true
        assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditTaskDescriptorBuilder().withName(null)
            .withPriority(null)
            .withDate(null).withIsPriorityDeleted(true)
            .build();
        // no fields specified, only priority field is deleted -> true
        assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditTaskDescriptorBuilder().withName(null)
            .withPriority(null)
            .withDate(null).withIsDateDeleted(true)
            .build();
        /// no fields specified, only date field is deleted -> true
        assertTrue(descriptor.isAnyFieldEdited());
    }
}
