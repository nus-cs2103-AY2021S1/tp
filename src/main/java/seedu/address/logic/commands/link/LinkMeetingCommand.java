package seedu.address.logic.commands.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Task;

/**
 * Add a meeting link to an event.
 */
public class LinkMeetingCommand extends LinkCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " meeting: Add meeting link to event. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_URL + "URL "
            + PREFIX_INDEX + "INDEX "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME\n"
            + "Example: " + COMMAND_WORD + " meeting "
            + PREFIX_DESCRIPTION + "workshop "
            + PREFIX_URL + "https://www.youtube.com "
            + PREFIX_INDEX + "1 "
            + PREFIX_DATE + "29-10-2020 "
            + PREFIX_TIME + "1200";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    private final MeetingLink meetingLink;

    private final Index index;

    /**
     * Creates a LinkMeetingCommand to add the meeting link to an {@code Event}
     */
    public LinkMeetingCommand(Index index, MeetingLink meetingLink) {
        requireNonNull(meetingLink);
        this.meetingLink = meetingLink;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        try {
            if (lastShownList.get(index.getZeroBased()).getClass() != Event.class) {
                throw new CommandException(Messages.MESSAGE_INVALID_INDEX_NOT_EVENT);
            }

            Event eventToEdit = (Event) lastShownList.get(index.getZeroBased());
            Event editedEvent;
            if (eventToEdit.getRecurrence() != null) {
                editedEvent = new Event(eventToEdit.getDescription(), eventToEdit.getStartTime(),
                        eventToEdit.getEndTime(), eventToEdit.getRecurrence(), meetingLink, eventToEdit.getTags());
            } else {
                editedEvent = new Event(eventToEdit.getDescription(), eventToEdit.getStartTime(),
                        eventToEdit.getEndTime(), meetingLink, eventToEdit.getTags());
            }
            model.setTask(eventToEdit, editedEvent);
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        } catch (ClassCastException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_NOT_EVENT);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingLink.getDescriptionDateTime()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkMeetingCommand // instanceof handles nulls
                && meetingLink.equals(((LinkMeetingCommand) other).meetingLink));
    }
}
