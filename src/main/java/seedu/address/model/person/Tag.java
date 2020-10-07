package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;



/**
 * Represents a Tag in the HelloFile.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tag {

    // Identity fields
    private final TagName tagName;

    // Data fields
    private final FileAddress fileAddress;

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName, FileAddress fileAddress) {
        requireAllNonNull(tagName, fileAddress);
        this.tagName = tagName;
        this.fileAddress = fileAddress;
    }

    public TagName getTagName() {
        return tagName;
    }

    public FileAddress getFileAddress() {
        return fileAddress;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null
                && otherTag.getTagName().equals(getTagName())
                && otherTag.getFileAddress().equals(getFileAddress());
    }
    /**
     * Returns true if both tag have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherPerson = (Tag) other;
        return otherPerson.getTagName().equals(getTagName())
                && otherPerson.getFileAddress().equals(getFileAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tagName, fileAddress);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTagName())
                .append(" FileAddress: ")
                .append(getFileAddress());
        return builder.toString();
    }

}
