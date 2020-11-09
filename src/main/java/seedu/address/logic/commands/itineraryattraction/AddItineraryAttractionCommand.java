package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ATTRACTION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITINERARY_DAY;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.commons.core.Messages.MESSAGE_TIMING_CLASH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * Adds an attraction to the attractions list in TrackPad.
 */
public class AddItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "add-itinerary-attraction";
    public static final String MESSAGE_ADD_ATTRACTION_SUCCESS = "Added Attraction: %1$s to Itinerary: %1$s.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction identified by the index in "
            + "attraction list to the selected itinerary.\n "
            + "Parameters: INDEX must be a number between 0 and 2147483648 " + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_DAY_VISITING + "DAY_VISITING.\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1600 " + PREFIX_DAY_VISITING + "3.";

    private final Index index;
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;
    private final Index dayVisiting;

    /**
     * Creates an AddAttractionCommand to add the specified {@code Attraction}
     */
    public AddItineraryAttractionCommand(Index index, ItineraryTime startTime, ItineraryTime endTime,
                                         Index dayVisiting) {
        requireNonNull(index);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(dayVisiting);
        this.index = index;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayVisiting = dayVisiting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Itinerary itinerary;
        Attraction attraction;
        ItineraryAttraction ia;
        Day day;

        if (model.getCurrentItinerary() == null) {
            throw new CommandException(MESSAGE_ITINERARY_NOT_SELECTED);
        }

        itinerary = model.getCurrentItinerary();
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        attraction = lastShownList.get(index.getZeroBased());
        ia = new ItineraryAttraction(attraction, startTime, endTime);


        if (dayVisiting.getZeroBased() >= itinerary.getDays().size()) {
            throw new CommandException(MESSAGE_INVALID_ITINERARY_DAY);
        }
        day = itinerary.getDay(dayVisiting);

        if (day.contains(ia)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        // checks if there is a timing clash with an existing itinerary attraction
        if (day.hasTimingClash(ia)) {
            throw new CommandException(MESSAGE_TIMING_CLASH);
        }

        itinerary.addItineraryAttraction(ia, dayVisiting);
        return new CommandResult(String.format(MESSAGE_ADD_ATTRACTION_SUCCESS, ia),
                CommandResult.ToSwitchItineraryPanels.YES);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItineraryAttractionCommand // instanceof handles nulls
                && index.equals(((AddItineraryAttractionCommand) other).index)
                && startTime.equals(((AddItineraryAttractionCommand) other).startTime)
                && endTime.equals(((AddItineraryAttractionCommand) other).endTime)
                && dayVisiting.equals(((AddItineraryAttractionCommand) other).dayVisiting));
    }
}
