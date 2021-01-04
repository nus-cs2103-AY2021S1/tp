package seedu.homerce.testutil.expense;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;

/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {
    public static final String DEFAULT_DESCRIPTION = "Conditioner";
    public static final String DEFAULT_ISFIXED = "n";
    public static final double DEFAULT_AMOUNT = 15.0;
    public static final String DEFAULT_DATE = "10-12-2020";
    public static final String DEFAULT_TAG = "Hairsupplies";

    private Description description;
    private IsFixed isFixed;
    private Amount value;
    private Date date;
    private Tag tag;

    /**
     * Creates a {@Code ExpenseBuilder} with default details.
     */
    public ExpenseBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        isFixed = new IsFixed(DEFAULT_ISFIXED);
        value = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        description = expenseToCopy.getDescription();
        isFixed = expenseToCopy.getIsFixed();
        value = expenseToCopy.getValue();
        date = expenseToCopy.getDate();
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
     * Sets the {@code IsFixed} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withIsFixed(String isFixed) {
        this.isFixed = new IsFixed(isFixed);
        return this;
    }

    /**
     * Sets the {@code Value} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withAmount(double value) {
        this.value = new Amount(value);
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
     * Sets the {@code Tag} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withTag() {
        this.tag = new Tag();
        return this;
    }

    public Expense build() {
        return new Expense(description, isFixed, value, date, tag);
    }
}

