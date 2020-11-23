package seedu.address.model.deck.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Entry in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Entry {

    // Identity fields
    private final Word word;
    private final Translation translation;

    /**
     * Every field must be present and not null.
     */
    public Entry(Word word, Translation translation) {
        requireAllNonNull(word, translation);
        this.word = word;
        this.translation = translation;
    }

    public Word getWord() {
        return word;
    }

    public Translation getTranslation() {
        return translation;
    }

    /**
     * Returns true if both entries of the same word have the same translation
     * This defines a notion of equality between the two entries
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        return otherEntry != null
                && (otherEntry.getTranslation().equals(getTranslation()));
    }

    /**
     * Returns true if both entries have the same translation
     * This defines a notion of equality between two entry objects
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return otherEntry.getTranslation().equals(getTranslation());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(word, translation);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getWord())
                .append(" Translation: ")
                .append(getTranslation());
        return builder.toString();
    }

}
