package seedu.address.commons.util;

/**
 * Helper functions for handling integers.
 */
public class IntegerUtil {

    /**
     * Returns true if a integer more than zero.
     * @param integer integer to be checked
     */
    public static boolean isPositiveInteger(Integer integer) {
        return integer > 0;
    }

    /**
     * Throws IllegalArgumentException if the integer is lesser than 0 or equal 0.
     * @param integer integer to be checked
     */
    public static void requirePositiveInteger(Integer integer) {
        if (!isPositiveInteger(integer)) {
            throw new IllegalArgumentException("Error. Require a positive number.");
        }
    }
}
