package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.command.add.AddCommand;
import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.model.activity.Activity;

public class ActivityUtil {
    /**
     * Returns an add command string for adding the {@code activity}.
     */
    public static String getAddCommand(Activity activity) {
        return AddCommand.COMMAND_WORD + " -activity " + getActivityDetails(activity);
    }

    /**
     * Returns the part of command string for the given {@code activity}'s details.
     */
    public static String getActivityDetails(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + activity.getName().getValue() + " ");
        sb.append(PREFIX_COST + activity.getCost().getValue() + " ");
        sb.append(PREFIX_LOCATION + activity.getLocation().getValue() + " ");
        sb.append(PREFIX_DATETIME + activity.getActivityDateTime().getDateTime() + " ");
        sb.append(PREFIX_IMPORTANCE + activity.getLevelOfImportance().getValue() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditactivityDescriptor}'s details.
     */
    public static String getEditActivityDescriptorDetails(EditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.getValue()).append(" "));
        descriptor.getCost().ifPresent(cost -> sb.append(PREFIX_COST).append(cost.getValue()).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.getValue())
                .append(" "));
        descriptor.getActivityDateTime().ifPresent(date -> sb.append(PREFIX_DATETIME).append(date.getDateTime())
                .append(" "));
        descriptor.getLevelOfImportance().ifPresent(importance -> sb.append(PREFIX_DATETIME)
                .append(importance.getValue()).append(" "));
        return sb.toString();
    }

    /**
     * Returns edited activity for editActivity Command.
     * @param descriptor EditDescriptor
     * @return string for edit command.
     */
    public static String getNewEditActivityDescriptorDetails(EditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.getValue()).append(" "));
        return sb.toString();
    }
}
