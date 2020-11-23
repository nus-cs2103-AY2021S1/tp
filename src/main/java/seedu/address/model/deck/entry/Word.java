package seedu.address.model.deck.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Entry's word in the word bank.
 * Guarantees: immutable; is valid as declared in {@link #isValidWord(String)}
 */
public class Word {

    public static final String MESSAGE_CONSTRAINTS = "Words cannot be blank";
    public static final String LENGTH_CONSTRAINTS = "Words cannot be longer than 200 characters";

    /**
     * Words cannot be null or empty
     * Words cannot be more than 200 characters long
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public static final int MAXIMUM_LENGTH = 200;
    public final String word;

    /**
     * Constructs a {@code Word}.
     *
     * @param word A valid word.
     */
    public Word(String word) {
        requireNonNull(word);
        checkArgument(isValidLength(word), LENGTH_CONSTRAINTS);
        checkArgument(isValidWord(word), MESSAGE_CONSTRAINTS);
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    /**
     * Returns true if a given string is a valid word.
     */
    public static boolean isValidWord(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidLength (String test) {
        return test.length() <= MAXIMUM_LENGTH;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Word // instanceof handles nulls
                && word.equals(((Word) other).word)); // state check
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
