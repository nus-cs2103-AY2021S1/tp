package seedu.bagel.logic.commands;

import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import org.junit.jupiter.api.Test;

import seedu.bagel.model.Bagel;
import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyBagel_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, true);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyBagel_success() {
        Model model = new ModelManager(getTypicalBagel(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalBagel(), new UserPrefs());
        expectedModel.setBagel(new Bagel());
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, true);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }
}
