package seedu.pivot.logic.commands;

/**
 * Lists all cases in PIVOT to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an item of a specified type.\n"
            + "Parameters: TYPE\n"
            + "Example: " + COMMAND_WORD + " case";

}
