package seedu.medibook.model.medicalnote;

import static java.util.Objects.requireNonNull;

/**
 * Represents the textual content of a medical note.
 */
public class Content implements Comparable<Content> {

    public static final String MESSAGE_CONSTRAINTS = "Content cannot be blank.";

    private final String innerString;

    /**
     * Construct a content object containing the specified string.
     * @param str content of medical note in string, cannot be empty.
     */
    public Content(String str) {
        requireNonNull(str);
        if (!isValidContent(str)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        innerString = str;
    }

    /**
     * Returns true if the given string is a valid content (non-empty).
     * @param test string to be tested.
     * @return true if string is valid as a content.
     */
    public static boolean isValidContent(String test) {
        return test.length() > 0;
    }

    @Override
    public String toString() {
        return innerString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Content)) {
            return false;
        }

        Content otherContent = (Content) other;
        return otherContent.innerString.equals(innerString);
    }

    @Override
    public int compareTo(Content o) {
        return innerString.compareToIgnoreCase(o.innerString);
    }
}
