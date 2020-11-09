package seedu.expense.testutil;

import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.Description;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.Remark;
import seedu.expense.model.tag.Tag;

/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {

    public static final String DEFAULT_DESCRIPTION = "Lunch Bak Chor Mee";
    public static final String DEFAULT_AMOUNT = "3.00";
    public static final String DEFAULT_DATE = "04-10-2020";
    public static final String DEFAULT_REMARK = "Very expensive.";

    private Description description;
    private Amount amount;
    private Date date;
    private Remark remark;
    private Tag tag;

    /**
     * Creates a {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        remark = new Remark(DEFAULT_REMARK);
        tag = DEFAULT_TAG;
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        description = expenseToCopy.getDescription();
        amount = expenseToCopy.getAmount();
        date = expenseToCopy.getDate();
        remark = expenseToCopy.getRemark();
        tag = expenseToCopy.getTag();
    }

    /**
     * Sets the {@code Description} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Tag} and set it to the {@code Expense} that we are building.
     */
    public ExpenseBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Expense build() {
        return new Expense(description, amount, date, remark, tag);
    }

}
