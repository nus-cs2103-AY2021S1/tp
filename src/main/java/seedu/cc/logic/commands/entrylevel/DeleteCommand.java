package seedu.cc.logic.commands.entrylevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import javafx.collections.ObservableList;
import seedu.cc.commons.core.Messages;
import seedu.cc.commons.core.category.Category;
import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;


/**
 * Deletes an entry identified using it's displayed index from the revenue/expense list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Entry identified by the index number used in the displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer) c/CATEGORY\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CATEGORY + "revenue";

    public static final String PREFIXES = PREFIX_CATEGORY + "CATEGORY";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Entry deleted!\n(%1$s)";

    private final Index targetIndex;
    private final Category category;

    /**
     * Creates an DeleteCommand to delete the specified {@code Entry}
     */
    public DeleteCommand(Index targetIndex, Category category) {
        this.targetIndex = targetIndex;
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);

        ObservableList<Expense> expenseList = activeAccount.getFilteredExpenseList();
        ObservableList<Revenue> revenueList = activeAccount.getFilteredRevenueList();
        int index = targetIndex.getZeroBased();
        boolean isExpense = this.category.isExpense();
        boolean isRevenue = this.category.isRevenue();
        boolean isInvalidExpenseIndex = isExpense && (index >= expenseList.size());
        boolean isInvalidRevenueIndex = isRevenue && (index >= revenueList.size());

        if (isInvalidExpenseIndex || isInvalidRevenueIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        // Set previous state for undo before entry is deleted
        activeAccount.setPreviousState();
        Entry entry;
        if (isExpense) {
            Expense expenseToDelete = expenseList.get(index);
            activeAccount.deleteExpense(expenseToDelete);
            entry = expenseToDelete;
        } else {
            assert isRevenue;
            Revenue revenueToDelete = revenueList.get(index);
            activeAccount.deleteRevenue(revenueToDelete);
            entry = revenueToDelete;
        }

        model.setAccount(activeAccount.getAccount());
        return CommandResultFactory
            .createCommandResultForEntryListChangingCommand(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entry));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex))
                && category.equals(((DeleteCommand) other).category); // state check
    }
}
