package seedu.address.logic.commands.meetingcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Sorts the meetings using the specified comparator.
 */
public class SortMeetingCommand extends Command {

    public static final String COMMAND_WORD = "sort-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the meeting in the meeting book."
            + "\n\nParameters: "
            + "\n" + "sort-m o/asc"
            + "\n" + "sort-m o/dsc";

    public static final String MESSAGE_SUCCESS = "Successfully sorted meeting";

    public static final String MESSAGE_SORT_TYPE_INVALID = "Invalid Sort Type";

    private final Comparator<Meeting> comparator;

    /**
     * Constructor for SortMeetingCommand. Checks the order of sort required
     * and producers the appropriate comparator.
     */
    public SortMeetingCommand(Comparator<Meeting> comparator, Boolean isAscending) {
        assert (comparator != null);
        if (!isAscending) {
            this.comparator = comparator.reversed();
        } else {
            this.comparator = comparator;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedMeetingList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
