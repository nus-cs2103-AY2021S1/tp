package seedu.forgetfulnus.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a german phrase in the glossary.
 * Guarantees: immutable; is valid as declared in {@link #isValidGermanPhrase(String)}
 */
public class GermanPhrase {

    public static final String MESSAGE_CONSTRAINTS =
            "German phrases should only contain english alphabets and spaces, and it should not be blank";

    /*
     * The first character of the German phrase must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{L}][\\p{L} ]*";

    private final String fullGermanPhrase;

    /**
     * Constructs a {@code German phrase}.
     *
     * @param germanPhrase A valid german phrase.
     */
    public GermanPhrase(String germanPhrase) {
        requireNonNull(germanPhrase);
        checkArgument(isValidGermanPhrase(germanPhrase), MESSAGE_CONSTRAINTS);
        fullGermanPhrase = germanPhrase;
    }

    /**
     * Returns true if a given string is a valid german phrase.
     */
    public static boolean isValidGermanPhrase(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullGermanPhrase;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GermanPhrase // instanceof handles nulls
                && fullGermanPhrase.toLowerCase()
                .equals(((GermanPhrase) other).fullGermanPhrase.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return fullGermanPhrase.hashCode();
    }

}
