package jimmy.mcgymmy.logic.commands;

import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String SHORT_DESCRIPTION = "Undo the last change to the food list.";

    public static final String MESSAGE_UNDO_SUCCESS = "Successfully undo the last change in the food list.";
    public static final String MESSAGE_NOT_UNDOABLE = "Cannot undo anymore.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canUndo()) {
            return new CommandResult(MESSAGE_NOT_UNDOABLE);
        }
        model.undo();
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
