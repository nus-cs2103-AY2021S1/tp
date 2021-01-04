package seedu.homerce.logic.commands.revenue;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;

import java.util.function.Predicate;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;

/**
 * Finds and lists all revenue in homerce whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRevenueCommand extends Command {

    public static final String COMMAND_WORD = "findrev";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all revenue by either their"
            + " date or service code, and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_SERVICE_SERVICE_CODE + "SERVICE_CODE " + " or " + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SERVICE_SERVICE_CODE + "SC000 " + "or "
            + COMMAND_WORD + " " + PREFIX_DATE + "22-10-2020";

    private final Predicate<Revenue> predicate;

    public FindRevenueCommand(Predicate<Revenue> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredRevenueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_REVENUE_LISTED_OVERVIEW, model.getFilteredRevenueList().size()),
                RevenueListPanel.TAB_NAME
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRevenueCommand // instanceof handles nulls
                && predicate.equals(((FindRevenueCommand) other).predicate)); // state check
    }
}
