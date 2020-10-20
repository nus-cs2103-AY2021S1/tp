package quickcache.model.flashcard;

import static java.util.Objects.requireNonNull;

import quickcache.commons.util.AppUtil;

public class Difficulty {

    public static final String MESSAGE_CONSTRAINTS = "Difficulty names should only be "
            + "low, medium, or high. "
            + "No spaces allowed. Cannot be empty.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String difficulty;

    /**
     * Constructs a {@code Difficulty}.
     *
     * @param difficulty A valid difficulty.
     */
    public Difficulty(String difficulty) {
        requireNonNull(difficulty);
        AppUtil.checkArgument(isValidDifficultyName(difficulty), MESSAGE_CONSTRAINTS);
        this.difficulty = difficulty.toUpperCase();
    }

    /**
     * Constructs a {@code Difficulty}.
      */
    public Difficulty() {
        this.difficulty = "UNSPECIFIED";
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDifficultyName(String test) {

        if (test.matches(VALIDATION_REGEX)) {
            String uppercaseTest = test.toUpperCase();
            for (Difficulties difficultyLevel : Difficulties.values()) {
                if (difficultyLevel.name().equals(uppercaseTest)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Difficulty // instanceof handles nulls
                && difficulty.equals(((Difficulty) other).difficulty)); // state check
    }

    @Override
    public int hashCode() {
        return difficulty.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + difficulty + ']';
    }

    public String getName() {
        return difficulty;
    }
}
