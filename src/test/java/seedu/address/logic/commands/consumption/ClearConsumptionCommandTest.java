package seedu.address.logic.commands.consumption;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearConsumptionCommandTest {

    @Test
    public void execute_emptyWishfulShrinking_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearConsumptionCommand(), model,
                ClearConsumptionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWishfulShrinking_success() {
        Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel.clearConsumption();

        assertCommandSuccess(new ClearConsumptionCommand(), model,
                ClearConsumptionCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
