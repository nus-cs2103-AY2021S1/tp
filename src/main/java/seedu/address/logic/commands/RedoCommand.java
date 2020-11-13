package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String DESCRIPTION = "Redoes the undid action.";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";
    public static final String MESSAGE_SUCCESS = "Redid action.";
    public static final String MESSAGE_FAILURE = "No action to redo.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canRedo()) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        model.redo();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RedoCommand;
    }
}
