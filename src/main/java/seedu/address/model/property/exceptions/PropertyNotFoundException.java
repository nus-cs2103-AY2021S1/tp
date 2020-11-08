package seedu.address.model.property.exceptions;

/**
 * Signals that the operation is unable to find the specified property.
 */
public class PropertyNotFoundException extends RuntimeException {

    public static final String ERROR_MESSAGE = "No such property in the property list.";

}
