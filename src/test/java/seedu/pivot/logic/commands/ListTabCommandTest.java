package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;


public class ListTabCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        StateManager.setState(INDEX_FIRST_PERSON);
        StateManager.resetTabState();
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListTabCommand(null));
    }

    @Test
    public void execute_success() throws CommandException {
        ListCommand command = new ListTabCommand("TEST");
        CommandResult result = command.execute(model);
        assertEquals(result.getFeedbackToUser(), String.format(ListTabCommand.LIST_TAB_SUCCESS, "TEST"));
        assertEquals(StateManager.getTabState(), "TEST");
    }

    @Test
    public void equals() {
        ListCommand command = new ListTabCommand("TEST");
        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        ListCommand commandCopy = new ListTabCommand("TEST");
        assertTrue(commandCopy.equals(command));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different person -> returns false
        assertFalse(command.equals(new ListTabCommand("ALTERNATE")));
    }
}
