package seedu.homerce.logic.commands.expense;

import static java.util.Objects.requireNonNull;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.expensepanel.ExpenseListPanel;

/**
 * Sorts all expenses in Homerce to the user.
 */
public class SortExpenseCommand extends Command {

    public static final String COMMAND_WORD = "sortexp";

    public static final String MESSAGE_SUCCESS = "Sorted all expenses";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the expense based on value.\n"
            + "Parameters: ORDER\n"
            + "Example: " + COMMAND_WORD + " asc";

    public final boolean isAscending;

    /**
     * Constructs a SortExpenseCommand.
     * @param isAscending
     */
    public SortExpenseCommand(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {

        requireNonNull(model);
        model.getExpenseTracker().sortExpenseList(isAscending);
        return new CommandResult(MESSAGE_SUCCESS, ExpenseListPanel.TAB_NAME);
    }
}

