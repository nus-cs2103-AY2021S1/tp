package seedu.pivot.logic.commands;

/**
 * Lists all cases in PIVOT to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE_MAIN_PAGE = COMMAND_WORD
            + ": Lists all items of a specified type.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'case'\n"
            + "Example: " + COMMAND_WORD + " case\n\n"
            + "TYPE 'archive'\n"
            + "Example: " + COMMAND_WORD + " archive";

    public static final String MESSAGE_USAGE_CASE_PAGE = COMMAND_WORD
            + ": Lists all items of a specified type.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'doc'\n"
            + "Example: " + COMMAND_WORD + " doc\n\n"
            + "TYPE 'suspect'\n"
            + "Example: " + COMMAND_WORD + " suspect\n\n"
            + "TYPE 'victim'\n"
            + "Example: " + COMMAND_WORD + " victim\n\n"
            + "TYPE 'witness'\n"
            + "Example: " + COMMAND_WORD + " witness\n\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all items of a specified type at Main Page.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'case'\n"
            + "Example: " + COMMAND_WORD + " case\n\n"
            + "TYPE 'archive'\n"
            + "Example: " + COMMAND_WORD + " archive\n\n"
            + COMMAND_WORD + ": Lists all items of a specified type at Case Page.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'doc'\n"
            + "Example: " + COMMAND_WORD + " doc\n\n"
            + "TYPE 'suspect'\n"
            + "Example: " + COMMAND_WORD + " suspect\n\n"
            + "TYPE 'victim'\n"
            + "Example: " + COMMAND_WORD + " victim\n\n"
            + "TYPE 'witness'\n"
            + "Example: " + COMMAND_WORD + " witness\n\n";

}
