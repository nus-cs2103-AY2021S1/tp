package seedu.fma.commons.util;

/**
 * Helper functions for handling integers.
 */
public class IntegerUtil {

    /**
     * Returns true iff integer is in range 1-1000 inclusive
     * @param integer value to be teseted
     * @return true iff integer is in range 1-1000 inclusive
     */
    public static boolean isInValidRange(int integer) {
        return integer >= 1 && integer <= 1000;
    }
}
