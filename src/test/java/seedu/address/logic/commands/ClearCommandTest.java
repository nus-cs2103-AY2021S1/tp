package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WishfulShrinking;

public class ClearCommandTest {

    @Test
    public void execute_emptyWishfulShrinking_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWishfulShrinking_success() {
        Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel.setWishfulShrinking(new WishfulShrinking());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
