package seedu.address.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.commons.core.index.Index;

/**
 * Represents a numerical option.
 * Can be transposed into an {@code Index} to index
 * the choice in a multiple choice question.
 */
public class Option {

    public static final String MESSAGE_CONSTRAINTS = "Option should only be numeric, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[0-9]+$";

    public final String option;


    /**
     * A constructor to create an Option.
     * @param option string.
     */
    public Option(String option) {
        requireNonNull(option);
        checkArgument(isValidOption(option), MESSAGE_CONSTRAINTS);
        this.option = option;
    }

    public static boolean isValidOption(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Option option1 = (Option) other;
        return Objects.equals(option, option1.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(option);
    }

    public String toString() {
        return "Option: " + option;
    }

    /**
     * Transposes the option given into an {@code Index}.
     * @return option in {@code Index}.
     */
    public Index getIndex() {
        return Index.fromOneBased(Integer.parseInt(option));
    }
}
