package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Reverted to previous state";

    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        for (Model model : models) {
            model.undo();
        }
        return new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT);
    }
}
