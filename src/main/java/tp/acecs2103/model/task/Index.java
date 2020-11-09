package tp.acecs2103.model.task;

import static java.util.Objects.requireNonNull;
import static tp.acecs2103.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidIndex(String)}
 */
public class Index {

    public static final String MESSAGE_CONSTRAINTS =
            "Your index format is wrong. Indexes should always start with 0, "
                    + "only contain numbers, be at least 4 digits long and should not be blank.\n"
                    + "E.g. 0101: first task of week 1\n"
                    + "01314: 14th task of week 13";

    /**
     *  The first character of the index must be a '0',
     *  followed by a number from '1' to '9' and a number from '0' to '9',
     *  with any number of '0' to '9' padded behind.
     */
    public static final String VALIDATION_REGEX = "\\A[0][1-9][0-9][0-9]+\\z";

    public final String value;

    /**
     * Constructs a {@code Index}.
     *
     * @param index A valid index.
     */
    public Index(String index) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), MESSAGE_CONSTRAINTS);
        value = index;
    }

    /**
     * Returns true if a given string is a valid index.
     */
    public static boolean isValidIndex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Index
                && value.equals(((Index) other).value));
    }
}
