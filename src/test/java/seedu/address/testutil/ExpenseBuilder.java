package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleCommonCentsUtilData;


/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {
    private static final String DEFAULT_DESCRIPTION = "buying paint supplies";
    private static final String DEFAULT_AMOUNT = "131.73";

    private Description description;
    private Amount amount;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(DEFAULT_AMOUNT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        description = expenseToCopy.getDescription();
        amount = expenseToCopy.getAmount();
        tags = expenseToCopy.getTags();
    }

    /**
     * Sets the {@code Description} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Expense} that we are building.
     */
    public ExpenseBuilder withTags(String ... tags) {
        this.tags = SampleCommonCentsUtilData.getTagSet(tags);
        return this;
    }

    public Expense build() {
        return new Expense(description, amount, tags);
    }
}
