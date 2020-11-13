package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String DESCRIPTION = "Undoes the previous action.";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";
    public static final String MESSAGE_SUCCESS = "Undid previous action.";
    public static final String MESSAGE_FAILURE = "No action to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canUndo()) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UndoCommand;
    }
}
