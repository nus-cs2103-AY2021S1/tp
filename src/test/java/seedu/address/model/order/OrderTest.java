package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrderItems.CHEESE_PRATA;
import static seedu.address.testutil.TypicalOrderItems.MILO;
import static seedu.address.testutil.TypicalOrderItems.NASI_GORENG;
import static seedu.address.testutil.TypicalOrderItems.NUGGETS;
import static seedu.address.testutil.TypicalOrderItems.PRATA;
import static seedu.address.testutil.TypicalOrderItems.VALID_NAME_PRATA;
import static seedu.address.testutil.TypicalOrderItems.VALID_PRICE_MILO;
import static seedu.address.testutil.TypicalOrderItems.VALID_TAG_CLASSIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.exceptions.DuplicateOrderItemException;
import seedu.address.model.order.exceptions.OrderItemNotFoundException;
import seedu.address.testutil.OrderItemBuilder;

public class OrderTest {

    private final Order order = new Order();

    @Test
    public void contains_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.contains(null));
    }

    @Test
    public void contains_orderItemNotInList_returnsFalse() {
        assertFalse(order.contains(PRATA));
    }

    @Test
    public void contains_orderItemInList_returnsTrue() {
        order.add(PRATA);
        assertTrue(order.contains(PRATA));
    }

    @Test
    public void contains_orderItemWithSameIdentityFieldsInList_returnsTrue() {
        order.add(PRATA);
        OrderItem editedPrata = new OrderItemBuilder(PRATA).withName(VALID_NAME_PRATA).withTags(VALID_TAG_CLASSIC)
                .build();
        assertTrue(order.contains(editedPrata));
    }

    @Test
    public void add_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.add(null));
    }

    @Test
    public void add_duplicateOrderItemTotalQuantity_success() {
        int initialQty = PRATA.getQuantity();
        int expectedFinalQty = initialQty * 2;
        order.add(PRATA);
        order.add(PRATA);
        assertEquals(expectedFinalQty, order.getOrderItem(VALID_NAME_PRATA).getQuantity());
    }

    @Test
    public void setOrderItem_nullTargetOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.setOrderItem(null, PRATA));
    }

    @Test
    public void setOrderItem_nullEditedOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.setOrderItem(PRATA, null));
    }

    @Test
    public void setOrderItem_targetOrderItemNotInList_throwsOrderItemNotFoundException() {
        assertThrows(OrderItemNotFoundException.class, () -> order.setOrderItem(PRATA, PRATA));
    }

    @Test
    public void setOrderItem_editedOrderItemIsSameOrderItem_success() {
        order.add(PRATA);
        order.setOrderItem(PRATA, PRATA);
        Order expectedOrder = new Order();
        expectedOrder.add(PRATA);
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItem_editedOrderItemHasSameIdentity_success() {
        order.add(PRATA);
        OrderItem editedPrata = new OrderItemBuilder(PRATA).withPrice(VALID_PRICE_MILO).withTags(VALID_TAG_CLASSIC)
                .build();
        order.setOrderItem(PRATA, editedPrata);
        Order expectedOrder = new Order();
        expectedOrder.add(editedPrata);
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItem_editedOrderItemHasDifferentIdentity_success() {
        order.add(PRATA);
        order.setOrderItem(PRATA, MILO);
        Order expectedOrder = new Order();
        expectedOrder.add(MILO);
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItem_editedOrderItemHasNonUniqueIdentity_throwsDuplicateOrderItemException() {
        order.add(PRATA);
        order.add(MILO);
        assertThrows(DuplicateOrderItemException.class, () -> order.setOrderItem(PRATA, MILO));
    }

    @Test
    public void remove_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.remove(null));
    }

    @Test
    public void remove_orderItemDoesNotExist_throwsOrderItemNotFoundException() {
        assertThrows(OrderItemNotFoundException.class, () -> order.remove(PRATA));
    }

    @Test
    public void remove_existingOrderItem_removesOrderItem() {
        order.add(PRATA);
        order.remove(PRATA);
        Order expectedOrder = new Order();
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItems_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.setOrderItems((List<OrderItem>) null));
    }

    @Test
    public void setOrderItems_order_replacesOwnListWithProvidedOrder() {
        order.add(PRATA);
        Order expectedOrder = new Order();
        expectedOrder.add(MILO);
        List<OrderItem> lst = new ArrayList<>();
        expectedOrder.forEach(lst:: add);
        order.setOrderItems(lst);
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.setOrderItems((List<OrderItem>) null));
    }

    @Test
    public void setOrderItems_list_replacesOwnListWithProvidedList() {
        order.add(PRATA);
        List<OrderItem> orderItemList = Collections.singletonList(MILO);
        order.setOrderItems(orderItemList);
        Order expectedOrder = new Order();
        expectedOrder.add(MILO);
        assertEquals(expectedOrder, order);
    }

    @Test
    public void setOrderItems_listWithDuplicateOrderItems_throwsDuplicateOrderItemException() {
        List<OrderItem> listWithDuplicateOrderItems = Arrays.asList(PRATA, PRATA);
        assertThrows(DuplicateOrderItemException.class, () -> order.setOrderItems(listWithDuplicateOrderItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> order.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getOrderItem_emptyList_throwsOrderItemNotFoundException() {
        assertThrows(OrderItemNotFoundException.class, () -> order.getOrderItem(VALID_NAME_PRATA));
    }

    @Test
    public void getOrderItem_listWithoutSpecifiedItem_throwsOrderItemNotFoundException() {
        order.add(CHEESE_PRATA);
        order.add(NUGGETS);
        order.add(NASI_GORENG);
        assertThrows(OrderItemNotFoundException.class, () -> order.getOrderItem(VALID_NAME_PRATA));
    }

    @Test
    public void setOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> order.setOrder(null));
    }

    @Test
    public void setOrder_replaceList_success() {
        order.add(NASI_GORENG);
        order.add(MILO);
        Order replacementOrder = new Order();
        replacementOrder.add(CHEESE_PRATA);
        replacementOrder.add(PRATA);
        replacementOrder.add(NUGGETS);
        order.setOrder(replacementOrder);
        assertEquals(order, replacementOrder);
    }

    @Test
    public void toString_emptyOrder_success() {
        assertEquals(order.toString(), "");
    }

    @Test
    public void toString_properOrder_success() {
        order.add(MILO);
        order.add(PRATA);
        order.add(CHEESE_PRATA);
        StringBuilder text = new StringBuilder();
        text.append(MILO.toString() + '\n').append(PRATA.toString() + '\n').append(CHEESE_PRATA.toString() + '\n');
        assertEquals(order.toString(), text.toString());
    }
}
