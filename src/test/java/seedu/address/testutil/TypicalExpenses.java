package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPTY_DESCRIPTION_BOOKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ExpenseBook;
import seedu.address.model.expense.Expense;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalExpenses {

    public static final Expense CLOTHES = new ExpenseBuilder().withAmount(60.0)
            .withDate("1").withCategory("clothes")
            .withDescription("bought a jacket at Zara")
            .build();
    public static final Expense SNACKS = new ExpenseBuilder().withAmount(9.0)
            .withDate("0").withCategory("snacks")
            .withDescription("bought chocolate")
            .build();
    public static final Expense KEYBOARD = new ExpenseBuilder().withAmount(80.0)
            .withDate("0").withCategory("digital devices")
            .withDescription("bought logitech keyboard")
            .build();

    // Manually added - Expense's details found in {@code CommandTestUtil}
    public static final Expense BOOKS = new ExpenseBuilder().withAmount(VALID_AMOUNT_BOOKS)
            .withDate(VALID_DATE_BOOKS).withCategory(VALID_CATEGORY_BOOKS)
            .withDescription(VALID_DESCRIPTION_BOOKS)
            .build();
    public static final Expense BOOKSWITHOUTDES = new ExpenseBuilder().withAmount(VALID_AMOUNT_BOOKS)
            .withDate(VALID_DATE_BOOKS).withCategory(VALID_CATEGORY_BOOKS)
            .withDescription(VALID_EMPTY_DESCRIPTION_BOOKS)
            .build();
    public static final Expense MOVIE = new ExpenseBuilder().withAmount(VALID_AMOUNT_MOVIE)
            .withDate(VALID_DATE_MOVIE).withCategory(VALID_CATEGORY_MOVIE)
            .withDescription(VALID_DESCRIPTION_MOVIE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExpenses() {} // prevents instantiation

    /**
     * Returns an {@code expenseBook} with all the typical expense.
     */
    public static ExpenseBook getTypicalExpenseBook() {
        ExpenseBook expenseBook = new ExpenseBook();
        for (Expense expense : getTypicalExpenses()) {
            expenseBook.addExpense(expense);
        }
        return expenseBook;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(CLOTHES, SNACKS, KEYBOARD));
    }
}
