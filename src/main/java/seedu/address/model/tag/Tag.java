package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private final Set<Description> descriptions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName, FileAddress fileAddress, Set<Description> descriptions) {
        requireAllNonNull(tagName, fileAddress, descriptions);
        this.tagName = tagName;
        this.fileAddress = fileAddress;
        this.descriptions.addAll(descriptions);
    }

    public TagName getTagName() {
        return tagName;
    }

    public FileAddress getFileAddress() {
        return fileAddress;
    }

    public Set<Description> getDescriptions() {
        return Collections.unmodifiableSet(descriptions);
    }

    /**
     * Converts the current tag to one with absolute address.
     *
     * @return The same tag but with absolute file address.
     * @throws IllegalArgumentException If file does not exist.
     */
    public Tag toAbsolute() {
        File file = new File(fileAddress.value);
        if (!file.exists()) {
            throw new IllegalArgumentException("Tag address not valid!");
        }
        FileAddress absAddress = new FileAddress(file.getAbsolutePath());

        return new Tag(tagName, absAddress, descriptions);
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
                && otherTag.getFileAddress().equals(getFileAddress())
                && otherTag.getDescriptions().equals(getDescriptions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tagName, fileAddress, descriptions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTagName())
                .append(" FileAddress: ")
                .append(getFileAddress())
                .append(" Descriptions: ");
        getDescriptions().forEach(builder::append);
        return builder.toString();
    }

}
