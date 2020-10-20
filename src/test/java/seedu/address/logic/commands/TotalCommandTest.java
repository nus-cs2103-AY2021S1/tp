package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TotalCommand.MESSAGE_RESULT;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.TypicalModel;

public class TotalCommandTest {

    @Test
    public void execute_orderTotal_success() {
        Model model = TypicalModel.getModelManagerWithMenu();

        ObservableList<Food> menu = model.getFilteredFoodList(0);

        Food firstItem = menu.get(0);
        double firstPrice = firstItem.getPrice();
        Food secondItem = menu.get(1);
        double secondPrice = secondItem.getPrice();
        Food thirdItem = menu.get(2);
        double thirdPrice = thirdItem.getPrice();
        Food fourthItem = menu.get(3);
        double fourthPrice = fourthItem.getPrice();
        Food fifthItem = menu.get(4);
        double fifthPrice = fifthItem.getPrice();
        double calculatedTotal = firstPrice * 6 + secondPrice * 7 + thirdPrice * 8 + fourthPrice * 9 + fifthPrice * 10;

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        model.addOrderItem(new OrderItem(firstItem, 6));
        model.addOrderItem(new OrderItem(secondItem, 7));
        model.addOrderItem(new OrderItem(thirdItem, 8));
        model.addOrderItem(new OrderItem(fourthItem, 9));
        model.addOrderItem(new OrderItem(fifthItem, 10));
        expectedModel.addOrderItem(new OrderItem(firstItem, 6));
        expectedModel.addOrderItem(new OrderItem(secondItem, 7));
        expectedModel.addOrderItem(new OrderItem(thirdItem, 8));
        expectedModel.addOrderItem(new OrderItem(fourthItem, 9));
        expectedModel.addOrderItem(new OrderItem(fifthItem, 10));
        String expectedMessage = String.format(MESSAGE_RESULT, calculatedTotal);

        assertCommandSuccess(new TotalCommand(), model, expectedMessage, expectedModel);
    }
}
