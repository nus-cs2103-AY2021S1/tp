package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.TypicalVendors;

public class ClearCommandTest {

    private void setOrderManager(Model model) {
        OrderItem item1 = new OrderItem(new MenuItem("Prata", 1, new HashSet<>(), ""), 1);
        OrderItem item2 = new OrderItem(new MenuItem("Milo", 1.50, new HashSet<>(), ""), 2);
        OrderItem item3 = new OrderItem(new MenuItem("Cheese Prata", 2, new HashSet<>(), ""), 3);
        try {
            model.addOrderItem(item1);
            model.addOrderItem(item2);
            model.addOrderItem(item3);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
    }

    private Model initialiseModel() {
        Model model = new ModelManager(getTypicalVendorManager(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        model.selectVendor(0);
        return model;
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = new ModelManager(getTypicalVendorManager(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        assertCommandFailure(new ClearCommand(), model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_emptyOrder_success() {
        Model model = initialiseModel();
        Model expectedModel = initialiseModel();
        OrderItem orderItem = new OrderItem(new MenuItem("Prata", 1, new HashSet<>(), ""), 1);
        try {
            model.addOrderItem(orderItem);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        model.clearOrder();
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_EMPTY_ORDER, expectedModel);
    }

    @Test
    public void execute_nonEmptyOrder_success() {
        Model model = initialiseModel();
        setOrderManager(model);
        Model expectedModel = initialiseModel();
        expectedModel.setOrderManager(new OrderManager());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
