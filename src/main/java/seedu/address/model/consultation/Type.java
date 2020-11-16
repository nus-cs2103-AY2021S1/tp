package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.consultation.ConsultationType.GROUP;
import static seedu.address.model.consultation.ConsultationType.PERSONAL;

/**
 * Represents the type of consultation in classes.
 */
public class Type {
    public static final String MESSAGE_CONSTRAINTS = "Accepted consultation type format: personal/group";

    public final ConsultationType type;

    /**
     * Constructs a {@code Type}.
     *
     * @param type A valid ConsultationType.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        this.type = assignType(type);
    }

    /**
     * Returns true if a given string is a valid consultation type.
     *
     * @param test Input type.
     * @return confirmation of valid type.
     */
    public static boolean isValidType(String test) {
        return test.equalsIgnoreCase(GROUP.toString()) || test.equalsIgnoreCase(PERSONAL.toString());
    }

    /**
     * Assigns a ConsultationType to the input.
     *
     * @param test Input type.
     * @return ConsultationType of input.
     */
    public static ConsultationType assignType(String test) {
        return test.equalsIgnoreCase(GROUP.toString()) ? GROUP : PERSONAL;
    }

    /**
     * Returns string representation of the consultation type..
     *
     * @return type in string.
     */
    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && type.equals(((Type) other).type)); // state check
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
