package seedu.expense.testutil;

import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.expense.model.ExpenseBook;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;

/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalExpenses {

    public static final Expense FEL_BDAY = new ExpenseBuilder().withDescription("Felicia's Birthday")
            .withDate("02-07-2020")
            .withAmount("140.00")
            .withRemark("Birthday surprise with friends + birthday presents + birthday dinner")
            .withTags("Girlfriend", "Shopping", "Food").build();
    public static final Expense GRAB_HOME = new ExpenseBuilder().withDescription("Grab Home")
            .withRemark("Need to stop grabbing so much!")
            .withDate("01-07-2020").withAmount("15.00")
            .withTags("Transport").build();
    public static final Expense ZARA = new ExpenseBuilder().withDescription("ZARA Jacket").withAmount("80.00")
            .withDate("30-06-2020")
            .withTags("Shopping").build();
    public static final Expense RAMEN = new ExpenseBuilder().withDescription("Ramen with Tyler").withAmount("18.50")
            .withDate("29-06-2020")
            .withTags("Food").build();
    public static final Expense PHONE_BILL = new ExpenseBuilder()
            .withDescription("Phone Bill Payment").withAmount("35.90")
            .withDate("29-06-2020").build();
    public static final Expense GRAB_SUPPER = new ExpenseBuilder()
            .withDescription("Grab to Supper").withAmount("5.00")
            .withDate("28-06-2020").build();
    public static final Expense SWEE_CHOON = new ExpenseBuilder()
            .withDescription("Swee Choon Supper").withAmount("12.40")
            .withDate("28-06-2020").build();

    // Manually added
    public static final Expense MOVIE = new ExpenseBuilder()
            .withDescription("Movie with Felicia").withAmount("14.00")
            .withDate("26-06-2020").build();
    public static final Expense EZ_LINK = new ExpenseBuilder()
            .withDescription("Top-up Ez-Link").withAmount("20")
            .withDate("25-06-2020").build();

    // Manually added - Expense's details found in {@code CommandTestUtil}
    public static final Expense FOOD = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_FOOD)
            .withAmount(VALID_AMOUNT_FOOD).withDate(VALID_DATE_FOOD)
            .withTags(VALID_TAG_FOOD).build();
    public static final Expense BUS = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_BUS)
            .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS)
            .withTags(VALID_TAG_TRANSPORT, VALID_TAG_FOOD)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExpenses() {} // prevents instantiation

    /**
     * Returns an {@code ExpenseBook} with all the typical expenses.
     */
    public static ExpenseBook getTypicalExpenseBook() {
        ExpenseBook ab = new ExpenseBook();
        for (Expense expense : getTypicalExpenses()) {
            ab.addExpense(expense);
        }
        Budget budget = new Budget();
        budget.topupBudget(new Amount("10"));
        ab.setBudget(budget);
        return ab;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(FEL_BDAY, GRAB_HOME, ZARA, RAMEN, PHONE_BILL, GRAB_SUPPER, SWEE_CHOON));
    }
}
