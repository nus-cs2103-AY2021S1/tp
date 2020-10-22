package seedu.resireg.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAlias(String)}
 */
public class Alias {

    public static final String MESSAGE_CONSTRAINTS =
        "Aliases should only contain alphanumeric characters and spaces, and it should not be a command word";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String alias;

    /**
     * Constructs a {@code Alias}.
     *
     * @param alias A valid alias.
     */
    public Alias(String alias) {
        requireNonNull(alias);
        checkArgument(isValidAlias(alias), MESSAGE_CONSTRAINTS);
        this.alias = alias;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAlias(String test) {

        return test.matches(VALIDATION_REGEX) && !CommandWord.isValidCommandWord(test);
    }


    @Override
    public String toString() {
        return alias;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Alias // instanceof handles nulls
            && alias.equals(((Alias) other).alias)); // state check
    }

    @Override
    public int hashCode() {
        return alias.hashCode();
    }

}
