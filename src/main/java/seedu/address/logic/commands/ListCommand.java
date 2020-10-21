package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

public class ListCommand extends Command {


    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all entries";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireNonNull(model);
        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
