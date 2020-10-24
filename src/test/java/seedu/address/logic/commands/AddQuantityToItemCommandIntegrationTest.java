package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DECREMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECREASED_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCREASED_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_ORIGINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DECREMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_INCREMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInventoryCommandFailure;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddQuantityToItemCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private Item apple;

    /**
     * Sets up model to contain an item list with only the item apple in it.
     */
    @BeforeEach
    public void setUp() {
        // this is necessary for reasons
        Item.setIdCounter(0);
        model = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        apple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_ORIGINAL).build();
        model.addItem(apple);
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new AddQuantityToItemCommand(null, 0));
    }

    /**
     * Tests for successful edit of an item's quantity found in the item list.
     */
    @Test
    public void execute_addQuantity_success() {
        AddQuantityToItemCommand aic = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, VALID_QUANTITY_INCREMENT);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_INCREASED_QUANTITY).build();
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);
        expectedModel.addItem(editedApple);

        assertCommandSuccess(aic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for successful decrease of an item's quantity found in the item list.
     */
    @Test
    public void execute_decreaseQuantity_success() {
        AddQuantityToItemCommand aic = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, VALID_QUANTITY_DECREMENT);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_DECREASED_QUANTITY).build();
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);

        expectedModel.addItem(editedApple);

        assertCommandSuccess(aic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for failed decrease of an item's quantity beyond 0.
     */
    @Test
    public void execute_decreaseQuantityToNegative_throwsException() {
        AddQuantityToItemCommand aic = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, INVALID_QUANTITY_DECREMENT);
        String expectedMessage = AddQuantityToItemCommand.MESSAGE_NEGATIVE_QUANTITY;

        assertInventoryCommandFailure(aic, model, expectedMessage);
    }
}
