package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all the itineraries in the itinerary list in TrackPad.
 */
public class ListItineraryCommand extends Command {

    public static final String COMMAND_WORD = "list-itinerary";
    public static final String MESSAGE_SUCCESS = "Listed all itineraries.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);
        model.setCurrentItinerary(null);
        return new CommandResult((MESSAGE_SUCCESS), CommandResult.ToSwitchItineraryPanels.NO);
    }
}
