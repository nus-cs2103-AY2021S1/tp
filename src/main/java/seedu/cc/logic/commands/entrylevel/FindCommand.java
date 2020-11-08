package seedu.cc.logic.commands.entrylevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;

import seedu.cc.commons.core.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.cc.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String EMPTY_KEYWORD_LIST_MESSAGE = "Keyword list cannot be empty! Here are some examples\n"
        + "Parameters: " + PREFIX_KEYWORDS + "KEYWORDS " + "[" + PREFIX_CATEGORY + "CATEGORY" + "]" + "\n"
        + "Example 1: " + COMMAND_WORD + " k/canvas cases\n"
        + "Example 2: " + COMMAND_WORD + " k/canvas c/expense\n"
        + "Example 3: " + COMMAND_WORD + " k/cases c/revenue\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all entries whose descriptions contain any of "
        + "the specified keywords (case-insensitive) and displays them as lists with index numbers.\n"
        + "Parameters: find k/KEYWORD [MORE_KEYWORDS]...\n"
        + "Example 1: " + COMMAND_WORD + " k/canvas cases\n"
        + "Example 2: " + COMMAND_WORD + " k/canvas c/expense\n"
        + "Example 3: " + COMMAND_WORD + " k/cases c/revenue\n";
    public static final String PREFIXES = PREFIX_KEYWORDS + "KEYWORDS\n" + "["
            + PREFIX_CATEGORY + "CATEGORY" + "]" + "\n";
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
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        assert (expensePredicate != null || revenuePredicate != null);

        if (expensePredicate != null) {
            activeAccount.updateFilteredExpenseList(expensePredicate);
        }
        if (revenuePredicate != null) {
            activeAccount.updateFilteredRevenueList(revenuePredicate);
        }

        boolean isNoExpenseFoundWithOnlyExpensePredicate =
            activeAccount.getFilteredExpenseList().size() == 0 && expensePredicate != null && revenuePredicate == null;
        boolean isNoRevenueFoundWithOnlyRevenuePredicate =
            activeAccount.getFilteredRevenueList().size() == 0 && revenuePredicate != null && expensePredicate == null;
        boolean isNoEntryFound = activeAccount.getFilteredExpenseList().size() == 0
                            && activeAccount.getFilteredRevenueList().size() == 0;

        if (isNoEntryFound || isNoExpenseFoundWithOnlyExpensePredicate || isNoRevenueFoundWithOnlyRevenuePredicate) {
            return CommandResultFactory.createDefaultCommandResult(Messages.MESSAGE_EMPTY_FILTERED_LIST);
        } else {
            return CommandResultFactory.createDefaultCommandResult(Messages.MESSAGE_ENTRIES_UPDATED);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        if (expensePredicate == null || revenuePredicate == null) {
            if (expensePredicate != null) {
                return expensePredicate.equals(otherFindCommand.expensePredicate);
            } else if (revenuePredicate != null) {
                return revenuePredicate.equals(otherFindCommand.revenuePredicate);
            } else {
                return true; // if both predicates are null
            }
        } else {
            return revenuePredicate.equals(otherFindCommand.revenuePredicate)
                && expensePredicate.equals(otherFindCommand.expensePredicate);
        }
    }
}
