package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;


/**
 * Deletes an attraction identified using it's displayed index from the attractions in current itinerary.
 */
public class DeleteItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "delete-itinerary-attraction";
    public static final String MESSAGE_DELETE_ATTRACTION_SUCCESS = "Deleted attraction: %1$s.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the attraction identified by the index of"
            + "attraction in the selected itinerary.\nParameters: INDEX must be a number between 0 and 2147483648 "
            + PREFIX_DAY_VISITING + "DAY VISITING .\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DAY_VISITING + "2.";


    private final Index index;
    private final Index dayVisiting;

    /**
     * @param index       of the attraction in the selected itinerary
     * @param dayVisiting the attraction
     */
    public DeleteItineraryAttractionCommand(Index index, Index dayVisiting) {
        this.index = index;
        this.dayVisiting = dayVisiting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Itinerary itinerary;
        ItineraryAttraction itineraryAttractionToDelete;
        Day day;


        if (model.getCurrentItinerary() == null) {
            throw new CommandException(MESSAGE_ITINERARY_NOT_SELECTED);
        }

        itinerary = model.getCurrentItinerary();

        if (dayVisiting.getZeroBased() >= itinerary.getDays().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DAY);
        }
        day = model.getCurrentItinerary().getDay(dayVisiting);

        List<ItineraryAttraction> itineraryAttractions = day.getItineraryAttractions();

        if (index.getZeroBased() >= itineraryAttractions.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        itineraryAttractionToDelete = itineraryAttractions.get(index.getZeroBased());

        itinerary.deleteItineraryAttraction(index, dayVisiting);

        return new CommandResult(String.format(MESSAGE_DELETE_ATTRACTION_SUCCESS, itineraryAttractionToDelete),
                CommandResult.ToSwitchItineraryPanels.YES);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteItineraryAttractionCommand)) {
            return false;
        }
        DeleteItineraryAttractionCommand delIaCommand = (DeleteItineraryAttractionCommand) other;

        return index.equals(delIaCommand.index) && dayVisiting.equals(delIaCommand.dayVisiting);
    }
}
