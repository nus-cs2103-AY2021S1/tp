package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.itinerary.ItineraryContainsKeywordsPredicate;

/**
 * Finds and lists all itineraries in the itineraries list in TrackPad whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindItineraryCommand extends Command {

    public static final String COMMAND_WORD = "find-itinerary";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all itineraries whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " Korea";

    private final ItineraryContainsKeywordsPredicate predicate;

    public FindItineraryCommand(ItineraryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItineraryList(predicate);
        model.setCurrentItinerary(null);
        if (model.getFilteredItineraryList().size() == 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ITINERARY_LISTED_OVERVIEW, model.getFilteredItineraryList().size()),
                    CommandResult.ToSwitchItineraryPanels.NO);
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_ITINERARIES_LISTED_OVERVIEW,
                    model.getFilteredItineraryList().size()),
                    CommandResult.ToSwitchItineraryPanels.NO);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindItineraryCommand // instanceof handles nulls
            && predicate.equals(((FindItineraryCommand) other).predicate)); // state check
    }
}
