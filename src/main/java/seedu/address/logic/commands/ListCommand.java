package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Lists all entries.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all entries";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
        return CommandResultFactory.createDefaultCommandResult(MESSAGE_SUCCESS);
    }
}
