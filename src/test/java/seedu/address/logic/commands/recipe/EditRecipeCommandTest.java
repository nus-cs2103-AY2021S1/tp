package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.Field;
import static seedu.address.logic.commands.CommandTestUtil.IDENTICAL_NAME_AND_INGREDIENT_SANDWICH_RECIPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_SANDWICH_DUPLICATE_INGREDIENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_SIMILAR_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_SIMILAR_SANDWICH_INGREDIENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_SIMILAR_SANDWICH_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_SIMILAR_SANDWICH_NAME_AND_INGREDIENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_SANDWICH_SIMILAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PASTA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.varyRecipeFieldsDescriptor;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;
import seedu.address.testutil.RecipeBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditRecipeCommandTest {

    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Recipe editedRecipe = new RecipeBuilder().buildOtherRecipe();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(editedRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editIngredientQuantitySpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withIngredient(VALID_INGREDIENT_SANDWICH, VALID_QUANTITY_PASTA).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.INGREDIENT_QUANTITY);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editIngredientNameSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withIngredient(VALID_INGREDIENT_PASTA, VALID_QUANTITY_SANDWICH).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.INGREDIENT_NAME);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editNameAndIngredientNameDuplicateSpecifiedUnfilteredList_failure() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        EditRecipeDescriptor descriptor = IDENTICAL_NAME_AND_INGREDIENT_SANDWICH_RECIPE;
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_editCaloriesSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withCalories(VALID_CALORIES_PASTA).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.CALORIES);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editImageSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withRecipeImage(VALID_RECIPE_IMAGE_PASTA).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.RECIPE_IMAGE);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editInstructionsSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withInstruction(VALID_INSTRUCTION_PASTA).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.INSTRUCTIONS);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editTagSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withTags(VALID_TAG_PASTA).build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.TAG);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_editRecipeNameSpecifiedUnfilteredList_success() {
        Index indexSandiwchRecipe = Index.fromZeroBased(0);
        Recipe sandiwchRecipe = model.getFilteredRecipeList().get(indexSandiwchRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(sandiwchRecipe);
        Recipe editedRecipe = recipeInList
                .withName("Pasta").build();

        EditRecipeDescriptor descriptor = varyRecipeFieldsDescriptor(Field.RECIPE_NAME);
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexSandiwchRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(sandiwchRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRecipe = Index.fromOneBased(model.getFilteredRecipeList().size());
        Recipe lastRecipe = model.getFilteredRecipeList().get(indexLastRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(lastRecipe);
        Recipe editedRecipe = recipeInList.withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                .build();

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexLastRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(lastRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_testWithSimilarRecipe() {
        Index indexPastaRecipe = Index.fromZeroBased(1);
        Recipe pastaRecipe = model.getFilteredRecipeList().get(indexPastaRecipe.getZeroBased());

        //Test replacing with similar recipe - similar recipe name and ingredient name -> success
        RecipeBuilder recipeInList = new RecipeBuilder();
        Recipe editedRecipe = recipeInList
                .withName("Sandwich similar")
                .withIngredient(VALID_INGREDIENT_SANDWICH_SIMILAR, VALID_QUANTITY_SANDWICH)
                .withCalories(VALID_CALORIES_SANDWICH)
                .withRecipeImage(VALID_RECIPE_IMAGE_SANDWICH)
                .withInstruction(VALID_INSTRUCTION_SANDWICH)
                .withTags(VALID_TAG_SANDWICH).build();

        EditRecipeDescriptor descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH;
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(pastaRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);


        //Test change recipe name of similar recipe to that of original recipe -> success
        Recipe similarRecipe = model.getFilteredRecipeList().get(indexPastaRecipe.getZeroBased());
        editedRecipe = recipeInList
                .withName("Sandwich")
                .build();
        descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH_NAME;
        editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);
        expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(similarRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);


        //Test change ingredient name of similar recipe to that of original recipe -> duplicate error
        descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH_NAME_AND_INGREDIENT_NAME;
        editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);


        //Test change recipe name of similar recipe back to previous similar recipe name -> success
        similarRecipe = model.getFilteredRecipeList().get(indexPastaRecipe.getZeroBased());
        editedRecipe = recipeInList
                .withName("Sandwich similar")
                .build();
        descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH;
        editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);
        expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(similarRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);


        //Test change ingredient name of similar recipe to that of original recipe -> success
        similarRecipe = model.getFilteredRecipeList().get(indexPastaRecipe.getZeroBased());
        editedRecipe = recipeInList
                .withIngredient(VALID_INGREDIENT_SANDWICH, VALID_QUANTITY_SANDWICH)
                .build();
        descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH_INGREDIENT_NAME;
        editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);
        expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(similarRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);


        //Test change recipe name of similar recipe to that of original recipe -> duplicate error
        descriptor = VALID_DESCRIPTOR_SIMILAR_SANDWICH_NAME_AND_INGREDIENT_NAME;
        editRecipeCommand = new EditRecipeCommand(indexPastaRecipe, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);

    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, new EditRecipeDescriptor());

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeInFilteredList = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        Recipe editedRecipe = new RecipeBuilder(recipeInFilteredList).withName(VALID_NAME_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build());

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecipeUnfilteredList_failure() {
        Recipe firstRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(firstRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_SECOND_RECIPE, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipeFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        // edit recipe in filtered list into a duplicate in recipe collection
        Recipe recipeInList = model.getWishfulShrinking().getRecipeList().get(INDEX_SECOND_RECIPE.getZeroBased());
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder(recipeInList).build());

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of recipe collection
     */
    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of recipe collection list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getRecipeList().size());

        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build());

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validRecipeWithDuplicateIngredients_throwsCommandException() {
        EditRecipeDescriptor descriptor = VALID_DESCRIPTOR_SANDWICH_DUPLICATE_INGREDIENT;
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, descriptor);
        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_INGREDIENTS);
    }

    @Test
    public void equals() {
        final EditRecipeCommand standardCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, VALID_DESCRIPTOR_NOODLE);

        // same values -> returns true
        EditRecipeDescriptor copyDescriptor = new EditRecipeDescriptor(VALID_DESCRIPTOR_NOODLE);
        EditRecipeCommand commandWithSameValues = new EditRecipeCommand(INDEX_FIRST_RECIPE, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearRecipeCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_SECOND_RECIPE, VALID_DESCRIPTOR_NOODLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_FIRST_RECIPE, VALID_DESCRIPTOR_MARGARITAS)));
    }

}
