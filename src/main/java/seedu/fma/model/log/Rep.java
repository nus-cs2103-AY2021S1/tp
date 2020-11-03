package seedu.fma.model.log;

/**
 * Represents a Log's Rep number in the log book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRep(int)}
 */
public class Rep {
    public static final String NUMBER_CONSTRAINTS = "Don't try to break our app! "
            + "Reps should be an integer within range 1-1000 inclusive.";
    public static final String MESSAGE_CONSTRAINTS =
            "Don't try to cheat us! Reps should be within range 1-1000 inclusive.";
    public final int value;

    /**
     * Constructs a {@code Rep}.
     *
     * @param reps The number of reps of exercise.
     */
    public Rep(int reps) {
        value = reps;
    }

    /**
     * Returns true if a given int is within range 1-1000 inclusive
     */
    public static boolean isValidRep(int rep) {
        return 1 <= rep && rep <= 1000;
    }

    public int getReps() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rep // instanceof handles nulls
                && value == (((Rep) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

}
