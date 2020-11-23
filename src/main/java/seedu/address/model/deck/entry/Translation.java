package seedu.address.model.deck.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Entry's translation in the word bank.
 * Guarantees: immutable; is valid as declared in {@link #isValidTranslation(String)}
 */
public class Translation {
    public static final String MESSAGE_CONSTRAINTS = "Translations cannot be blank";
    public static final String LENGTH_CONSTRAINTS = "Translations cannot be longer than 200 characters";

    /**
     * Translations cannot be null or empty
     * Translations cannot be more than 200 characters long
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public static final int MAXIMUM_LENGTH = 200;

    public final String translation;

    /**
     * Constructs an {@code Translation}.
     *
     * @param translation A valid translation.
     */
    public Translation(String translation) {
        requireNonNull(translation);
        checkArgument(isValidLength(translation), LENGTH_CONSTRAINTS);
        checkArgument(isValidTranslation(translation), MESSAGE_CONSTRAINTS);
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    /**
     * Returns if a given string is a valid translation.
     */
    public static boolean isValidTranslation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidLength (String test) {
        return test.length() <= MAXIMUM_LENGTH;
    }

    @Override
    public String toString() {
        return translation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Translation // instanceof handles nulls
                && translation.equals(((Translation) other).translation)); // state check
    }

    @Override
    public int hashCode() {
        return translation.hashCode();
    }
}
