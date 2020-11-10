package seedu.momentum.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.momentum.testutil.Assert.assertThrows;
import static seedu.momentum.testutil.TypicalProjects.getTypicalProjectBook;

import org.junit.jupiter.api.Test;

import seedu.momentum.logic.parser.ShowComponentCommandParser;
import seedu.momentum.model.Model;
import seedu.momentum.model.ModelManager;
import seedu.momentum.model.UserPrefs;

public class ShowComponentCommandTest {
    private static final ShowComponentCommand showComponentCommand =
            new ShowComponentCommand(ShowComponentCommandParser.ComponentType.TAGS);

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowComponentCommand(null));
    }

    @Test
    public void execute_showComponent_allFieldsSpecifiedSuccess() {
        Model model = new ModelManager(getTypicalProjectBook(), new UserPrefs());

        String successMessage = String.format(ShowComponentCommand.MESSAGE_SUCCESS,
                ShowComponentCommandParser.ComponentType.TAGS,
                ShowComponentCommand.REMOVED);
        Model expectedModel = new ModelManager(getTypicalProjectBook(), new UserPrefs());
        expectedModel.showOrHideTags();
        expectedModel.commitToHistory();
        assertCommandSuccess(showComponentCommand, model, successMessage, expectedModel);

        successMessage = String.format(ShowComponentCommand.MESSAGE_SUCCESS,
                ShowComponentCommandParser.ComponentType.TAGS,
                ShowComponentCommand.SHOWN);
        expectedModel.showOrHideTags();
        expectedModel.commitToHistory();
        assertCommandSuccess(showComponentCommand, model, successMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(showComponentCommand.equals(showComponentCommand));

        // same values -> returns true
        assertTrue(showComponentCommand.equals(
                new ShowComponentCommand(ShowComponentCommandParser.ComponentType.TAGS)));

        // different types -> returns false
        assertFalse(showComponentCommand.equals(1));

        // null -> returns false
        assertFalse(showComponentCommand.equals(null));
    }
}
