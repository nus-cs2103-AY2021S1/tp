package seedu.address.model.account.entry;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a profit entry in Common Cents.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Profit extends Entry {

    public Profit(Description description, Amount amount, Set<Tag> tags) {
        super(description, amount, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Profit)) {
            return false;
        }

        Profit otherProfit = (Profit) other;
        return otherProfit.getDescription().equals(getDescription())
                && otherProfit.getAmount().equals(getAmount())
                && otherProfit.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Profit: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
