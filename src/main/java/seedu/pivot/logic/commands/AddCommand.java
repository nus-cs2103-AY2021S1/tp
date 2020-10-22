package seedu.pivot.logic.commands;

/**
 * Adds a case to PIVOT.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // Todo: Refine the description of message usage
    public static final String MESSAGE_USAGE_MAIN_PAGE = COMMAND_WORD
            + ": Adds an item of a specified type to PIVOT.\n"
            + "Parameters (for 'case' TYPE): TYPE t:TITLE\n"
            + "Example: " + COMMAND_WORD + " case t: Ang Mo Kio Murders";

    // Todo: Refine the description of message usage
    public static final String MESSAGE_USAGE_CASE_PAGE = COMMAND_WORD
            + ": Adds an item of a specified type to current case in PIVOT.\n"
            + "Parameters (for 'suspect','victim','witness' TYPE): TYPE n:NAME\n"
            + "Parameters (for 'docs' TYPE): TYPE n:NAME r:REFERENCE\n"
            + "Parameters (for 'desc' TYPE): TYPE d:DESC \n"
            + "Example: " + COMMAND_WORD + " suspect n:John\n"
            + "Example: " + COMMAND_WORD + " doc n:Evidence r:text1.txt"
            + "Example: " + COMMAND_WORD + " desc d:description of case";
}
