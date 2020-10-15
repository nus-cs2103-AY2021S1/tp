package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.testutil.TypicalItems.APPLE;

public class EditItemCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private Item apple;

    /**
     * Sets up model to contain an itemlist with only the item apple in it.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        apple = new ItemBuilder(APPLE).build();
        model.addItem(apple);
    }

    /**
     * Tests for successful edit of an item's quantity found in the item list.
     */
    @Test
    public void execute_singleField_success() {
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), descriptor);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA).build();
        expectedModel.addItem(editedApple);
        assertCommandSuccess(eic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for successful edit of an item's quantity and description found in the item list.
     */
    @Test
    public void execute_multipleFields_success() {
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        descriptor.setDescription(VALID_ITEM_DESCRIPTION_BANANA);
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), descriptor);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE)
                .withQuantity(VALID_ITEM_QUANTITY_BANANA)
                .withDescription(VALID_ITEM_DESCRIPTION_BANANA)
                .build();
        expectedModel.addItem(editedApple);

        assertCommandSuccess(eic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for failure when no field is specified.
     */
    @Test
    public void execute_noFieldSpecified_failure() {
        // edit command has empty descriptor with no fields specified
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), new EditItemCommand.EditItemDescriptor());

        assertInventoryCommandFailure(eic, model, EditItemCommand.MESSAGE_NOT_EDITED);
    }

    /**
     * Tests for failure when index is out of range of the number of items.
     */
    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        EditItemCommand eic = new EditItemCommand(outOfBoundIndex, descriptor);
        assertInventoryCommandFailure(eic, model, EditItemCommand.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    /**
     * Tests for failure when the edited item is a duplicate of an existing item.
     */
    @Test
    public void execute_duplicateItem_failure() {
        Item banana = new ItemBuilder(APPLE).withName("Banana").build();
        model.addItem(banana); // model now has apple and banana
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName(banana.getName());
        // This command tries to modify apple to be named banana, which is already in the model
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), descriptor);
        assertInventoryCommandFailure(eic, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }
}