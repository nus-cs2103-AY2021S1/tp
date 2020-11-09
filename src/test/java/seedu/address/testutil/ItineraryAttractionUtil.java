package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.AddItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Attraction.
 */
public class ItineraryAttractionUtil {

    /**
     * Returns an add command string for adding the {@code attraction}.
     */
    public static String getAddItineraryAttractionCommand(Index index, Index day, ItineraryAttraction attraction) {
        return AddItineraryAttractionCommand.COMMAND_WORD + " " + index.getOneBased() + " "
                + getItineraryAttractionDetails(attraction) + PREFIX_DAY_VISITING + day.getOneBased();
    }

    /**
     * Returns the part of command string for the given {@code attraction}'s details.
     */
    private static String getItineraryAttractionDetails(ItineraryAttraction attraction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + attraction.getName().fullName + " ");
        sb.append(PREFIX_PHONE + attraction.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + attraction.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + attraction.getAddress().value + " ");
        sb.append(PREFIX_DESCRIPTION + attraction.getDescription().value + " ");
        sb.append(PREFIX_LOCATION + attraction.getLocation().value + " ");
        sb.append(PREFIX_OPENING_HOURS + attraction.getOpeningHours().value + " ");
        sb.append(PREFIX_PRICE_RANGE + attraction.getPriceRange().value + " ");
        sb.append(PREFIX_RATING + attraction.getRating().value + " ");
        sb.append(PREFIX_VISITED + attraction.getVisited().value + " ");
        sb.append(PREFIX_START_TIME + attraction.getStartTime().toString() + " ");
        sb.append(PREFIX_END_TIME + attraction.getEndTime().toString() + " ");
        attraction.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    public static String getEditItineraryAttractionCommand(Index index, Index day,
                                                           EditItineraryAttractionDescriptor descriptor) {
        return EditItineraryAttractionCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_DAY_VISITING
                + day.getOneBased() + " " + getItineraryEditAttractionDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of command string for the given {@code EditAttractionDescriptor}'s details.
     */
    private static String getItineraryEditAttractionDescriptorDetails(EditItineraryAttractionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getDescription().ifPresent(description ->
                sb.append(PREFIX_DESCRIPTION).append(description.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getOpeningHours().ifPresent(openingHours ->
                sb.append(PREFIX_OPENING_HOURS).append(openingHours.value).append(" "));
        descriptor.getPriceRange().ifPresent(priceRange ->
                sb.append(PREFIX_PRICE_RANGE).append(priceRange.value).append(" "));
        descriptor.getRating().ifPresent(rating -> sb.append(PREFIX_RATING).append(rating.value).append(" "));
        descriptor.getVisited().ifPresent(visited -> sb.append(PREFIX_VISITED).append(visited.value).append(" "));
        descriptor.getStartTime().ifPresent(startTime ->
                sb.append(PREFIX_START_TIME).append(startTime.toString()).append(" "));
        descriptor.getEndTime().ifPresent(endTime -> sb.append(PREFIX_END_TIME).append(endTime.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
