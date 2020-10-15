package quickcache.model.flashcard;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import quickcache.commons.core.index.Index;
import quickcache.commons.util.AppUtil;

/**
 * Represents a numerical option.
 * Can be transposed into an {@code Index} to index
 * the choice in a multiple choice question.
 */
public class Option {

    public static final String MESSAGE_CONSTRAINTS = "Option should only be numeric, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[0-9]+$";

    public final String value;


    /**
     * A constructor to create an Option.
     *
     * @param option string.
     */
    public Option(String option) {
        requireNonNull(option);
        AppUtil.checkArgument(isValidOption(option), MESSAGE_CONSTRAINTS);
        this.value = option;
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
        return Objects.equals(value, option1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String toString() {
        return "Option: " + value;
    }

    /**
     * Transposes the option given into an {@code Index}.
     *
     * @return option in {@code Index}.
     */
    public Index getIndex() {
        return Index.fromOneBased(Integer.parseInt(value));
    }
}
