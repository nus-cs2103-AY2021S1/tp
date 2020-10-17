package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_ACKNOWLEDGEMENT = "Redone state";

    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        for (Model model : models) {
            model.redo();
        }
        return new CommandResult(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }
}
