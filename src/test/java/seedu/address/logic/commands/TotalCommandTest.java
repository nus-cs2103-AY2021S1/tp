package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TotalCommand.MESSAGE_RESULT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.TypicalModel;

public class TotalCommandTest {

    @Test
    public void execute_orderTotal_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        ObservableList<Food> menu = model.getFilteredFoodList();

        double calculatedTotal = 0;
        for (int i = 0; i < 5; i++) {
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
