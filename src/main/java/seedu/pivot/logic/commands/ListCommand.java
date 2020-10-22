package seedu.pivot.logic.commands;

/**
 * Lists all cases in PIVOT to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all items of a specified type.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'case'\n"
            + "Example: " + COMMAND_WORD + " case";

}
