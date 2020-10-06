package jimmy.mcgymmy.logic.parser.exceptions;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

public class ParameterConflictException extends IllegalValueException {
    private static final String defaultMessage = "The command cannot have multiple parameters with the same name.";

    public ParameterConflictException() {
        super(defaultMessage);
    }
}
