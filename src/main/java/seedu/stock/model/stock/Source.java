package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Stocks's source in the stock book.
 * Guarantees: immutable;
 */
public class Source {
    // Original class is email. Can be reused to store the email of the company next time.
    //    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    //    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
    //            + "and adhere to the following constraints:\n"
    //            + "1. The local-part should only contain alphanumeric characters
    //    //      and these special characters, excluding "
    //            + "the parentheses, (" + SPECIAL_CHARACTERS + ") .\n"
    //            + "2. This is followed by a '@' and then a domain name. "
    //            + "The domain name must:\n"
    //            + "    - be at least 2 characters long\n"
    //            + "    - start and end with alphanumeric characters\n"
    //            + "    - consist of alphanumeric characters,
    //    //      a period or a hyphen for the characters in between, if any.";
    //    // alphanumeric and special characters
    //    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    //    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]";
    //    // alphanumeric characters except underscore
    //    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
    //    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    //    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
    //    + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;
    // Condition can be refined later.
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the source must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param source A valid source.
     */
    public Source(String source) {
        requireNonNull(source);
        value = source;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid source.
     */
    public static boolean isValidSource(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Source // instanceof handles nulls
                && value.equals(((Source) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isSameSource(Source source) {
        return this.value.equals(source.value);
    }

}
