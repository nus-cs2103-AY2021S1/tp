package seedu.address.logic.parser.exceptions;

/**
 * Represents an error which occurs during execution of a command.
 */
public class CaloriesOverflow extends ParseException {
    private static final String MESSAGE_INTEGER_OVERFLOW =
            "That's too much calories burnt within the day. Calo is meant for human. Not superhuman like you. \n"
                    + "Ensure your calories on that day is within human limits";

    public CaloriesOverflow() {
        super(MESSAGE_INTEGER_OVERFLOW);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CaloriesOverflow(String message, Throwable cause) {
        super(message, cause);
    }
}
