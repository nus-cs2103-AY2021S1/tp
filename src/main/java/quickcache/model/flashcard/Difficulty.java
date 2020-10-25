package quickcache.model.flashcard;

import static java.util.Objects.requireNonNull;

import quickcache.commons.util.AppUtil;

public class Difficulty {

    public static final String MESSAGE_CONSTRAINTS = "Difficulty names should only be "
            + "LOW, MEDIUM, or HIGH. "
            + "No spaces allowed. "
            + "Difficulty will be set to UNSPECIFIED if input is empty or UNSPECIFIED.";

    public final String value;

    /**
     * Constructs a {@code Difficulty}.
     *
     * @param value A valid difficulty.
     */
    public Difficulty(String value) {
        requireNonNull(value);
        AppUtil.checkArgument(isValidDifficultyName(value), MESSAGE_CONSTRAINTS);
        boolean isValidInput = isValidDifficultyName(value);
        assert isValidInput;
        this.value = value.toUpperCase();
    }

    /**
     * Constructs a {@code Difficulty}.
      */
    public Difficulty() {
        this.value = Difficulties.UNSPECIFIED.name();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDifficultyName(String test) {
        String uppercaseTest = test.toUpperCase();
        for (Difficulties difficultyLevel : Difficulties.values()) {
            if (difficultyLevel.name().equals(uppercaseTest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Difficulty // instanceof handles nulls
                && value.equals(((Difficulty) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }

    public String getValue() {
        return value;
    }
}
