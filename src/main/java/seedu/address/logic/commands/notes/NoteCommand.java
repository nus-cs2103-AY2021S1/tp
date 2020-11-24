package seedu.address.logic.commands.notes;

import seedu.address.logic.commands.Command;

/**
 * Represents a note command with hidden internal logic and the ability to be executed.
 */
public abstract class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, edits or deletes a note "
            + "in Reeve.\n\n"
            + "SUPPORTED COMMANDS: add, edit, delete";

}
