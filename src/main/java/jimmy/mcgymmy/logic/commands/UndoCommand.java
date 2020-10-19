package jimmy.mcgymmy.logic.commands;

import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String SHORT_DESCRIPTION = "Undo the last change to the food list.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("undo command called");
    }
}
