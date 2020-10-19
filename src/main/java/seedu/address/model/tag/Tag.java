package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.description.Description;

/**
 * Represents a Tag in the HelloFile.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tag {

    // Identity fields
    private final TagName tagName;

    // Data fields
    private final FileAddress fileAddress;

    private final Description description;

    /**
     * Every field must be present and not null, escept description.
     */
    public Tag(TagName tagName, FileAddress fileAddress) {
        requireAllNonNull(tagName, fileAddress);
        this.tagName = tagName;
        this.fileAddress = fileAddress;
        this.description = new Description("");
    }

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName, FileAddress fileAddress, Description description) {
        requireAllNonNull(tagName, fileAddress, description);
        this.tagName = tagName;
        this.fileAddress = fileAddress;
        this.description = description;
    }

    public TagName getTagName() {
        return tagName;
    }

    public FileAddress getFileAddress() {
        return fileAddress;
    }

    public Description getDescription() {
        return description;
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
                && otherTag.getTagName().equals(getTagName());
    }
    /**
     * Returns true if both tag have the same identity and data fields.
     * This defines a stronger notion of equality between two tags.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return otherTag.getTagName().equals(getTagName())
                && otherTag.getFileAddress().equals(getFileAddress());
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
