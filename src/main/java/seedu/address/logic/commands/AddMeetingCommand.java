package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_VENUE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.CalendarMeeting;

/**
 * Adds a meeting to the meeting book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "add-meeting";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the meeting book. "
            + "Parameters: "
            + PREFIX_CALENDAR_TYPE + "TYPE"
            + PREFIX_CALENDAR_PROPERTY_ID + "PROPERTY_ID"
            + PREFIX_CALENDAR_BIDDER_ID + "BIDDER_ID"
            + PREFIX_CALENDAR_TIME + "TIME "
            + PREFIX_CALENDAR_VENUE + "VENUE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALENDAR_TYPE + "VIEW"
            + PREFIX_CALENDAR_PROPERTY_ID + "P12 "
            + PREFIX_CALENDAR_BIDDER_ID + "B12 "
            + PREFIX_CALENDAR_TIME + "11-12-2021 "
            + PREFIX_CALENDAR_VENUE + "2 ALBERT PARK";

    private final CalendarMeeting toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddMeetingCommand(CalendarMeeting meeting) {
        requireNonNull(meeting);
        this.toAdd = meeting;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)).setEntity(EntityType.MEETING);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
