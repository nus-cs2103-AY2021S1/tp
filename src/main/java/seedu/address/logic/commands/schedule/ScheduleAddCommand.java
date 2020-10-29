package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class ScheduleAddCommand extends ScheduleCommand {

    public static final String MESSAGE_USAGE = "schedule add n/[characters] start/yyyy-mm-ddTHH:mm "
            + "end/yyyy-mm-ddTHH:mm "
            + "r/[weekly, daily, none]" + "\n"
            + "Example: schedule add n/lesson start/2020-10-30T16:00 end/2020-10-30T17:00 r/weekly";

    public static final String MESSAGE_SUCCESS = "Successfully added an event: \n%1$s";

    private final Event eventToAdd;

    /**
     * Creates a scheduleAddCommand with the corresponding {@code vEvent} to add
     */
    public ScheduleAddCommand(Event eventToAdd) {
        requireNonNull(eventToAdd);
        this.eventToAdd = eventToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_EVENT);
        }

        if (model.isClashingEvent(eventToAdd)) {
            throw new CommandException(Messages.MESSAGE_CLASHING_EVENT);
        }

        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd),
                false, false, true, false);
    }
}
