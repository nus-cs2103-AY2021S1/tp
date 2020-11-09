package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPlanus.getTypicalPlanus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CalendarCommandTest {

    @Test
    public void execute_emptyPlanus_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new CalendarCommand(), model, CalendarCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPlanus_success() {
        Model model = new ModelManager(getTypicalPlanus(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPlanus(), new UserPrefs());

        assertCommandSuccess(new CalendarCommand(), model, CalendarCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
