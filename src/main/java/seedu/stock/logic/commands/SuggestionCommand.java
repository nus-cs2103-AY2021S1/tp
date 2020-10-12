package seedu.stock.logic.commands;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;

public class SuggestionCommand extends Command {

    private String toBeDisplayed;

    public SuggestionCommand(String toBeDisplayed) {
        this.toBeDisplayed = toBeDisplayed;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(toBeDisplayed);
    }
}
