package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

/**
 * Adds a case to PIVOT.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // Todo: Refine the description of message usage
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all items of a specified type.\n"
            + "Parameters: TYPE REQUIRED_DETAILS\n"
            + "Example: " + COMMAND_WORD + " case";

    /**
     * Creates an AddCommand to add the specified {@code Case}
     */
    public AddCommand(Object obj) {
        requireNonNull(obj);
    }
}
