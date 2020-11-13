package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.meeting.Meeting;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the meeting list.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO "
            + "[" + PREFIX_CONTACTS + "CONTACTS] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Discuss user requirements with biz associates "
            + PREFIX_FROM + "30-12-2020 12:00 "
            + PREFIX_TO + "14:00 "
            + PREFIX_CONTACTS + "Abby Li, John Martin "
            + PREFIX_LOCATION + "Room 1A "
            + PREFIX_DESCRIPTION + "Refer to the reviewed user stories during discussion";

    public static final String MESSAGE_SUCCESS = "Added new meeting: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the meeting list.";

    private final Meeting toAdd;

    /**
     * Creates a MeetingCommand to add the specified {@code Meeting}
     */
    public AddCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(ModelMeeting modelMeeting) throws CommandException {
        requireNonNull(modelMeeting);

        if (modelMeeting.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        modelMeeting.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddCommand
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
