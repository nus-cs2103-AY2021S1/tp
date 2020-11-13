package seedu.address.testutil;

import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TO;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * A utility class for Meeting.
 */
public class MeetingUtil {

    public static final DateTimeFormatter DT_FORMATTER_FROM =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter DF_FORMATTER_TO =
            DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Returns an add command string for adding the {@code meeting}.
     */
    public static String getAddCommand(Meeting meeting) {
        return AddCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    /**
     * Returns the part of command string for the given {@code meeting}'s details.
     */
    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + meeting.getTitle().value + " ");
        sb.append(PREFIX_FROM + DT_FORMATTER_FROM.format(meeting.getFrom().value) + " ");
        sb.append(PREFIX_TO + DF_FORMATTER_TO.format(meeting.getTo().value) + " ");
        meeting.getDescription().value.ifPresent(desc -> sb.append(PREFIX_DESCRIPTION + desc + " "));
        meeting.getLocation().value.ifPresent(loc -> sb.append(PREFIX_LOCATION + loc + " "));
        meeting.getContacts().value.ifPresent(con -> sb.append(PREFIX_CONTACTS + con + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMeetingDescriptor}'s details.
     */
    public static String getEditMeetingDescriptorDetails(EditMeetingDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.value).append(" "));
        descriptor.getFrom().ifPresent(from -> sb.append(PREFIX_FROM)
                .append(DT_FORMATTER_FROM.format(from.value)).append(" "));
        descriptor.getTo().ifPresent(to -> sb.append(PREFIX_TO)
                .append(DF_FORMATTER_TO.format(to.value)).append(" "));
        descriptor.getLocation().ifPresent(location ->
                sb.append(PREFIX_LOCATION).append(location.value.orElse("")).append(" "));
        descriptor.getContacts().ifPresent(contacts ->
                sb.append(PREFIX_CONTACTS).append(contacts.value.orElse("")).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.value.orElse("")).append(" "));
        return sb.toString();
    }
}
