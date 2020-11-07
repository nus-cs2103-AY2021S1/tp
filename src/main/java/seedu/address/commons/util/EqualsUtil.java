package seedu.address.commons.util;

/**
 * A helper class used for handling equality checking.
 */
public class EqualsUtil {

    /**
     * Returns true if both objects are equal, or if both objects are null.
     * Returns false if one is null and the other is non-null.
     * Returns false if both objects are not equal / different.
     *
     * @param obj The first object to test for equality.
     * @param otherObj The second object to test for equality.
     * @return A boolean indicating if the the objects are equal, with null cases included.
     */
    public static boolean equalsNullable(Object obj, Object otherObj) {
        if (obj == null) {
            return otherObj == null;
        } else {
            return obj.equals(otherObj);
        }
    }
}
