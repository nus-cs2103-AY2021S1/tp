package seedu.homerce.logic.commands.revenue;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.model.Model.PREDICATE_SHOW_ALL_REVENUE;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;

/**
 * Lists all revenues in Homerce to the user.
 */
public class ListRevenueCommand extends Command {

    public static final String COMMAND_WORD = "listrev";

    public static final String MESSAGE_SUCCESS = "Listed all revenue";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.getRevenueTracker().sortDefaultRevenueList();
        model.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
        return new CommandResult(MESSAGE_SUCCESS, RevenueListPanel.TAB_NAME);
    }
}
