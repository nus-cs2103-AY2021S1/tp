package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.day.Day;

/**
 * A utility class for Day.
 */
public class DayUtil {

    /**
     * Returns an add command string for adding the {@code day}.
     */
    public static String getAddCommand(Day day) {
        return AddCommand.COMMAND_WORD + " " + getDayDetails(day);
    }

    /**
     * Returns the part of command string for the given {@code day}'s details.
     */
    public static String getDayDetails(Day day) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DATE + day.getDate().value + " ");
        sb.append(PREFIX_WEIGHT + day.getWeight().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditDayDescriptor}'s details.
     */
    public static String getEditDayDescriptorDetails(EditCommand.EditDayDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        return sb.toString();
    }
}
