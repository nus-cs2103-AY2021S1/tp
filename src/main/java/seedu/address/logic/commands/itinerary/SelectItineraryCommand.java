package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Itinerary;

/**
 * Selects an itinerary in the itinerary list in TrackPad.
 */
public class SelectItineraryCommand extends Command {

    public static final String COMMAND_WORD = "select-itinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Selects the itinerary identified by the index number used in the displayed itinerary list.\n"
        + "Parameters: INDEX must be a number between 0 and 2147483648.\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_ITINERARY_SUCCESS = "Selected itinerary: %1$s.";

    private final Index targetIndex;

    public SelectItineraryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Itinerary> lastShownList = model.getFilteredItineraryList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
        }
        Itinerary itineraryToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.setCurrentItinerary(itineraryToSelect);
        return new CommandResult(String.format(MESSAGE_SELECT_ITINERARY_SUCCESS, itineraryToSelect),
                CommandResult.ToSwitchItineraryPanels.YES);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SelectItineraryCommand // instanceof handles nulls
            && targetIndex.equals(((SelectItineraryCommand) other).targetIndex)); // state check
    }
}
