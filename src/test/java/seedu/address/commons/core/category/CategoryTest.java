package seedu.address.commons.core.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



public class CategoryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));
    }

    @Test
    public void isValidAmount_success() {
        // null category
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // blank category
        assertFalse(Category.isValidCategory("")); // empty string
        assertFalse(Category.isValidCategory(" ")); // spaces only

        // invalid category
        assertFalse(Category.isValidCategory("hello"));
        assertFalse(Category.isValidCategory("expenses"));
        assertFalse(Category.isValidCategory("expense1"));
        assertFalse(Category.isValidCategory("reve nue"));
        assertFalse(Category.isValidCategory("revenuee"));


        // valid category
        assertTrue(Category.isValidCategory("E"));
        assertTrue(Category.isValidCategory("e"));
        assertTrue(Category.isValidCategory("R"));
        assertTrue(Category.isValidCategory("r"));
        assertTrue(Category.isValidCategory("Expense"));
        assertTrue(Category.isValidCategory("expense"));
        assertTrue(Category.isValidCategory("ExPeNsE"));
        assertTrue(Category.isValidCategory("Revenue"));
        assertTrue(Category.isValidCategory("revenue"));
        assertTrue(Category.isValidCategory("rEvEnUe"));
    }

    @Test
    public void validIsExpense_success() {
        Category validExpense1 = new Category("expense");
        Category validExpense2 = new Category("e");
        Category validExpense3 = new Category("E");
        Category validExpense4 = new Category("exPeNSe");

        assertTrue(validExpense1.isExpense());
        assertTrue(validExpense2.isExpense());
        assertTrue(validExpense3.isExpense());
        assertTrue(validExpense4.isExpense());

        // revenue -> returns false
        assertFalse(new Category("reveNUe").isExpense());
    }

    @Test
    public void validIsRevenue_success() {
        Category validRevenue1 = new Category("reveNue");
        Category validRevenue2 = new Category("r");
        Category validRevenue3 = new Category("R");
        Category validRevenue4 = new Category("revenue");

        assertTrue(validRevenue1.isRevenue());
        assertTrue(validRevenue2.isRevenue());
        assertTrue(validRevenue3.isRevenue());
        assertTrue(validRevenue4.isRevenue());

        // expense -> returns false
        assertFalse(new Category("expeNse").isRevenue());
    }

    @Test
    public void toString_success() {
        Category validExpense = new Category("expEnse");
        Category validRevenue = new Category("r");

        assertEquals("expEnse", validExpense.toString());
        assertEquals("r", validRevenue.toString());
    }

    @Test
    public void equals() {
        Category validExpense = new Category("expEnse");
        Category validRevenue = new Category("r");

        // same object -> returns true
        assertEquals(validExpense, validExpense);
        assertEquals(validRevenue, validRevenue);

        // same value -> returns true
        Category validExpenseCopy = new Category("expEnse");
        Category validRevenueCopy = new Category("r");
        assertEquals(validExpense, validExpenseCopy);
        assertEquals(validRevenue, validRevenueCopy);

        // different types -> returns false
        assertNotEquals(null, validExpense);
        assertNotEquals(null, validRevenue);
        assertNotEquals(1, validExpense);
        assertNotEquals(1, validRevenue);
    }

    @Test
    public void hashCode_test() {
        Category validExpense = new Category("expEnse");
        Category validRevenue = new Category("r");

        Category anotherValidExpense = new Category("eXPENse");
        Category anotherValidRevenue = new Category("revenue");

        Category validExpenseCopy = new Category("expEnse");
        Category validRevenueCopy = new Category("r");

        // same value -> same hashcode
        assertEquals(validExpense.hashCode(), validExpenseCopy.hashCode());
        assertEquals(validRevenue.hashCode(), validRevenueCopy.hashCode());

        // different value -> different hashcode
        assertNotEquals(validExpense.hashCode(), anotherValidExpense.hashCode());
        assertNotEquals(validRevenue.hashCode(), anotherValidRevenue.hashCode());
        assertNotEquals(validExpense.hashCode(), validRevenue.hashCode());
    }
}
