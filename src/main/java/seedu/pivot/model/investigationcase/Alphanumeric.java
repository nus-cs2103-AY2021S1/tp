package seedu.pivot.model.investigationcase;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public abstract class Alphanumeric {

    public static final String MESSAGE_CONSTRAINTS =
            "Only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the input must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String alphaNum;

    /**
     * Constructs a {@code Name}.
     *
     * @param alphaNum A valid alphaNum.
     */
    protected Alphanumeric(String alphaNum, boolean canBeBlank) {
        requireNonNull(alphaNum);
        String trimmedAlphanum = alphaNum.trim();
        checkArgument(isValidAlphanum(trimmedAlphanum, canBeBlank), MESSAGE_CONSTRAINTS);
        this.alphaNum = trimmedAlphanum;
    }

    public String getAlphaNum() {
        return alphaNum;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    protected static boolean isValidAlphanum(String test, boolean canBeBlank) {
        String trimmedTest = test.trim();
        return canBeBlank
                ? trimmedTest.isEmpty() || trimmedTest.matches(VALIDATION_REGEX)
                : trimmedTest.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return alphaNum;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return Objects.hash(alphaNum);
    }
}
