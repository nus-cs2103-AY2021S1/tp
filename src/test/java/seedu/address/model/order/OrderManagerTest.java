package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrderItems.PRATA;
import static seedu.address.testutil.TypicalOrderItems.VALID_TAG_CLASSIC;
import static seedu.address.testutil.TypicalOrderItems.getTypicalOrderManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.order.exceptions.DuplicateOrderItemException;
import seedu.address.testutil.OrderItemBuilder;

public class OrderManagerTest {
    private final OrderManager orderManager = new OrderManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), orderManager.getOrderItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyOrderManager_replacesData() {
        OrderManager newData = getTypicalOrderManager();
        orderManager.resetData(newData);
        assertEquals(newData, orderManager);
    }

    @Test
    public void resetData_withDuplicateOrderItems_throwsDuplicateOrderItemException() {
        // Two orderItems with the same identity fields
        OrderItem editedPrata = new OrderItemBuilder(PRATA).build();
        List<OrderItem> newOrderItems = Arrays.asList(PRATA, editedPrata);
        OrderManager newData = new OrderManager();

        assertThrows(DuplicateOrderItemException.class, () -> {
            newData.setOrder(newOrderItems);
            orderManager.resetData(newData);
        });
    }

    @Test
    public void hasOrderItem_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderManager.hasOrderItem(null));
    }

    @Test
    public void hasOrderItem_orderItemNotInOrderManager_returnsFalse() {
        assertFalse(orderManager.hasOrderItem(PRATA));
    }

    @Test
    public void hasOrderItem_orderItemInOrderManager_returnsTrue() {
        try {
            orderManager.addOrderItem(PRATA);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        assertTrue(orderManager.hasOrderItem(PRATA));
    }

    @Test
    public void hasOrderItem_orderItemWithSameIdentityFieldsInOrderManager_returnsTrue() {
        try {
            orderManager.addOrderItem(PRATA);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        OrderItem editedPrata = new OrderItemBuilder(PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(orderManager.hasOrderItem(editedPrata));
    }

    @Test
    public void getOrderItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> orderManager.getOrderItemList().remove(0));
    }
}
