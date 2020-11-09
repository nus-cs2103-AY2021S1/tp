package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.task.DateTimeDue;

public class SearchTaskCommandTest {
    @Test
    public void constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SearchTaskCommand(null));
    }

    @Test
    public void equals() {
        SearchTaskCommand searchCommand = new SearchTaskCommand(new DateTimeDue("20200105"));
        SearchTaskCommand correctSearchCommand = new SearchTaskCommand(new DateTimeDue("20200105"));
        SearchTaskCommand wrongSearchCommand = new SearchTaskCommand(new DateTimeDue("20200106"));

        // same object -> returns true
        assertTrue(searchCommand.equals(correctSearchCommand));

        // null -> returns false
        assertFalse(searchCommand.equals(null));

        // Different types -> returns false
        assertFalse(searchCommand.equals(new ExitCommand()));

        // Different due date -> returns false
        assertNotEquals(wrongSearchCommand, searchCommand);
    }
}
