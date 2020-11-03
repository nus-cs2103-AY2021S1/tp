package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.TypicalModel;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class SubmitCommandTest {

    @Test
    public void execute_submit_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();

        boolean copySuccess = true;
        try {
            StringSelection stringSelection = new StringSelection("testing clipboard");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            // clipboard.setContents(stringSelection, null);
        } catch (HeadlessException e) {
            copySuccess = false;
        }

        StringBuilder expectedMessage = new StringBuilder();
        if (copySuccess) {
            expectedMessage.append(SubmitCommand.CLIPBOARD_SUCCESS_MESSAGE);
        }
        double calculatedTotal = 0;
        for (int i = 0; i < 5; i++) {
            OrderItem orderItem = new OrderItem(menu.get(i), i + 6);
            expectedMessage.append(String.format("%s x %d\n", orderItem.getName(), i + 6));
            calculatedTotal += orderItem.getPrice() * (i + 6);
            try {
                model.addOrderItem(orderItem);
                expectedModel.addOrderItem(orderItem);
            } catch (CommandException e) {
                Assertions.assertTrue(false);
            }
        }
        expectedMessage.append(String.format("Estimated total: $%.2f\n", calculatedTotal));
        assertCommandSuccess(new SubmitCommand(), model, expectedMessage.toString(), expectedModel);
    }
}
