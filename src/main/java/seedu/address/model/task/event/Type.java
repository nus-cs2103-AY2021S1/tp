package seedu.address.model.task.event;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's type in the PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    /**
     * Types of task that a user can create.
     */
    public enum TypeOfTask {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline"),
        LESSON("lesson");
        private final String type;
        TypeOfTask(String type) {
            this.type = type;
        }
        @Override
        public String toString() {
            return this.type;
        }
    }
    public static final String ACCEPTED_TYPES = listAcceptedTypes();
    public static final String MESSAGE_CONSTRAINTS = String.format("Type can only be one of the following: %s.",
            ACCEPTED_TYPES);
    public static final String SEARCH_CONSTRAINTS = String.format("Search phrase for Type can only be one of "
            + "the following: %s.", ACCEPTED_TYPES);
    public final String value;

    /**
     * Constructs an {@code Type}.
     *
     * @param type A valid type.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid type.
     */
    public static boolean isValidType(String testType) {
        requireNonNull(testType);
        for (TypeOfTask types : TypeOfTask.values()) {
            String type = types.toString();
            if (type.equals(testType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all possible types of task a user can create.
     * @return a String containing all possible types of task a user can create.
     */
    private static String listAcceptedTypes() {
        ArrayList<String> listOfAcceptedTypes = new ArrayList<>();
        for (TypeOfTask types : TypeOfTask.values()) {
            listOfAcceptedTypes.add(types.toString());
        }
        return String.join(", ", listOfAcceptedTypes);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
