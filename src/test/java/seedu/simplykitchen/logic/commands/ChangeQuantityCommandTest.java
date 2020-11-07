package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.ChangeQuantityCommand.MESSAGE_QUANTITY_ERROR;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;
import seedu.simplykitchen.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code ChangeQuantityCommand}.
 */
public class ChangeQuantityCommandTest {

    private final Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    /**
     * Updates the quantity of the first food item by a certain amount.
     */
    private Food updateFoodQuantity(double amount) {
        Food food = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());

        Quantity oldQuantity = food.getQuantity();
        double newQuantityValue = oldQuantity.updateQuantityValue(amount);

        Description description = food.getDescription();
        Priority priority = food.getPriority();
        ExpiryDate expiryDate = food.getExpiryDate();
        Quantity newQuantity = oldQuantity.updateQuantity(newQuantityValue);
        Set<Tag> tags = food.getTags();

        return new Food(description, priority, expiryDate, newQuantity, tags);
    }

    @Test
    public void execute_unfilteredList_success() {
        double amount = +1.0;
        Food newFood = updateFoodQuantity(amount);

        String expectedMessage = String.format(ChangeQuantityCommand.MESSAGE_SUCCESS, newFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), newFood);
        expectedModel.commitFoodInventory();

        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        assertCommandSuccess(changeQuantityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        double amount = +1.0;
        Food newFood = updateFoodQuantity(amount);

        String expectedMessage = String.format(ChangeQuantityCommand.MESSAGE_SUCCESS, newFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), newFood);
        expectedModel.commitFoodInventory();

        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        assertCommandSuccess(changeQuantityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidFoodIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);

        double amount = +1.0;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(outOfBoundIndex, amount);

        assertCommandFailure(changeQuantityCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidFoodIndexFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);
        Index outOfBoundIndex = INDEX_SECOND_FOOD;
        // ensures that outOfBoundIndex is still in bounds of food inventory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodInventory().getFoods().size());

        double amount = +1.0;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(outOfBoundIndex, amount);

        assertCommandFailure(changeQuantityCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_zeroQuantity_failure() {
        double amount = -1.0;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        assertCommandFailure(changeQuantityCommand, model, MESSAGE_QUANTITY_ERROR);
    }

    @Test
    public void execute_negativeQuantity_failure() {
        double amount = -100.0;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        assertCommandFailure(changeQuantityCommand, model, MESSAGE_QUANTITY_ERROR);
    }

    @Test
    public void execute_aboveMaxQuantity_failure() {
        double amount = +99996;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_SECOND_FOOD, amount);

        assertCommandFailure(changeQuantityCommand, model, MESSAGE_QUANTITY_ERROR);
    }

    @Test
    public void execute_maxQuantity_success() {
        double amount = +99999;
        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);
        Food newFood = updateFoodQuantity(amount);

        String expectedMessage = String.format(ChangeQuantityCommand.MESSAGE_SUCCESS, newFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), newFood);
        expectedModel.commitFoodInventory();

        assertCommandSuccess(changeQuantityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        double amount = +1.0;
        Food newFood = updateFoodQuantity(amount);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), newFood);
        expectedModel.commitFoodInventory();

        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        // changeQty -> quantity of first food item changed
        changeQuantityCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first food edited again
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_validIndexFilteredList_success() throws Exception {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        double amount = +1.0;
        Food newFood = updateFoodQuantity(amount);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), newFood);
        expectedModel.commitFoodInventory();

        ChangeQuantityCommand changeQuantityCommand = new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount);

        // changeQty -> quantity of first food item changed
        changeQuantityCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first food edited again
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
