package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TotalCommand.MESSAGE_RESULT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.TypicalModel;

public class TotalCommandTest {

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new TotalCommand(), model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_noOrder_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        assertCommandFailure(new TotalCommand(), model, Messages.MESSAGE_EMPTY_ORDER);
    }

    @Test
    public void execute_orderTotal_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();

        double calculatedTotal = 0;
        for (int i = 0; i < 3; i++) {
            OrderItem orderItem = new OrderItem(menu.get(i), i + 6);
            calculatedTotal += orderItem.getPrice() * (i + 6);
            try {
                model.addOrderItem(orderItem);
                expectedModel.addOrderItem(orderItem);
            } catch (CommandException e) {
                Assertions.assertTrue(false);
            }
        }
        String expectedMessage = String.format(MESSAGE_RESULT, calculatedTotal);

        assertCommandSuccess(new TotalCommand(), model, expectedMessage, expectedModel);
    }
}
