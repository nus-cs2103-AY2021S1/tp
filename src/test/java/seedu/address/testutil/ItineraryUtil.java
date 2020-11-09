package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.model.itinerary.Itinerary;

/**
 * A utility class for Itinerary.
 */
public class ItineraryUtil {

    /**
     * Returns an add itinerary command string for adding the {@code itinerary}.
     */
    public static String getAddItineraryCommand(Itinerary itinerary) {
        return AddItineraryCommand.COMMAND_WORD + " " + getItineraryDetails(itinerary);
    }

    /**
     * Returns the part of command string for the given {@code itinerary}'s details.
     */
    public static String getItineraryDetails(Itinerary itinerary) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + itinerary.getName().fullName + " ");
        sb.append(PREFIX_DESCRIPTION + itinerary.getDescription().value + " ");
        sb.append(PREFIX_START_DATE + itinerary.getStartDate().toString() + " ");
        sb.append(PREFIX_END_DATE + itinerary.getEndDate().toString() + " ");
        sb.append(PREFIX_BUDGET + itinerary.getBudget().value + " ");
        return sb.toString();
    }

    /**
     * * Returns the part of command string for the given {@code EditItineraryDescriptor}'s details.
     */
    public static String getEditItineraryDescriptorDetails(EditItineraryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDescription().ifPresent(description ->
                sb.append(PREFIX_DESCRIPTION).append(description.value).append(" "));
        descriptor.getStartDate().ifPresent(startDate ->
                sb.append(PREFIX_START_DATE).append(startDate.toString()).append(" "));
        descriptor.getEndDate().ifPresent(endDate ->
                sb.append(PREFIX_END_DATE).append(endDate.toString()).append(" "));
        descriptor.getBudget().ifPresent(budget ->
                sb.append(PREFIX_BUDGET).append(budget.value).append(" "));
        return sb.toString();
    }
}
