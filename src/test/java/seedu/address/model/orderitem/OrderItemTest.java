package seedu.address.model.orderitem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrderItems.CHEESE_PRATA;
import static seedu.address.testutil.TypicalOrderItems.PRATA;
import static seedu.address.testutil.TypicalOrderItems.getTypicalOrderItems;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.OrderItem;
import seedu.address.testutil.OrderItemBuilder;

public class OrderItemTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        OrderItem orderItem = new OrderItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> orderItem.getTags().remove(0));
    }

    @Test
    public void equals() {
        getTypicalOrderItems();
        // same values -> returns true
        OrderItem prataCopy = new OrderItemBuilder(PRATA).build();
        assertTrue(PRATA.equals(prataCopy));

        // same object -> returns true
        assertTrue(PRATA.equals(PRATA));

        // null -> returns false
        assertFalse(PRATA.equals(null));

        // different type -> returns false
        assertFalse(PRATA.equals(5));

        // different orderItem -> returns false
        assertFalse(PRATA.equals(CHEESE_PRATA));
        // different name -> returns false
        OrderItem editedPrata = new OrderItemBuilder(PRATA).withName("Cheese Prata").build();
        assertFalse(PRATA.equals(editedPrata));

        // different price -> returns false
        editedPrata = new OrderItemBuilder(PRATA).withPrice(2.3).build();
        assertFalse(PRATA.equals(editedPrata));

        // different quantity, same name -> returns true
        editedPrata = new OrderItemBuilder(PRATA).withQuantity(4).build();
        assertTrue(PRATA.equals(editedPrata));
    }

    @Test
    public void invalidQuantityTest() {
        assertFalse(OrderItem.isValidQuantity(-2));
        assertFalse(OrderItem.isValidQuantity(0));
        assertTrue(OrderItem.isValidQuantity(1));
    }
}
