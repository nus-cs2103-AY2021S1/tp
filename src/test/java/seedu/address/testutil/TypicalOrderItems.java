package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;

/**
 * A utility class containing a list of {@code OrderItem} objects to be used in tests.
 */
public class TypicalOrderItems {
    public static final double VALID_PRICE_MILO = 1.50;
    public static final String VALID_NAME_PRATA = "Prata";
    public static final String VALID_TAG_CLASSIC = "classic";

    // These data are the same as those found in `typicalOrderManager.json`
    public static final OrderItem PRATA = new OrderItemBuilder()
                                            .withName("Prata")
                                            .withPrice(1.00)
                                            .withQuantity(3)
                                            .withTags(VALID_TAG_CLASSIC)
                                            .build();
    public static final OrderItem MILO = new OrderItemBuilder()
                                            .withName("Milo")
                                            .withPrice(1.50)
                                            .withQuantity(2)
                                            .withTags("Iced")
                                            .build();
    public static final OrderItem NASI_GORENG = new OrderItemBuilder()
                                                    .withName("Nasi Goreng")
                                                    .withPrice(4.50)
                                                    .withQuantity(1)
                                                    .withTags("Bestseller")
                                                    .build();

    // Manually added
    public static final OrderItem NUGGETS = new OrderItemBuilder()
                                                .withName("Nuggets")
                                                .withPrice(3.20)
                                                .withQuantity(5)
                                                .withTags("six")
                                                .build();

    // Manually added - OrderItem's details found in {@code CommandTestUtil}
    public static final OrderItem CHEESE_PRATA = new OrderItemBuilder()
                                                    .withName("Cheese Prata")
                                                    .withPrice(1.60)
                                                    .withQuantity(2)
                                                    .withTags("cheesy")
                                                    .build();

    private TypicalOrderItems() {
    } // prevents instantiation

    /**
     * Returns an {@code OrderManager} with all the typical foods.
     */
    public static OrderManager getTypicalOrderManager() {
        OrderManager orderManager = new OrderManager();
        try {
            for (OrderItem orderItem : getTypicalOrderItems()) {
                orderManager.addOrderItem(orderItem);
            }
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        return orderManager;
    }

    public static List<OrderItem> getTypicalOrderItems() {
        return new ArrayList<>(Arrays.asList(PRATA, MILO, NASI_GORENG, NUGGETS, CHEESE_PRATA));
    }
}
