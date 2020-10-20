package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PropertyId extends Id {

    public static final String PREFIX_PROPERTY_ID = "P";
    public static final String VALIDATION_REGEX = "[P]\\p{Digit}+";
    public static final String MESSAGE_CONSTRAINTS = "Property Id should start with a \""
            + PREFIX_PROPERTY_ID
            + "\", followed by"
            + "some numbers.";
    public static final PropertyId DEFAULT_PROPERTY_ID = new PropertyId("P0");

    public PropertyId(int idNumber) {
        super(PREFIX_PROPERTY_ID, idNumber);
    }

    /**
     * Constructs a PropertyId from a string.
     *
     * @param id The Id in string format.
     */
    public PropertyId(String id) {
        super(id);
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
    }

    @Override
    public PropertyId increment() {
        return new PropertyId(idNumber + 1);
    }

    /** Returns true if a given string is a valid Id. */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
