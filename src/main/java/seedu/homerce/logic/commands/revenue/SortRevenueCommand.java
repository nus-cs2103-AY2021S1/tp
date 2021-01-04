package seedu.homerce.logic.commands.revenue;

import static java.util.Objects.requireNonNull;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;

/**
 * Sorts all revenues in Homerce to the user.
 */
public class SortRevenueCommand extends Command {

    public static final String COMMAND_WORD = "sortrev";

    public static final String MESSAGE_SUCCESS = "Sorted all revenues";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the revenue based on value.\n"
            + "Parameters: ORDER\n" + "Example: " + COMMAND_WORD + " asc" + " or " + COMMAND_WORD + " desc";

    private final boolean isAscending;

    /**
     * Constructs a SortRevenueCommand.
     *
     * @param isAscending to indicate the order of the sort.
     */
    public SortRevenueCommand(boolean isAscending) {
        this.isAscending = isAscending;
    }

    public boolean isAscending() {
        return isAscending;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.getRevenueTracker().sortRevenueList(isAscending);
        return new CommandResult(MESSAGE_SUCCESS, RevenueListPanel.TAB_NAME);
    }
}
