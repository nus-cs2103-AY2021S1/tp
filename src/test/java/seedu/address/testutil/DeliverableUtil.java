package seedu.address.testutil;

import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.deliverable.AddCommand;
import seedu.address.logic.commands.deliverable.EditCommand;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * A utility class for Deliverable.
 */
public class DeliverableUtil {

    /**
     * Returns an add command string for adding the {@code deliverable}.
     */
    public static String getAddCommand(Deliverable deliverable) {
        return AddCommand.COMMAND_WORD + " " + getDeliverableDetails(deliverable);
    }

    /**
     * Returns the part of command string for the given {@code deliverable}'s details.
     */
    public static String getDeliverableDetails(Deliverable deliverable) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + deliverable.getTitle().value + " ");
        sb.append(PREFIX_MILESTONE + deliverable.getMilestone().value + " ");
        deliverable.getDescription().value.ifPresent(description -> sb.append(PREFIX_DESCRIPTION + description + " "));
        sb.append(PREFIX_DEADLINE + deliverable.getDeadline().toString() + " ");
        deliverable.getContacts().value.ifPresent(contacts -> sb.append(PREFIX_CONTACTS + contacts + " "));
        return sb.toString();
    }

    // TODO
    /**
     * Returns the part of command string for the given {@code EditDeliverableDescriptor}'s details.
     */
    public static String getEditDeliverableDescriptorDetails(EditCommand.EditDeliverableDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.value).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.toString()).append(" "));
        descriptor.getMilestone().ifPresent(milestone -> sb.append(PREFIX_MILESTONE)
                .append(milestone.value).append(" "));
        descriptor.getContacts().ifPresent(contacts ->
                sb.append(PREFIX_CONTACTS).append(contacts.value.orElse("")).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.value.orElse("")).append(" "));
        System.out.println(sb.toString());
        return sb.toString();
    }
}
