package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInventoryCommandFailure;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemBuilder;

public class EditItemCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private Item apple;

    /**
     * Sets up model to contain an item list with only the item apple in it.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        apple = new ItemBuilder(APPLE).build();
        model.addItem(apple);
    }

    /**
     * Tests for successful edit of an item's quantity and description found in the item list.
     */
    @Test
    public void execute_multipleFields_success() {
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        descriptor.setDescription(VALID_ITEM_DESCRIPTION_BANANA);
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE)
                .withQuantity(VALID_ITEM_QUANTITY_BANANA)
                .withDescription(VALID_ITEM_DESCRIPTION_BANANA)
                .build();
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);
        expectedModel.addItem(editedApple);

        assertCommandSuccess(eic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for successful edit of an item's name found in the item list and correct update in recipe.
     */
    @Test
    public void execute_withRecipeList_success() {
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName(VALID_ITEM_NAME_BANANA);
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        model.addRecipe(BANANA_PIE);

        Item editedApple = new ItemBuilder(APPLE).withName(VALID_ITEM_NAME_BANANA).build();
        expectedModel.addItem(editedApple);
        Recipe updatedRecipe = BANANA_PIE.setProductName(VALID_ITEM_NAME_BANANA);
        expectedModel.addRecipe(updatedRecipe);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);

        assertCommandSuccess(eic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for failure when no field is specified.
     */
    @Test
    public void execute_noFieldSpecified_failure() {
        // edit command has empty descriptor with no fields specified
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, new EditItemCommand.EditItemDescriptor());

        assertInventoryCommandFailure(eic, model, EditItemCommand.MESSAGE_NOT_EDITED);
    }

    /**
     * Tests for failure when the edited item is a duplicate of an existing item.
     */
    @Test
    public void execute_duplicateItem_failure() {
        Item banana = new ItemBuilder(APPLE).withName(VALID_ITEM_NAME_BANANA).build();
        model.addItem(banana); // model now has apple and banana
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName(banana.getName());
        // This command tries to modify apple to be named banana, which is already in the model
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        assertInventoryCommandFailure(eic, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }
}
