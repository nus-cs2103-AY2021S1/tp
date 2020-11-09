package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.CHORES;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandTest {
    @Test
    public void constructor_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTodoCommand(null));
    }

    @Test
    public void execute_todoAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTodoAdded modelStub = new ModelStubAcceptingTodoAdded();
        Todo validTodo = new TodoBuilder().build();

        CommandResult commandResult = new AddTodoCommand(validTodo).execute(modelStub);

        assertEquals(String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo.getDescriptionDateTime()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTodo), modelStub.todosAdded);
    }

    @Test
    public void execute_duplicateTodo_throwsCommandException() {
        Todo validTodo = new TodoBuilder().build();
        AddTodoCommand addTodoCommand = new AddTodoCommand(validTodo);
        ModelStub modelStub = new ModelStubWithTodo(validTodo);

        assertThrows(CommandException.class,
                AddTodoCommand.MESSAGE_DUPLICATE_TODO, () -> addTodoCommand.execute(modelStub));
    }

    @Test
    public void execute_todoWithRecurrenceAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTodoAdded modelStub = new ModelStubAcceptingTodoAdded();
        Todo validTodo = new TodoBuilder(CHORES).build();

        CommandResult commandResult = new AddTodoCommand(validTodo).execute(modelStub);

        assertEquals(String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo.getDescriptionDateTime()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTodo), modelStub.todosAdded);
    }

    @Test
    public void equals() {
        Todo homework = new TodoBuilder().withDescription("homework").build();
        Todo assignment = new TodoBuilder().withDescription("assignment").build();
        AddTodoCommand addHomeworkCommand = new AddTodoCommand(homework);
        AddTodoCommand addAssignmentCommand = new AddTodoCommand(assignment);

        // same object -> returns true
        assertTrue(addHomeworkCommand.equals(addHomeworkCommand));

        // same values -> returns true
        AddTodoCommand addHomeworkCommandCopy = new AddTodoCommand(homework);
        assertTrue(addHomeworkCommand.equals(addHomeworkCommandCopy));

        // different types -> returns false
        assertFalse(addHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(addHomeworkCommand.equals(null));

        // different person -> returns false
        assertFalse(addHomeworkCommand.equals(addAssignmentCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        // Needs to be null to update "Due Soon" section in GUI after adding a task.
        @Override
        public ObservableList<Task> getDueSoonTaskList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<? super Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markAsDone(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedTaskList(Comparator<Task> taskComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean filteredTaskListIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean filteredAddressBookIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean taskListIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean addressBookIsEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single to-do.
     */
    private class ModelStubWithTodo extends ModelStub {
        private final Todo todo;

        ModelStubWithTodo(Todo todo) {
            requireNonNull(todo);
            this.todo = todo;
        }

        @Override
        public boolean hasTask(Task todo) {
            requireNonNull(todo);
            return this.todo.isSameTask(todo);
        }
    }

    /**
     * A Model stub that always accept the to-do being added.
     */
    private class ModelStubAcceptingTodoAdded extends ModelStub {
        final ArrayList<Todo> todosAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task todo) {
            requireNonNull(todo);
            return todosAdded.stream().anyMatch(todo::isSameTask);
        }

        @Override
        public void addTodo(Todo todo) {
            requireNonNull(todo);
            todosAdded.add(todo);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

    }

}
