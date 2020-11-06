package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.VersionedListException;
import seedu.address.testutil.todolist.TaskBuilder;
import seedu.address.testutil.todolist.TodoListBuilder;

public class VersionedTodoListTest {
    private static final TodoList VALID_TODO_LIST = new TodoListBuilder().withTask(new TaskBuilder().build())
            .build();
    private final VersionedTodoList versionedTodoList = new VersionedTodoList(VALID_TODO_LIST);
    @Test
    public void constructor() {
        assertEquals(VALID_TODO_LIST, versionedTodoList);
    }
    @Test
    public void execute_commit_success() {
        //Initiate empty VersionedTodoList
        VersionedTodoList initialVersionedTodoList = new VersionedTodoList();
        initialVersionedTodoList.commit(VALID_TODO_LIST);
        assertEquals(initialVersionedTodoList, VALID_TODO_LIST);
    }

    @Test
    public void undoData_withNoHistory_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedTodoList.undo());
    }

    @Test
    public void execute_undoWithHistory_success() {
        //Initiate empty VersionedTodoList
        VersionedTodoList initialVersionedTodoList = new VersionedTodoList();
        initialVersionedTodoList.commit(VALID_TODO_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedTodoList, VALID_TODO_LIST);
        try {
            initialVersionedTodoList.undo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedTodoList, new TodoList());
    }

    @Test
    public void redoData_withNoFuture_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedTodoList.redo());
    }

    @Test
    public void execute_redoWithFuture_success() {
        //Initiate empty VersionedTodoList
        VersionedTodoList initialVersionedTodoList = new VersionedTodoList();
        initialVersionedTodoList.commit(VALID_TODO_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedTodoList, VALID_TODO_LIST);
        try {
            initialVersionedTodoList.undo();
            //make sure that undo was successful
            assertEquals(initialVersionedTodoList, new TodoList());
            initialVersionedTodoList.redo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedTodoList, VALID_TODO_LIST);
    }
}
