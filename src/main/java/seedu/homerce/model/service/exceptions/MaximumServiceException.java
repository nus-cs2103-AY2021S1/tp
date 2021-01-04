package seedu.homerce.model.service.exceptions;

/**
 * Indicates that homerce has reached the maximum number of Services that it can store - 1000.
 */
public class MaximumServiceException extends RuntimeException {
    public MaximumServiceException(String message) {
        super(message);
    }
}
