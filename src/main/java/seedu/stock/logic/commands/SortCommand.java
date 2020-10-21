package seedu.stock.logic.commands;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.model.Model;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the inventory list according to "
            + "the given argument. ";

    public static final String MESSAGE_SORT_STOCK_SUCCESS = "Sorted stock by %1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException, SourceCompanyNotFoundException {
        return null;
    }
}
