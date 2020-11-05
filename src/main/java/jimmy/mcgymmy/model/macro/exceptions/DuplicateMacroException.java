package jimmy.mcgymmy.model.macro.exceptions;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

/**
 * Represents a Macro Exception when user keys in a Macro name that already exists.
 */
public class DuplicateMacroException extends IllegalValueException {
    private static final String defaultMessage = "This macro's name has already been taken.";

    public DuplicateMacroException() {
        super(defaultMessage);
    }
}
