package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Calculates the profit in an account.
 */
public class GetProfitCommand extends Command {
    public static final String COMMAND_WORD = "profit";
    public static final String MESSAGE_SUCCESS = "Profit: ";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);

        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);

        double profits = activeAccount.getProfits();
        return CommandResultFactory.createDefaultCommandResult(MESSAGE_SUCCESS + String.format("%.2f", profits));
    }
}
