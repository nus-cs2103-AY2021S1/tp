package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newVendor_success() {
        //        Vendor validVendor = new VendorBuilder().build();
        ArrayList<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(new MenuManager());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                menuManagers,
                new OrderManager());
        Food food = new Food("Prata", 1.00, new HashSet<>());
        OrderItem item = new OrderItem(food, 1);
        expectedModel.addOrderItem(item);
        assertCommandSuccess(new AddCommand(item), model,
                String.format(AddCommand.MESSAGE_SUCCESS, item),
                expectedModel);
    }

    @Test
    public void execute_duplicateVendor_throwsCommandException() {
        ArrayList<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(new MenuManager());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                menuManagers,
                new OrderManager());
        Food food = new Food("Prata", 1.00, new HashSet<>());
        OrderItem item = new OrderItem(food, 1);
        expectedModel.addOrderItem(item);

        assertCommandFailure(new AddCommand(item), expectedModel,
                AddCommand.MESSAGE_DUPLICATE_VENDOR);
    }
}
