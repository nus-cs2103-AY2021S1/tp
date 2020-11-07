package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.Messages;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;
import seedu.simplykitchen.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Food editedFood = new FoodBuilder().build();
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(editedFood).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);
        expectedModel.commitFoodInventory();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Food editedFood = foodInList.withDescription(VALID_DESCRIPTION_BREAD).withPriority(VALID_PRIORITY_BREAD)
                .withTags(VALID_TAG_WHOLEMEAL).build();

        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BREAD)
                .withPriority(VALID_PRIORITY_BREAD).withTags(VALID_TAG_WHOLEMEAL).build();
        EditCommand editCommand = new EditCommand(indexLastFood, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(lastFood, editedFood);
        expectedModel.commitFoodInventory();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, new EditCommand.EditFoodDescriptor());
        Food editedFood = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.commitFoodInventory();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        Food foodInFilteredList = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        Food editedFood = new FoodBuilder(foodInFilteredList).withDescription(VALID_DESCRIPTION_BREAD).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD,
                new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAD).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(
                new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);
        expectedModel.commitFoodInventory();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFoodUnfilteredList_failure() {
        Food firstFood = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(firstFood).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FOOD, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_duplicateFoodFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        // edit food in filtered list into a duplicate in food inventory
        Food foodInList = model.getFoodInventory().getFoods().get(INDEX_SECOND_FOOD.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD,
                new EditFoodDescriptorBuilder(foodInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_invalidFoodIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BREAD).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of SimplyKitchen inventory.
     */
    @Test
    public void execute_invalidFoodIndexFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);
        Index outOfBoundIndex = INDEX_SECOND_FOOD;
        // ensures that outOfBoundIndex is still in bounds of food inventory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodInventory().getFoods().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAD).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Food editedFood = new FoodBuilder().build();
        Food foodToEdit = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(editedFood).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, descriptor);
        Model expectedModel = new ModelManager(new FoodInventory(model.getFoodInventory()), new UserPrefs());
        expectedModel.setFood(foodToEdit, editedFood);
        expectedModel.commitFoodInventory();

        // edit -> first food edited
        editCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first food edited again
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BREAD).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        // execution failed -> food inventory state not added into model
        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);

        // single food inventory state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Food} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited food in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the food object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameFoodEdited() throws Exception {
        Food editedFood = new FoodBuilder().build();
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(editedFood).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, descriptor);
        Model expectedModel = new ModelManager(new FoodInventory(model.getFoodInventory()), new UserPrefs());

        showFoodAtIndex(model, INDEX_SECOND_FOOD);
        Food foodToEdit = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        expectedModel.setFood(foodToEdit, editedFood);
        expectedModel.commitFoodInventory();

        // edit -> edits second food in unfiltered food list / first food in filtered food list
        editCommand.execute(model);

        // undo -> reverts food inventory back to previous state and filtered food list to show all foods
        expectedModel.undoFoodInventory();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased()), foodToEdit);
        // redo -> edits same second food in unfiltered food list
        expectedModel.redoFoodInventory();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FOOD, DESC_APPLE_PIE);

        // same values -> returns true
        EditFoodDescriptor copyDescriptor = new EditFoodDescriptor(DESC_APPLE_PIE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FOOD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FOOD, DESC_APPLE_PIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FOOD, DESC_BREAD)));
    }

}
