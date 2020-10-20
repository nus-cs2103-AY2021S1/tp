package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.TypicalFoods;

public class AddCommandTest {

    @Test
    public void constructor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_validIndex_success() {
        List<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(TypicalFoods.getTypicalMenuManager());
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());

        Index index = Index.fromOneBased(1);
        AddCommand addCommand = new AddCommand(index);

        ObservableList<Food> menu = model.getFilteredFoodList(0);
        Food firstItem = menu.get(0);
        OrderItem addedItem = new OrderItem(firstItem, 1);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());
        expectedModel.addOrderItem(addedItem);
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_FIRST_SUCCESS, addedItem);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validQuantity_success() {
        List<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(TypicalFoods.getTypicalMenuManager());
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());

        Index first = Index.fromOneBased(2);
        int quantity = 3;
        AddCommand addCommand = new AddCommand(first, quantity);

        ObservableList<Food> menu = model.getFilteredFoodList(0);
        Food secondItem = menu.get(1);
        OrderItem addedItem = new OrderItem(secondItem, 3);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());
        expectedModel.addOrderItem(addedItem);
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_FIRST_SUCCESS, addedItem);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        List<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(TypicalFoods.getTypicalMenuManager());
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());

        ObservableList<Food> menu = model.getFilteredFoodList(0);
        Index outOfBoundIndex = Index.fromOneBased(menu.size() + 1);
        AddCommand addCommand = new AddCommand(outOfBoundIndex);

        assertCommandFailure(addCommand, model, ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        AddCommand addCommand1 = new AddCommand(Index.fromOneBased(1));
        AddCommand addCommand2 = new AddCommand(Index.fromOneBased(3));

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AddCommand addCommandCopy = new AddCommand(Index.fromOneBased(1));
        assertTrue(addCommandCopy.equals(addCommand1));

        // different types -> returns false
        assertFalse(addCommand1.equals(1));

        // null -> returns false
        assertFalse(addCommand1.equals(null));

        // different vendor -> returns false
        assertFalse(addCommand1.equals(addCommand2));
    }

}
