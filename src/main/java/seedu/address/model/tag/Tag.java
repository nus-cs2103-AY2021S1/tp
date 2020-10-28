package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.label.Label;

/**
 * Represents a Tag in the HelloFile.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tag {

    // Identity fields
    private final TagName tagName;

    // Data fields
    private final FileAddress fileAddress;

    private final Set<Label> labels = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName, FileAddress fileAddress, Set<Label> labels) {
        requireAllNonNull(tagName, fileAddress, labels);
        this.tagName = tagName;
        this.fileAddress = fileAddress;
        this.labels.addAll(labels);
    }

    public TagName getTagName() {
        return tagName;
    }

    public FileAddress getFileAddress() {
        return fileAddress;
    }

    public Set<Label> getLabels() {
        return Collections.unmodifiableSet(labels);
    }

    /**
     * Converts the current tag to one with absolute address.
     *
     * @return The same tag but with absolute file address.
     * @throws IllegalArgumentException If file does not exist.
     */
    public Tag toAbsolute(boolean isAbsolutePath, FileAddress currentPath) {
        File file;

        if (isAbsolutePath) {
            file = new File(fileAddress.value);
        } else {
            file = new File(currentPath.value, fileAddress.value);
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("Tag address not valid!");
        }

        FileAddress absAddress;

        try {
            absAddress = new FileAddress(Paths.get(file.getCanonicalPath()).normalize().toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("Tag address not found!");
        }

        return new Tag(tagName, absAddress, labels);
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
                && otherTag.getLabels().equals(getLabels());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tagName, fileAddress, labels);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTagName())
                .append(" FileAddress: ")
                .append(getFileAddress())
                .append(" Labels: ");
        getLabels().forEach(builder::append);
        return builder.toString();
    }

}
