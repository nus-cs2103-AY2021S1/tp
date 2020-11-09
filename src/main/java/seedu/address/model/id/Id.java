package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
* Represents an Id to uniquely identify elements in a list.
*/
public abstract class Id {

    public static final String MESSAGE_CONSTRAINTS = "Ids should start with a representative character, followed by"
            + "some numbers.";

    public static final String VALIDATION_REGEX = "[BSP]\\p{Digit}+";

    protected final String prefix;
    protected final int idNumber;

    /**
    * Constructs the Id with a prefix and id number.
    * @param prefix The prefix representing the type of object.
    * @param idNumber The id number.
    */
    protected Id(String prefix, int idNumber) {
        this.prefix = prefix;
        this.idNumber = idNumber;
    }

    /**
     * Constructs the Id from a String.
     *
     * @param id The id in string format.
     */
    protected Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.prefix = String.valueOf(id.charAt(0));
        this.idNumber = Integer.parseInt(id.substring(1));
    }

    /**
     * retrieves the id number from an id object
     * @return id of a certain object
     */
    public int getId() {
        return this.idNumber;
    }

    /**
     * Gets the next id with the same prefix.
     *
     * @return The next id.
     */
    public abstract Id increment();

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
