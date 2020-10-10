package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Id to uniquely identify elements in a list.
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Ids should start with a representative character, followed by"
        + "some numbers.";

    public static final String VALIDATION_REGEX = "[BSP]\\p{Digit}+";

    private final String prefix;
    private final int idNumber;

    /**
     * Constructs the Id with a prefix and id number.
     *
     * @param prefix The prefix representing the type of object.
     * @param idNumber The id number.
     */
    public Id(String prefix, int idNumber) {
        this.prefix = prefix;
        this.idNumber = idNumber;
    }

    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.prefix = String.valueOf(id.charAt(0));
        this.idNumber = Integer.parseInt(id.substring(1));
    }

    /**
     * Gets the next id with the same prefix.
     *
     * @return The next id.
     */
    public Id increment() {
        return new Id(prefix, idNumber + 1);
    }

    /** Returns true if a given string is a valid Id. */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return prefix + idNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof Id
                && this.prefix.equals(((Id) obj).prefix)
                && this.idNumber == ((Id) obj).idNumber);
    }
}
