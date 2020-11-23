package seedu.tr4cker.model.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.daily.exceptions.DuplicateTodoException;
import seedu.tr4cker.model.daily.exceptions.TodoNotFoundException;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;

/**
 * Contains integration tests for {@code UniqueDailyList}.
 */
public class UniqueDailyListTest {

    private final UniqueDailyList uniqueDailyList = new UniqueDailyList();
    private Todo todo1 = new Todo(new Name("Name 1"), new Deadline("09-09-2021 2000", false));
    private Todo todo2 = new Todo(new Name("Name 2"), new Deadline("08-08-2021 1800", false));

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.contains(null));
    }

    @Test
    public void contains_todoNotInList_returnsFalse() {
        assertFalse(uniqueDailyList.contains(todo1));
    }

    @Test
    public void contains_todoInList_returnsTrue() {
        uniqueDailyList.add(todo1);
        assertTrue(uniqueDailyList.contains(todo1));
    }

    @Test
    public void contains_todoWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDailyList.add(todo1);
        Todo newTodo = new Todo(new Name("Name 1"), new Deadline("09-09-2021 2000", false));
        assertTrue(uniqueDailyList.contains(newTodo));
    }

    @Test
    public void add_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.add(null));
    }

    @Test
    public void add_duplicateTodo_throwsDuplicateTodoException() {
        uniqueDailyList.add(todo1);
        assertThrows(DuplicateTodoException.class, () -> uniqueDailyList.add(todo1));
    }

    @Test
    public void setTodo_nullTargetTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.setTodo(null, todo1));
    }

    @Test
    public void setTodo_nullEditedTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.setTodo(todo1, null));
    }

    @Test
    public void setTodo_targetTodoNotInList_throwsTodoNotFoundException() {
        assertThrows(TodoNotFoundException.class, () -> uniqueDailyList.setTodo(todo1, todo2));
    }

    @Test
    public void setTodo_editedTodoIsSameTodo_success() {
        uniqueDailyList.add(todo1);
        uniqueDailyList.setTodo(todo1, todo1);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        expectedUniqueDailyList.add(todo1);
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodo_editedTodoHasSameIdentity_success() {
        uniqueDailyList.add(todo1);
        Todo newTodo = new Todo(new Name("Name 1"), new Deadline("09-09-2021 2000", false));
        uniqueDailyList.setTodo(todo1, newTodo);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        expectedUniqueDailyList.add(newTodo);
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodo_editedTodoHasDifferentIdentity_success() {
        uniqueDailyList.add(todo1);
        uniqueDailyList.setTodo(todo1, todo2);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        expectedUniqueDailyList.add(todo2);
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodo_editedTodoHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueDailyList.add(todo1);
        uniqueDailyList.add(todo2);
        assertThrows(DuplicateTodoException.class, () -> uniqueDailyList.setTodo(todo1, todo2));
    }

    @Test
    public void remove_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.remove(null));
    }

    @Test
    public void remove_todoDoesNotExist_throwsTodoNotFoundException() {
        assertThrows(TodoNotFoundException.class, () -> uniqueDailyList.remove(todo1));
    }

    @Test
    public void remove_existingTodo_removesTodo() {
        uniqueDailyList.add(todo1);
        uniqueDailyList.remove(todo1);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodos_nullUniqueDailyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.setTodos((UniqueDailyList) null));
    }

    @Test
    public void setTodos_uniqueDailyList_replacesOwnListWithProvidedUniqueDailyList() {
        uniqueDailyList.add(todo1);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        expectedUniqueDailyList.add(todo2);
        uniqueDailyList.setTodos(expectedUniqueDailyList);
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodos_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDailyList.setTodos((List<Todo>) null));
    }

    @Test
    public void setTodos_list_replacesOwnListWithProvidedList() {
        uniqueDailyList.add(todo1);
        List<Todo> todoList = Collections.singletonList(todo2);
        uniqueDailyList.setTodos(todoList);
        UniqueDailyList expectedUniqueDailyList = new UniqueDailyList();
        expectedUniqueDailyList.add(todo2);
        assertEquals(expectedUniqueDailyList, uniqueDailyList);
    }

    @Test
    public void setTodos_listWithDuplicateTodos_throwsDuplicateTaskException() {
        List<Todo> listWithDuplicateTodos = Arrays.asList(todo1, todo1);
        assertThrows(DuplicateTodoException.class, () -> uniqueDailyList.setTodos(listWithDuplicateTodos));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDailyList.asUnmodifiableObservableList().remove(0));
    }
}
