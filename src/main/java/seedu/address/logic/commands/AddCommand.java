package seedu.address.logic.commands;

/**
 * Adds a case to PIVOT.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // Todo: Refine the description of message usage
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an item of a specified type.\n"
            + "Parameters: TYPE REQUIRED_DETAILS\n"
            + "Example: " + COMMAND_WORD + " case";
}
