
package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpenseTest {

    @Test
    public void isSameExpense() {
        Amount buyBookExpense = new Amount(10.0);
        Category buyBookCategory = new Category("study");
        Date buyBookDate = new Date("2");
        Description buyBookDescription = new Description("bought book at coop");

        Expense buyBook = new Expense(buyBookExpense, buyBookDate,
                buyBookCategory, buyBookDescription);

        Amount buyFoodExpense = new Amount(10.0);
        Category buyFoodCategory = new Category("food");
        Date buyFoodDate = new Date("2");
        Description buyFoodDescription = new Description("bought sushi for dinner");

        Expense buyFood = new Expense(buyFoodExpense, buyFoodDate,
                buyFoodCategory, buyFoodDescription);

        assertTrue(buyBook.isSameExpense(buyBook));

        assertTrue(buyFood.isSameExpense(buyFood));

        assertFalse(buyBook.isSameExpense(buyFood));

        assertFalse(buyFood.isSameExpense(buyBook));

        assertFalse(buyFood.isSameExpense(null));

        assertFalse(buyBook.isSameExpense(null));
    }

    @Test
    public void isValidExpense() {

        Amount buyBookExpense = new Amount(10.0);
        Category buyBookCategory = new Category("study");
        Description buyBookDescription = new Description("bought book at coop");

        assertThrows(NullPointerException.class, () -> new Expense(buyBookExpense, null,
                buyBookCategory, buyBookDescription));

        Category buyFoodCategory = new Category("food");
        Date buyFoodDate = new Date("2");
        Description buyFoodDescription = new Description("bought sushi for dinner");

        assertThrows(NullPointerException.class, () -> new Expense(null, buyFoodDate,
                buyFoodCategory, buyFoodDescription));

        Amount movieExpense = new Amount(10.0);
        Date movieDate = new Date("1");
        Description movieDescription = new Description("watch 1917");

        assertThrows(NullPointerException.class, () -> new Expense(movieExpense, movieDate,
                null, movieDescription));

        assertThrows(NullPointerException.class, () -> new Expense(null, null,
                null, null));
    }

}
