package seedu.address.logic.commands.meeting;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.ModelMeeting;

/**
 * Represents a meeting command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    public static final String COMMAND_WORD = "meeting";

    /**
     * Executes the command and returns the result message.
     *
     * @param modelMeeting {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(ModelMeeting modelMeeting) throws CommandException;
}
