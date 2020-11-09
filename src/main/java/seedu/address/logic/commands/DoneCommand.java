package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;
/**
 * Marks a task identified using it's displayed index from the task list as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_MARK_TASK_AS_DONE_SUCCESS = "Task marked as done: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        if (taskToMark.isRecurring()) {
            Recurrence recurrence = taskToMark.getRecurrence();
            Set<Tag> tags = taskToMark.getTags();
            String description = taskToMark.getDescription();
            if (taskToMark.isTodo()) {
                LocalDateTime newDateTime = taskToMark.getLocalDateTime().plus(taskToMark.getRecurrence().getValue(),
                        taskToMark.getRecurrence().getChronoUnit());
                if (taskToMark.getLink().isEmpty()) {
                    model.addTask(new Todo(description, newDateTime, recurrence, tags));
                } else {
                    CollaborativeLink collaborativeLink = ((Todo) taskToMark).getCollaborativeLink();
                    model.addTask(new Todo(description, newDateTime, recurrence, collaborativeLink, tags));
                }
            } else {
                LocalDateTime newStartDateTime = taskToMark.getStart()
                        .plus(taskToMark.getRecurrence().getValue(), taskToMark.getRecurrence().getChronoUnit());
                LocalDateTime newEndDateTime = taskToMark.getEnd()
                        .plus(taskToMark.getRecurrence().getValue(), taskToMark.getRecurrence().getChronoUnit());
                if (taskToMark.getLink().isEmpty()) {
                    model.addTask(new Event(description, newStartDateTime, newEndDateTime, recurrence, tags));
                } else {
                    MeetingLink currentMeeting = ((Event) taskToMark).getMeetingLink();
                    LocalDateTime newTiming = currentMeeting.getLocalDateTime()
                            .plus(taskToMark.getRecurrence().getValue(), taskToMark.getRecurrence().getChronoUnit());

                    String meetingDescription = currentMeeting.getDescription();
                    int positionOfOldTiming = meetingDescription.indexOf("(on: ");
                    meetingDescription = meetingDescription.substring(0, positionOfOldTiming - 1);

                    MeetingLink newMeeting = new MeetingLink(meetingDescription, currentMeeting.getUrl(), newTiming);
                    model.addTask(new Event(description, newStartDateTime, newEndDateTime,
                            recurrence, newMeeting, tags));
                }
            }
        }
        model.markAsDone(taskToMark);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_AS_DONE_SUCCESS, taskToMark), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex));
    }
}
