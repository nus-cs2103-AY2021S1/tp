package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FOOD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FOOD_SUCCESS, foodToDelete);

        ModelManager expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());
        expectedModel.deleteFood(foodToDelete);
        expectedModel.commitFoodInventory();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FOOD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FOOD_SUCCESS, foodToDelete);

        Model expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());
        expectedModel.deleteFood(foodToDelete);
        expectedModel.commitFoodInventory();
        showNoFood(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        Index outOfBoundIndex = INDEX_SECOND_FOOD;
        // ensures that outOfBoundIndex is still in bounds of food inventory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodInventory().getFoods().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FOOD);
        Model expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());
        expectedModel.deleteFood(foodToDelete);
        expectedModel.commitFoodInventory();

        // delete -> first food deleted
        deleteCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first food deleted again
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        // execution failed -> food inventory state not added into model
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);

        // single food inventory state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Food} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted food in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the food object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameFoodDeleted() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FOOD);
        Model expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());

        showFoodAtIndex(model, INDEX_SECOND_FOOD);
        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        expectedModel.deleteFood(foodToDelete);
        expectedModel.commitFoodInventory();

        // delete -> deletes second food in unfiltered food list / first food in filtered food list
        deleteCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(foodToDelete, model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased()));
        // redo -> deletes same second food in unfiltered food list
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_FOOD);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_FOOD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_FOOD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different food -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFood(Model model) {
        model.updateFilteredFoodList(p -> false);

        assertTrue(model.getFilteredFoodList().isEmpty());
    }
}
