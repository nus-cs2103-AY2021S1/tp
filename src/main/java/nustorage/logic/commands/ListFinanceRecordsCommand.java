package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.model.Model.PREDICATE_SHOW_ALL_FINANCE;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;

/**
 * List all finance records in the address book to the user.
 */
public class ListFinanceRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list_finance";

    public static final String MESSAGE_SUCCESS = "Listed all finance records!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredFinanceList(PREDICATE_SHOW_ALL_FINANCE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
