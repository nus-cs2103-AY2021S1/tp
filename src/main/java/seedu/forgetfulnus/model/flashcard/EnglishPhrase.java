package seedu.forgetfulnus.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a FlashCard's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEnglishPhrase(String)} (String)}
 */
public class EnglishPhrase {


    public static final String MESSAGE_CONSTRAINTS =
            "English phrases should only contain alphabets and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";
    private final String fullEnglishPhrase;

    /**
     * Constructs a {@code English phrase}.
     *
     * @param englishPhrase A valid english phrase.
     */
    public EnglishPhrase(String englishPhrase) {
        requireNonNull(englishPhrase);
        checkArgument(isValidEnglishPhrase(englishPhrase), MESSAGE_CONSTRAINTS);
        fullEnglishPhrase = englishPhrase;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidEnglishPhrase(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is equivalent to the full english phrase, case insensitive.
     */
    public boolean isCorrectAttempt(String attempt) {
        assert (attempt != null);
        return attempt.trim().replaceAll("( )+", " ")
                .toLowerCase().equals(fullEnglishPhrase.toLowerCase());
    }

    @Override
    public String toString() {
        return fullEnglishPhrase;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnglishPhrase // instanceof handles nulls
                && fullEnglishPhrase.toLowerCase()
                .equals(((EnglishPhrase) other).fullEnglishPhrase.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return fullEnglishPhrase.hashCode();
    }
}
