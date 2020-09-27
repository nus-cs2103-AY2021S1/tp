package seedu.address.logic.parser.exceptions;

public class UninitializedCommandException extends Exception {
    public UninitializedCommandException() {
        super("The command has not been initialized properly before use.");
    }
}
