package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INGREDIENT_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INGREDIENT;
import static seedu.address.testutil.TypicalIngredients.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Ingredient;
import seedu.address.testutil.EditIngredientDescriptorBuilder;
import seedu.address.testutil.IngredientBuilder;

public class EditIngredientCommandTest {
    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Ingredient editedIngredient = new IngredientBuilder().build();
        EditIngredientCommand.EditIngredientDescriptor descriptor =
                new EditIngredientDescriptorBuilder(editedIngredient).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                descriptor);

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS,
                editedIngredient);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setIngredient(model.getFilteredIngredientList().get(0), editedIngredient);

        assertCommandSuccess(editIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastIngredient = Index.fromOneBased(model.getFilteredIngredientList().size());
        Ingredient lastIngredient = model.getFilteredIngredientList().get(indexLastIngredient.getZeroBased());

        IngredientBuilder ingredientInList = new IngredientBuilder(lastIngredient);
        Ingredient editedIngredient = ingredientInList.withValue(VALID_INGREDIENT_MARGARITAS)
                .build();

        EditIngredientCommand.EditIngredientDescriptor descriptor =
                new EditIngredientDescriptorBuilder().withIngredient(
                        new Ingredient(VALID_INGREDIENT_MARGARITAS)).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(indexLastIngredient, descriptor);

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS,
                editedIngredient);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setIngredient(lastIngredient, editedIngredient);

        assertCommandSuccess(editIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                new EditIngredientCommand.EditIngredientDescriptor());
        Ingredient editedIngredient = model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_NOT_EDITED,
                editedIngredient);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());

        assertCommandSuccess(editIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Ingredient IngredientInFilteredList =
                model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        Ingredient editedIngredient =
                new IngredientBuilder(IngredientInFilteredList).withValue(VALID_NAME_MARGARITAS).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                new EditIngredientDescriptorBuilder().withIngredient(new Ingredient(VALID_NAME_MARGARITAS))
                        .build());

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS,
                editedIngredient);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setIngredient(model.getFilteredIngredientList().get(0), editedIngredient);
        expectedModel.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        assertCommandSuccess(editIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateIngredientUnfilteredList_failure() {
        Ingredient firstIngredient = model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        EditIngredientCommand.EditIngredientDescriptor descriptor =
                new EditIngredientDescriptorBuilder(firstIngredient).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_SECOND_INGREDIENT,
                descriptor);

        assertCommandFailure(editIngredientCommand, model, EditIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }

    @Test
    public void execute_duplicateIngredientFilteredList_failure() {
        showIngredientAtIndex(model, INDEX_FIRST_INGREDIENT);

        // edit Ingredient in filtered list into a duplicate in Ingredient collection
        Ingredient ingredientInList =
                model.getWishfulShrinking().getIngredientList().get(INDEX_SECOND_INGREDIENT.getZeroBased());
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                new EditIngredientDescriptorBuilder(ingredientInList).build());

        assertCommandFailure(editIngredientCommand, model, EditIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }

    @Test
    public void execute_invalidIngredientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIngredientList().size() + 1);
        EditIngredientCommand.EditIngredientDescriptor descriptor =
                new EditIngredientDescriptorBuilder().withIngredient(new Ingredient(VALID_NAME_MARGARITAS))
                        .build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Ingredient collection
     */
    @Test
    public void execute_invalidIngredientIndexFilteredList_failure() {
        showIngredientAtIndex(model, INDEX_FIRST_INGREDIENT);
        Index outOfBoundIndex = INDEX_SECOND_INGREDIENT;
        // ensures that outOfBoundIndex is still in bounds of Ingredient collection list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getIngredientList().size());

        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(outOfBoundIndex,
                new EditIngredientDescriptorBuilder().withIngredient(new Ingredient(VALID_NAME_MARGARITAS)).build());

        assertCommandFailure(editIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditIngredientCommand standardCommand = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                DESC_INGREDIENT_NOODLE);

        // same values -> returns true
        EditIngredientCommand.EditIngredientDescriptor copyDescriptor =
                new EditIngredientCommand.EditIngredientDescriptor(DESC_INGREDIENT_NOODLE);
        EditIngredientCommand commandWithSameValues = new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearIngredientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditIngredientCommand(INDEX_SECOND_INGREDIENT,
                DESC_INGREDIENT_NOODLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditIngredientCommand(INDEX_FIRST_INGREDIENT,
                DESC_INGREDIENT_MARGARITAS)));
    }
}
