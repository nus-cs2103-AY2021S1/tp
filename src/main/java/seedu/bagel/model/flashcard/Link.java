package seedu.bagel.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a Flashcard's link in Bagel.
 * Guarantees: immutable; is always valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Links should either be a valid URL or blank\n"
            + "A valid URL should:\n"
            + "- have a protocol e.g. https://example.com instead of example.com\n"
            + "- be absolute e.g. file:///GER1000/example.png instead of file://example.png";

    public final String value;

    /**
     * Constructs an {@code Link}.
     *
     * @param link A valid link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        value = link;
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        if (test.isEmpty()) {
            return true;
        }
        try {
            new URL(test);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && value.equals(((Link) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
