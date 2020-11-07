package seedu.simplykitchen.logic.commands;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.deleteFirstFood;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;

//Solution below adapted from https://github.com/se-edu/addressbook-level4.
public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstFood(model);
        deleteFirstFood(model);
        model.undoFoodInventory();
        model.undoFoodInventory();

        deleteFirstFood(expectedModel);
        deleteFirstFood(expectedModel);
        expectedModel.undoFoodInventory();
        expectedModel.undoFoodInventory();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}
