package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.due.DueBeforeCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DueBeforePredicate;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class DueBeforeCommandTest {
    private Todo todo = new TodoBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
    @Test
    public void execute_rightDateAndTimeInputs_todoFound() {
        model.addTodo(todo);
        DueBeforePredicate predicate = preparePredicate("date/13-12-2020 time/2359");
        DueBeforeCommand command = new DueBeforeCommand(predicate);
        String expectedMessage = String.format(DueBeforeCommand.MESSAGE_SUCCESS, predicate.getDateTime());
        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
    }
    @Test
    public void equals() {
        DueBeforePredicate firstPredicate = preparePredicate("date/10-10-2020 time/0000");
        DueBeforePredicate secondPredicate = preparePredicate("date/30-12-2021 time/2100");

        DueBeforeCommand firstCommand = new DueBeforeCommand(firstPredicate);
        DueBeforeCommand secondCommand = new DueBeforeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DueBeforeCommand firstCommandCopy = new DueBeforeCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code DueBeforePredicate}.
     */
    private DueBeforePredicate preparePredicate(String userInput) {
        String deadline = userInput.replace("date/", "");
        deadline = deadline.replace("time/", "");
        return new DueBeforePredicate(deadline);
    }
}
