package seedu.simplykitchen.logic.commands;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFoodInventory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitFoodInventory();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFoodInventory_success() {
        Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFoodInventory(), new UserPrefs());
        expectedModel.setFoodInventory(new FoodInventory());
        expectedModel.commitFoodInventory();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
