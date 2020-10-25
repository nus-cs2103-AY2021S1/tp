package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.address.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;


public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String EMPTY_KEYWORD_LIST_MESSAGE = "Keyword list cannot be empty! Here are some examples\n"
        + "Example 1: " + COMMAND_WORD + " k/canvas cases\n"
        + "Example 2: " + COMMAND_WORD + " k/canvas c/expense\n"
        + "Example 3: " + COMMAND_WORD + " k/cases c/revenue\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all entries whose descriptions contain any of "
        + "the specified keywords (case-insensitive) and displays them as lists with index numbers.\n"
        + "Parameters: find k/KEYWORD [MORE_KEYWORDS]...\n"
        + "Example 1: " + COMMAND_WORD + " k/canvas cases\n"
        + "Example 2: " + COMMAND_WORD + " k/canvas c/expense\n"
        + "Example 3: " + COMMAND_WORD + " k/cases c/revenue\n";

    private final ExpenseDescriptionContainsKeywordsPredicate expensePredicate;
    private final RevenueDescriptionContainsKeywordsPredicate revenuePredicate;

    /**
     * Creates a FindCommand with a given predicate to find revenues with the given keywords.
     */
    public FindCommand(RevenueDescriptionContainsKeywordsPredicate predicate) {
        this.expensePredicate = null;
        this.revenuePredicate = predicate;
    }

    /**
     * Creates a FindCommand with a given predicate to find expenses with the given keywords.
     */
    public FindCommand(ExpenseDescriptionContainsKeywordsPredicate predicate) {
        this.expensePredicate = predicate;
        this.revenuePredicate = null;
    }

    /**
     * Creates a FindCommand with a given predicate to find expenses and revenues with the given keywords.
     */
    public FindCommand(ExpenseDescriptionContainsKeywordsPredicate expensePredicate,
                       RevenueDescriptionContainsKeywordsPredicate revenuePredicate) {
        this.expensePredicate = expensePredicate;
        this.revenuePredicate = revenuePredicate;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);
        assert (activeAccount != null);

        if (expensePredicate != null) {
            activeAccount.updateFilteredExpenseList(expensePredicate);
        }
        if (revenuePredicate != null) {
            activeAccount.updateFilteredRevenueList(revenuePredicate);
        }

        return CommandResultFactory.createDefaultCommandResult(Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW);
    }
}
