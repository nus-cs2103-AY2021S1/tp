package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.DeleteEvent;

/**
 * Deletes an event based on the event's name, start and end date time.
 */
public class ScheduleDeleteCommand extends ScheduleCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + "delete"
            + ": Deletes the event based on the event's name, start and end date time.\n"
            + "Parameters: n/[event name] start/yyyy-mm-ddTHH:mm end/yyyy-mm-ddTHH:mm \n"
            + "Example: " + COMMAND_WORD + "delete" + " n/Lesson start/2020-10-30T16:00 end/2020-10-30T17:00";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Event: \n%1$s";
    public static final String MESSAGE_NO_MATCHING_EVENT = "Cannot find the event specified";

    private final DeleteEvent eventToDelete;

    public ScheduleDeleteCommand(DeleteEvent eventToDelete) {
        this.eventToDelete = eventToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<VEvent> vEventList = new ArrayList<>(model.getVEventList());

        if (vEventList.size() == 0) {
            throw new CommandException("No events in schedule to delete");
        }

        if (!model.hasEvent(eventToDelete)) {
            throw new CommandException(MESSAGE_NO_MATCHING_EVENT);
        }
        model.removeEvent(eventToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, eventToDelete), false, false, true);
    }

}
