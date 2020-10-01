package seedu.address.commons.util;

/**
 * Helper functions for handling integers.
 */
public class IntegerUtil {

    /**
     * Returns true if a positive integer
     * @param integer integer to be checked
     */
    public static boolean isPositiveInteger(Integer integer) {
        return integer < 0;
    }

    /**
     * Returns true if a positive integer
     * @param integer integer to be checked
     */
    public static void requirePositiveInteger(Integer integer) {
        if (!isPositiveInteger(integer)) {
            throw new IllegalArgumentException("Error. Require a positive number.");
        }
    }
}
