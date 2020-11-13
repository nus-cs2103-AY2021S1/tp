package seedu.internhunter.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Lists all company items in InternHunter to the user.
 */
public class ListCompanyCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_LIST_SUCCESS, COMPANY_NAME);

    /**
     * Executes the list company command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_ITEMS);
        model.setCompanyViewIndex(Index.fromOneBased(1)); // reset to zero for consistency
        return getCommandResult(model, MESSAGE_SUCCESS, TabName.COMPANY);
    }
}
