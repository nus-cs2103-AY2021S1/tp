package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;

/**
 * Finds and lists all attractions in the attractions list in TrackPad whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAttractionCommand extends Command {

    public static final String COMMAND_WORD = "find-attraction";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all attractions whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Zoo Orchard Gardens.";

    private final AttractionContainsKeywordsPredicate predicate;

    public FindAttractionCommand(AttractionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAttractionList(predicate);
        if (model.getFilteredAttractionList().size() == 1) {
            return new CommandResult(String.format(Messages.MESSAGE_ATTRACTION_LISTED_OVERVIEW,
                    model.getFilteredAttractionList().size()));
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_ATTRACTIONS_LISTED_OVERVIEW,
                    model.getFilteredAttractionList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAttractionCommand // instanceof handles nulls
                && predicate.equals(((FindAttractionCommand) other).predicate)); // state check
    }
}
