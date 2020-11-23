package seedu.tr4cker.model.daily;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;

/**
 * Contains integration tests for {@code Todo}.
 */
public class TodoTest {
    private Todo todo1 = new Todo(new Name("Name 1"), new Deadline("09-09-2021 2000", false));
    private Todo todo2 = new Todo(new Name("Name 2"), new Deadline("08-08-2021 1800", false));
    private Todo todo1Copy = new Todo(new Name("Name 1"), new Deadline("09-09-2021 2000", false));
    private Todo todo3 = new Todo(new Name("Name 2"), new Deadline("15-09-2022 2020", false));
    private Todo todo4 = new Todo(new Name("Name 4"), new Deadline("09-09-2021 2000", false));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Todo(null, null));
    }

    @Test
    public void constructor_success() {
        Name name = new Name("name");
        Deadline deadline = new Deadline("01-02-2021 1900", false);
        assertDoesNotThrow(() -> new Todo(name, deadline));
    }

    @Test
    public void isSameTodoTest() {
        // same object -> returns true
        assertTrue(todo1.isSameTodo(todo1));

        //same name and deadline -> returns true
        assertTrue(todo1.isSameTodo(todo1Copy));

        //null -> returns false
        assertFalse(todo1.isSameTodo(null));

        //different name -> returns false
        assertFalse(todo1.isSameTodo(todo4));

        //different deadline -> returns false
        assertFalse(todo2.isSameTodo(todo3));

        //different deadline and name -> returns false
        assertFalse(todo1.isSameTodo(todo2));
    }

    @Test
    public void testEquals() {
        // same object -> returns true
        assertEquals(todo1, todo1);

        // null -> returns false
        assertNotEquals(todo1, null);

        // different name and deadline -> return false
        assertNotEquals(todo1, todo2);

        // different name -> return false
        assertNotEquals(todo1, todo4);

        // different deadline -> return false
        assertNotEquals(todo2, todo3);
    }

    @Test
    public void testHashcode() {
        Name name = new Name("Name 1");
        Deadline deadline = new Deadline("09-09-2021 2000", false);
        assertEquals(todo1.hashCode(), Objects.hash(name, deadline));
    }
}
