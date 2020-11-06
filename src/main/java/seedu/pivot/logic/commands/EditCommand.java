package seedu.pivot.logic.commands;

/**
 * Represents an Edit command for editing items of different types to PIVOT.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the specified type "
            + "in the current case.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Format: '" + COMMAND_WORD + " TYPE PARAMETERS'\n\n"
            + "TYPE 'title'\n"
            + "Parameters: [t:TITLE]\n"
            + "Example: " + COMMAND_WORD + " title t:Triple Kovan Murders\n\n"
            + "TYPE 'status'\n"
            + "Parameters: [s:STATUS]\n"
            + "Example: " + COMMAND_WORD + " status s:closed\n\n"
            + "TYPE 'doc'\n"
            + "Parameters: INDEX [n:NAME] [r:REFERENCE]\n"
            + "Example: " + COMMAND_WORD + " doc 1 n:meeting notes\n\n"
            + "TYPE 'suspect','victim','witness'\n"
            + "Parameters: INDEX [n:NAME] [sex:SEX] [p:PHONE] [e:EMAIL] [a:ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " suspect 1 e:newEmail@mail.com a:new road crescent\n\n";

}
