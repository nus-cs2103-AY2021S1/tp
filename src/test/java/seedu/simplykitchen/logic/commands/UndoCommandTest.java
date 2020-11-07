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
public class UndoCommandTest {
    private final Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstFood(model);
        deleteFirstFood(model);

        deleteFirstFood(expectedModel);
        deleteFirstFood(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }
}
