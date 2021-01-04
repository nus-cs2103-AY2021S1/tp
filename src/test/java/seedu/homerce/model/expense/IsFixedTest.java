package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.expense.ExpenseBuilder;

public class IsFixedTest {
    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsFixed(null));
    }

    @Test
    public void constructorInvalidIsFixedThrowsIllegalArgumentException() {
        String invalidIsFixedLetter = "z";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedLetter));

        String invalidIsFixedLetters = "abc";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedLetters));

        String invalidIsFixedSymbol = "//";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedSymbol));
    }

    @Test
    public void constructorInvalidIsRecurringThrowsIllegalArgumentException() {
        String invalidIsFixedLetter = "z";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedLetter, true));

        String invalidIsFixedLetters = "abc";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedLetters, false));

        String invalidIsFixedSymbol = "//";
        assertThrows(IllegalArgumentException.class, () -> new IsFixed(invalidIsFixedSymbol, true));
    }

    @Test
    public void isValidIsFixed() {
        // Null IsFixed
        assertThrows(NullPointerException.class, () -> IsFixed.isValidIsFixed(null));

        // Invalid IsFixed
        assertFalse(IsFixed.isValidIsFixed("abc")); // Invalid letters
        assertFalse(IsFixed.isValidIsFixed("a")); // Invalid letter
        assertFalse(IsFixed.isValidIsFixed("//"));

        // Valid IsFixed
        assertTrue(IsFixed.isValidIsFixed("y"));
        assertTrue(IsFixed.isValidIsFixed("n"));
    }

    @Test
    public void testToString() {
        assertEquals("Yes", new IsFixed("y").toString());
        assertEquals("No", new IsFixed("n").toString());
    }

    @Test
    public void testMarkAsRecurred() {
        Expense recurringExpense = new ExpenseBuilder().withIsFixed("y").build();
        recurringExpense.getIsFixed().markAsRecurred();
        assertEquals(false, recurringExpense.getIsFixed().getIsRecurring());
    }
}

