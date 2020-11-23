package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FilterEntityCommand;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.HairdresserNameContainsKeywordsPredicate;

/**
 * Filters and lists all hairdressers whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterHairdresserCommand extends FilterEntityCommand<HairdresserNameContainsKeywordsPredicate> {

    public static final String COMMAND_WORD = "filter_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all hairdressers whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    public FilterHairdresserCommand(HairdresserNameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredHairdresserList(predicate);

        if (model.getFilteredHairdresserList().size() == 1) {
            return new CommandResult(Messages.MESSAGE_HAIRDRESSER_LISTED_OVERVIEW_SINGULAR);
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_HAIRDRESSER_LISTED_OVERVIEW,
                    model.getFilteredHairdresserList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FilterHairdresserCommand // instanceof handles nulls
            && predicate.equals(((FilterHairdresserCommand) other).predicate)); // state check
    }
}
