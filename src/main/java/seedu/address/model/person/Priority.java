package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's priority number in the client list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {


    public static final String MESSAGE_CONSTRAINTS =
            "Priority numbers should only contain characters L,M,H,U OR ulmh";
    public static final String VALIDATION_REGEX = "[ulmhULMH]|^undefined$|^high$|^medium$|^low$";
    public final String value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority keyword.
     *                 If there is no priority keyword, it would be U.
     */
    public Priority(String priority) {
        String tempValue;
        if (priority == null) {
            tempValue = matchToPriorityName("u");
        } else {
            checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
            tempValue = matchToPriorityName(priority);
        }
        value = tempValue;
    }

    /**
     * Returns true if a given string is a valid priority number.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Helper function to match the priorities to their respective names.
     *
     * @return the corresponding Priority name.
     */
    public static String matchToPriorityName(String priority) {
        switch (priority.toLowerCase()) {
        case "u":
        case "undefined":
            return PriorityType.UNDEFINED.name().toLowerCase();
        case "l":
        case "low":
            return PriorityType.LOW.name().toLowerCase();
        case "m":
        case "medium":
            return PriorityType.MEDIUM.name().toLowerCase();
        case "h":
        case "high":
            return PriorityType.HIGH.name().toLowerCase();
        default:
            return "x"; // priority type does not exist
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
