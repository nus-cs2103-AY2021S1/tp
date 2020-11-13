package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.budget.Budget;
import seedu.address.testutil.TypicalBudget;

class NodeTest {
    @Test
    void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Node<Budget>(null));
    }

    @Test
    void getValue_withValidBudget_returnsCorrectly() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertEquals(value, node.getValue());
    }

    @Test
    void getNext_withoutNext_returnsCorrectly() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertEquals(null, node.getNext());
    }

    @Test
    void getPrevious_withoutPrevious_returnsCorrectly() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertEquals(null, node.getPrevious());
    }

    @Test
    void hasNext_withoutNext_returnsFalse() {
        Node<Budget> node = new Node<>();
        assertFalse(node.hasNext());
    }

    @Test
    void hasPrevious_withoutNext_returnsFalse() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertFalse(node.hasPrevious());
    }

    @Test
    void connectTo_withValidBudget_returnsCorrectly() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        Budget nextValue = TypicalBudget.getMcDonaldsBudget();
        Node<Budget> nextNode = new Node<>(nextValue);
        node.connectTo(nextNode);
        assertEquals(nextNode, node.getNext());
        assertEquals(node, nextNode.getPrevious());
    }

    @Test
    void isNull_withValue_returnsFalse() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertFalse(node.isNull());
    }

    @Test
    void isEmpty_withEmptyNode_returnsTrue() {
        Node<Budget> node = new Node<>();
        assertTrue(node.isEmpty());
    }

    @Test
    void equals_withSameNode_returnsTrue() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertTrue(node.equals(node));
    }

    @Test
    void equals_withValidNode_returnsTrue() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        Budget valueToCompare = TypicalBudget.getKfcBudget();
        Node<Budget> nodeToCompare = new Node<>(valueToCompare);
        assertTrue(node.equals(nodeToCompare));
    }

    @Test
    void equals_withNull_returnsFalse() {
        Budget value = TypicalBudget.getKfcBudget();
        Node<Budget> node = new Node<>(value);
        assertFalse(node.equals(null));
    }
}
