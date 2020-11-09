package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_CLEAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CLEAN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.ASSIGNMENT;
import static seedu.address.testutil.TypicalTodos.CHORES;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class TodoTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Todo todo = new TodoBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> todo.getTags().remove(0));
    }

    @Test
    public void isSameTodo() {
        // same object -> returns true
        assertTrue(ASSIGNMENT.isSameTodo(ASSIGNMENT));

        // null -> returns false
        assertFalse(ASSIGNMENT.isSameTodo(null));

        // different description and datetime -> returns false
        Todo editedAssignment = new TodoBuilder(ASSIGNMENT).withDescription(VALID_DESCRIPTION_CLEAN)
                .withDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(ASSIGNMENT.isSameTodo(editedAssignment));

        // different description -> returns false
        editedAssignment = new TodoBuilder(ASSIGNMENT).withDescription(VALID_DESCRIPTION_CLEAN).build();
        assertFalse(ASSIGNMENT.isSameTodo(editedAssignment));

        // different date an timed -> returns false
        editedAssignment = new TodoBuilder(ASSIGNMENT).withDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(ASSIGNMENT.isSameTodo(editedAssignment));
    }

    @Test
    public void equals() {
        // exact same object -> returns true
        assertTrue(ASSIGNMENT.equals(ASSIGNMENT));

        // null -> returns false
        assertFalse(ASSIGNMENT.equals(null));

        // different type -> returns false
        assertFalse(ASSIGNMENT.equals(5));

        // different todo -> returns false
        assertFalse(ASSIGNMENT.equals(CHORES));

        // different description and datetime -> returns false
        Todo editedAssignment = new TodoBuilder(ASSIGNMENT).withDescription(VALID_DESCRIPTION_CLEAN)
                .withDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(ASSIGNMENT.equals(editedAssignment));

        // different description -> returns false
        editedAssignment = new TodoBuilder(ASSIGNMENT).withDescription(VALID_DESCRIPTION_CLEAN).build();
        assertFalse(ASSIGNMENT.equals(editedAssignment));

        // different date and time -> returns false
        editedAssignment = new TodoBuilder(ASSIGNMENT).withDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(ASSIGNMENT.equals(editedAssignment));
    }
}
