package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class DeleteCommand extends Command {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String result = deleteFromModel(model);

        return new CommandResult(result);
    }

    public abstract String deleteFromModel(Model model) throws CommandException;
}
