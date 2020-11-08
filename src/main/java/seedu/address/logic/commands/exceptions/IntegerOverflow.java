package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a command.
 */
public class IntegerOverflow extends CommandException {
    private static final String MESSAGE_INTEGER_OVERFLOW =
            "That's too much calories burnt within the day. Calo is meant for human. Not superhuman like you. \n"
                    + "Ensure your calories on that day is within human limits";
    public IntegerOverflow() {
        super(MESSAGE_INTEGER_OVERFLOW);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public IntegerOverflow(String message, Throwable cause) {
        super(message, cause);
    }
}
