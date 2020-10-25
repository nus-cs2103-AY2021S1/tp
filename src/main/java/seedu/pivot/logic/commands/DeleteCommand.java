package seedu.pivot.logic.commands;

/**
 * Abstract class for deleting different types of items based off of index.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    //TODO: Consider abstracting this out further, find a way to represent any item type or show
    // of all different item types and the delete command usage
    public static final String MESSAGE_USAGE_MAIN_PAGE = COMMAND_WORD
            + ": Deletes the item of a specified type identified by the index number"
            + " used in the displayed list.\n"
            + "Format: '" + COMMAND_WORD + " TYPE PARAMETERS'\n\n"
            + "TYPE 'case'\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " case 1";

    public static final String MESSAGE_USAGE_CASE_PAGE = COMMAND_WORD
            + ": Deletes the specified type of item identified by the index number"
            + " used in the displayed list in current case.\n"
            + "Format: '" + COMMAND_WORD + " TYPE PARAMETERS'\n\n"
            + "TYPE 'suspect','victim','witness', 'doc'\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " suspect 1";

}
