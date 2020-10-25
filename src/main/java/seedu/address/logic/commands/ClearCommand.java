package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.address.commons.core.category.Category;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;


/**
 * Clears all entries in the specified revenue/expense list of the account.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries in the specified entry (expense/revenue) list.\n"
            + "Parameters: c/CATEGORY\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + "revenue";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Cleared: %1$s" + "s";

    private final Category category;

    /**
     * Creates an ClearCommand to add the specified {@code Entry}
     */
    public ClearCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        assert(!isNull(model));

        boolean isExpense = this.category.isExpense();
        boolean isRevenue = this.category.isRevenue();

        activeAccount.setPreviousState();
        if (isExpense) {
            activeAccount.clearExpenses();
        } else {
            assert isRevenue;
            activeAccount.clearRevenues();
        }

        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);

        model.setAccount(activeAccount.getAccount());
        return CommandResultFactory
            .createCommandResultForEntryListChangingCommand(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, category));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand) // instanceof handles nulls
                && category.equals(((ClearCommand) other).category); // state check
    }

}
