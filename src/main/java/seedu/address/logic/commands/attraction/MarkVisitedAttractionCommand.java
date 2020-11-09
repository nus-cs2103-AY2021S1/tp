package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;

/**
 * Marks an existing attraction in the attractions list in TrackPad as visited.
 */
public class MarkVisitedAttractionCommand extends Command {

    public static final String COMMAND_WORD = "markVisited-attraction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attraction identified "
            + "by the index number used in the displayed attraction list as visited.\n"
            + "Parameters: INDEX must be a number between 0 and 2147483648.\n"
            + "Example: " + COMMAND_WORD + " 1.";

    public static final String MESSAGE_MARKVISITED_ATTRACTION_SUCCESS = "Attraction marked as visited: %1$s.";

    public static final String MESSAGE_ATTRACTION_ALREADY_MARKVISITED = "This attraction was already marked as "
            + "visited.";

    private final Index index;
    /**
     * @param index of the attraction in the filtered attraction list to mark as visited
//     * @param markVisitedAttractionDescriptor details to edit the attraction with
     */
    public MarkVisitedAttractionCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToMarkVisited = lastShownList.get(index.getZeroBased());

        if (attractionToMarkVisited.getVisited().equals(new Visited("True"))) {
            throw new CommandException(MESSAGE_ATTRACTION_ALREADY_MARKVISITED);
        }
        Attraction markedVisitedAttraction = createMarkVisitedAttraction(attractionToMarkVisited);

        model.setAttraction(attractionToMarkVisited, markedVisitedAttraction);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(String.format(MESSAGE_MARKVISITED_ATTRACTION_SUCCESS, markedVisitedAttraction));
    }

    /**
     * Creates and returns a {@code Attraction} with the details of {@code attractionToEdit}
     * edited with {@code editAttractionDescriptor}.
     */
    private static Attraction createMarkVisitedAttraction(Attraction attractionToMarkVisited) {
        assert attractionToMarkVisited != null;

        Name unchangedName = attractionToMarkVisited.getName();
        Phone unchangedPhone = attractionToMarkVisited.getPhone();
        Email unchangedEmail = attractionToMarkVisited.getEmail();
        Address unchangedAddress = attractionToMarkVisited.getAddress();
        Description unchangedDescription = attractionToMarkVisited.getDescription();
        Location unchangedLocation = attractionToMarkVisited.getLocation();
        OpeningHours unchangedOpeningHours = attractionToMarkVisited.getOpeningHours();
        PriceRange unchangedPriceRange = attractionToMarkVisited.getPriceRange();
        Rating unchangedRating = attractionToMarkVisited.getRating();
        Visited updatedVisited = new Visited("TRUE");
        Set<Tag> unchangedTags = attractionToMarkVisited.getTags();

        return new Attraction(unchangedName, unchangedPhone, unchangedEmail, unchangedAddress,
                unchangedDescription, unchangedLocation, unchangedOpeningHours, unchangedPriceRange,
                unchangedRating, updatedVisited, unchangedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkVisitedAttractionCommand)) {
            return false;
        }

        // state check
        MarkVisitedAttractionCommand e = (MarkVisitedAttractionCommand) other;
        return index.equals(e.index);
    }

}
