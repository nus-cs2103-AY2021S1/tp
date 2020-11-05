package seedu.cc.model.account.entry;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.cc.model.tag.Tag;

/**
 * Represents the skeleton of an entry in Common Cents.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Entry {

    private final Description description;
    private final Amount amount;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Entry(Description description, Amount amount, Set<Tag> tags) {
        requireAllNonNull(description, amount, tags);
        this.description = description;
        this.amount = amount;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    protected boolean hasTags() {
        return !tags.isEmpty();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, amount, tags);
    }

}
