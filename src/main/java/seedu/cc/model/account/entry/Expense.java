package seedu.cc.model.account.entry;

import java.util.Set;

import seedu.cc.model.tag.Tag;
/**
 * Represents a expense entry in Common Cents.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Expense extends Entry {

    public Expense(Description description, Amount amount, Set<Tag> tags) {
        super(description, amount, tags);
    }

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
                && otherExpense.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Expense: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount());
        if (hasTags()) {
            builder.append(" Tags: ");
            getTags().forEach(builder::append);
        }
        return builder.toString();
    }

}
