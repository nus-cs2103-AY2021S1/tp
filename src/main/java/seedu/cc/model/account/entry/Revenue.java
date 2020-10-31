package seedu.cc.model.account.entry;

import java.util.Set;

import seedu.cc.model.tag.Tag;

/**
 * Represents a revenue entry in Common Cents.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Revenue extends Entry {

    public Revenue(Description description, Amount amount, Set<Tag> tags) {
        super(description, amount, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Revenue)) {
            return false;
        }

        Revenue otherRevenue = (Revenue) other;
        return otherRevenue.getDescription().equals(getDescription())
                && otherRevenue.getAmount().equals(getAmount())
                && otherRevenue.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Revenue: ")
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
