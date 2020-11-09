package seedu.expense.model.expense;

import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import java.util.Objects;

import seedu.expense.model.tag.Tag;

/**
 * Represents an Expense in the expense book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Expense {

    // Identity fields
    private final Description description;
    private final Amount amount;
    private final Date date;

    // Data fields
    private final Remark remark;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Expense(Description description, Amount amount, Date date, Remark remark, Tag tag) {
        requireAllNonNull(description, amount, date, tag);
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
        this.tag = tag;
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Remark getRemark() {
        return remark;
    }

    public Tag getTag() {
        return tag;
    }

    public Expense resetTag() {
        return new Expense(description, amount, date, remark, DEFAULT_TAG);
    }

    /**
     * Returns true if both expenses have the same description, amount and date.
     * The same level of equality is defined as the this#equals method.
     */
    public boolean isSameExpense(Expense otherExpense) {
        if (otherExpense == this) {
            return true;
        }

        return otherExpense != null
                && otherExpense.getDescription().equals(getDescription())
                && (otherExpense.getAmount().equals(getAmount())
                && otherExpense.getDate().equals(getDate()));
    }

    /**
     * Returns true if both expenses have the same description, amount and date.
     * This defines a strong notion of equality between two expenses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return otherExpense.getDescription().equals(getDescription())
                && otherExpense.getAmount().equals(getAmount())
                && otherExpense.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, amount, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Amount: $")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Tags: ")
                .append(getTag());
        return builder.toString();
    }

}
