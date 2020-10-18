package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_FILTER_BY_NAME;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.project.Project;

/**
 * Filters meetings by assignee's name, task name or deadline.
 */
public class MeetingFilterCommand extends Command {
    public static final String COMMAND_WORD = "filterm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Filter and show meetings with given predicate\n"
        + "Parameters: ("
        + PREFIX_MEETING_FILTER_BY_NAME + "MEETING NAME)\n"
        + "Example: " + COMMAND_WORD + " ("
        + PREFIX_MEETING_FILTER_BY_NAME + "cs2103 weekly meeting)\n";
    public static final String MESSAGE_FILTER_MEETING_SUCCESS = "Here are the filtered meetings:";

    private final Predicate<Meeting> predicate;

    /**
     * Creates a filter command with the given predicate.
     * @param predicate the predicate used to filter meetings
     */
    public MeetingFilterCommand(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.updateMeetingFilter(predicate);
        return new CommandResult(String.format(MESSAGE_FILTER_MEETING_SUCCESS));
    }
}
