package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;

import java.util.Set;

import seedu.address.logic.commands.attraction.AddAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Attraction.
 */
public class AttractionUtil {

    /**
     * Returns an add command string for adding the {@code attraction}.
     */
    public static String getAddAttractionCommand(Attraction attraction) {
        return AddAttractionCommand.COMMAND_WORD + " " + getAttractionDetails(attraction);
    }

    /**
     * Returns the part of command string for the given {@code attraction}'s details.
     */
    public static String getAttractionDetails(Attraction attraction) {
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
        attraction.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAttractionDescriptor}'s details.
     */
    public static String getEditAttractionDescriptorDetails(EditAttractionDescriptor descriptor) {
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
