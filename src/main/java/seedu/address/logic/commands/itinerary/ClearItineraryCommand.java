package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;

/**
 * Clears all the itineraries in the itinerary list in TrackPad.
 */
public class ClearItineraryCommand extends Command {

    public static final String COMMAND_WORD = "clear-itinerary";
    public static final String MESSAGE_SUCCESS = "TrackPad's itineraries has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItineraryList(new ItineraryList());
        model.setCurrentItinerary(null);
        return new CommandResult((MESSAGE_SUCCESS), CommandResult.ToSwitchItineraryPanels.NO);
    }
}
