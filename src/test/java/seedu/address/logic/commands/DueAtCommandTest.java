package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.due.DueAtCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DueAtPredicate;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class DueAtCommandTest {
    private Todo todo = new TodoBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_rightDateAndTimeInputs_todoFound() {
        model.addTodo(todo);

        DueAtPredicate predicate = preparePredicate("date/13-12-2020 time/2359");
        DueAtCommand command = new DueAtCommand(predicate);

        String expectedMessage = String.format(DueAtCommand.MESSAGE_SUCCESS, predicate.getDateTime());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void equals() {
        DueAtPredicate firstPredicate = preparePredicate("date/10-10-2020 time/0000");
        DueAtPredicate secondPredicate = preparePredicate("date/30-12-2021 time/2100");

        DueAtCommand firstCommand = new DueAtCommand(firstPredicate);
        DueAtCommand secondCommand = new DueAtCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DueAtCommand firstCommandCopy = new DueAtCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code DueAtPredicate}.
     */
    private DueAtPredicate preparePredicate(String userInput) {
        String deadline = userInput.replace("date/", "");
        deadline = deadline.replace("time/", "");
        return new DueAtPredicate(deadline);
    }
}
