package seedu.jarvis.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_ONE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.add.AddTaskCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.testutil.DeadlineBuilder;
import seedu.jarvis.testutil.EventBuilder;
import seedu.jarvis.testutil.ModelStub;
import seedu.jarvis.testutil.TodoBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        Todo todo = null;
        Event event = null;
        Deadline deadline = null;
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(todo));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(event));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(deadline));
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        AddTaskCommand addTaskCommandTodo = new AddTaskCommand(TEST_TODO_ONE);
        assertThrows(NullPointerException.class, () -> addTaskCommandTodo.execute(emptyModel));
    }

    @Test
    public void execute_todoAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Todo todo = new TodoBuilder().withDescription("UniqueTodo").build();

        CommandResult commandResult = new AddTaskCommand(todo).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS_TODO, todo),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(todo), modelStub.tasksAdded);
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Event event = new EventBuilder().withDescription("UniqueEvent").build();

        CommandResult commandResult = new AddTaskCommand(event).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS_EVENT, event),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(event), modelStub.tasksAdded);
    }

    @Test
    public void execute_deadlineAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Deadline deadline = new DeadlineBuilder().withDescription("UniqueDeadline").build();

        CommandResult commandResult = new AddTaskCommand(deadline).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS_DEADLINE, deadline),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(deadline), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Todo validTodo = new TodoBuilder().build();
        AddTaskCommand addCommandTodo = new AddTaskCommand(validTodo);
        ModelStub modelStubTodo = new ModelStubWithTask(validTodo);
        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addCommandTodo.execute(modelStubTodo));

        Event validEvent = new EventBuilder().build();
        AddTaskCommand addCommandEvent = new AddTaskCommand(validEvent);
        ModelStub modelStubEvent = new ModelStubWithTask(validEvent);
        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addCommandEvent.execute(modelStubEvent));

        Deadline validDeadline = new DeadlineBuilder().build();
        AddTaskCommand addCommandDeadline = new AddTaskCommand(validDeadline);
        ModelStub modelStubDeadline = new ModelStubWithTask(validDeadline);
        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addCommandDeadline.execute(modelStubDeadline));

    }

    @Test
    public void execute_eventAcceptedByModel_commandTargetFeatureAccurate() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Event event = new EventBuilder().withDescription("UniqueEvent").build();

        CommandResult commandResult = new AddTaskCommand(event).execute(modelStub);

        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Tasks, actualTargetTab);
    }

    @Test
    public void execute_deadlineAcceptedByModel_commandTargetFeatureAccurate() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Deadline deadline = new DeadlineBuilder().withDescription("UniqueDeadline").build();

        CommandResult commandResult = new AddTaskCommand(deadline).execute(modelStub);

        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Tasks, actualTargetTab);
    }

    @Test
    public void execute_todoAcceptedByModel_commandTargetFeatureAccurate() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Todo todo = new TodoBuilder().withDescription("UniqueTodo").build();

        CommandResult commandResult = new AddTaskCommand(todo).execute(modelStub);

        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Tasks, actualTargetTab);
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.equals(task);
        }

        @Override
        public boolean hasTodo(Todo todo) {
            requireNonNull(todo);
            return this.task.equals(todo);
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.task.equals(event);
        }

        @Override
        public boolean hasDeadline(Deadline deadline) {
            requireNonNull(deadline);
            return this.task.equals(deadline);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::equals);
        }

        @Override
        public boolean hasTodo(Todo todo) {
            requireNonNull(todo);
            return tasksAdded.stream().anyMatch(todo::equals);
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return tasksAdded.stream().anyMatch(event::equals);
        }

        @Override
        public boolean hasDeadline(Deadline deadline) {
            requireNonNull(deadline);
            return tasksAdded.stream().anyMatch(deadline::equals);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void addTodo(Todo todo) {
            requireNonNull(todo);
            tasksAdded.add(todo);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            tasksAdded.add(event);
        }

        @Override
        public void addDeadline(Deadline deadline) {
            requireNonNull(deadline);
            tasksAdded.add(deadline);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

    }
}
