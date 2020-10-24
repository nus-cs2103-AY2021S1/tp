package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAllCommand extends ListCommand {

    public static final String LIST_WORD = "all";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStockList(PREDICATE_SHOW_ALL_PERSONS);
        model.sortFilteredStockList(SortUtil.generateGeneralComparator());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
