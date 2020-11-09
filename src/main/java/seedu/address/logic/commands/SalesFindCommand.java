package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.InputContainsKeywordsPredicate;
import seedu.address.model.Model;

public class SalesFindCommand extends Command {

    public static final String COMMAND_WORD = "s-find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the sales of the drinks as entered. \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS] where KEYWORD refers to the drink abbreviation. \n"
            + "Example: " + COMMAND_WORD + " " + "BSBBT";

    private final InputContainsKeywordsPredicate predicate;

    public SalesFindCommand(InputContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert model != null;
        model.updateFilteredSalesRecordList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DRINKS_LISTED_OVERVIEW, model.getFilteredSalesRecordList().size()));
    }
}
