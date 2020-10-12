package seedu.stock.logic.commands;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;

public class SuggestionCommand extends Command {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("This is a suggestion feature");
    }
}
