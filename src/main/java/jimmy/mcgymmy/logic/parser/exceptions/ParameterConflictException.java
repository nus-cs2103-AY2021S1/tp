package jimmy.mcgymmy.logic.parser.exceptions;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error when a user enters multiple parameters with the same name.
 */
public class ParameterConflictException extends IllegalValueException {
    private static final String defaultMessage = "The command cannot have multiple parameters with the same name.";

    public ParameterConflictException() {
        super(defaultMessage);
    }
}
