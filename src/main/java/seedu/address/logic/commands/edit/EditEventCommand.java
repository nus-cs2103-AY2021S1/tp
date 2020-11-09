package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing event in the Lifebook.
 */
public class EditEventCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " event: Edits the details of the event identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_STARTDATE + "STARTDATE] "
            + "[" + PREFIX_STARTTIME + "STARTTIME] "
            + "[" + PREFIX_ENDDATE + "ENDDATE] "
            + "[" + PREFIX_ENDTIME + "ENDTIME] "
            + "Example: " + COMMAND_WORD + " event "
            + PREFIX_INDEX + "1 "
            + PREFIX_DESCRIPTION + "A new description "
            + PREFIX_STARTDATE + "20-01-2020 "
            + PREFIX_STARTTIME + "2350 "
            + PREFIX_ENDDATE + "23-01-2020 "
            + PREFIX_ENDTIME + "2359";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the Lifebook.";

    private final Index index;
    private final EditEventCommand.EditEventDescriptor editEventDescriptor;

    /**
     * @param index of the event in the filtered task list to edit
     * @param editEventDescriptor details to edit the event with
     */
    public EditEventCommand(Index index, EditEventCommand.EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = new EditEventCommand.EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        if (lastShownList.get(index.getZeroBased()).getClass() != Event.class) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_NOT_EVENT);
        }

        Event eventToEdit = (Event) lastShownList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasTask(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setTask(eventToEdit, editedEvent);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent), "TASK");
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit,
                                         EditEventCommand.EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        String description = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        String previousStartDateTime = eventToEdit.getStartTime();
        String startDate = editEventDescriptor.getStartDate().orElse(previousStartDateTime.split(" ")[0]);
        String startTime = editEventDescriptor.getStartTime().orElse(previousStartDateTime.split(" ")[1]);
        String previousEndDateTime = eventToEdit.getEndTime();
        String endDate = editEventDescriptor.getEndDate().orElse(previousEndDateTime.split(" ")[0]);
        String endTime = editEventDescriptor.getEndTime().orElse(previousEndDateTime.split(" ")[1]);
        Set<Tag> updatedTags = editEventDescriptor.getTags().orElse(eventToEdit.getTags());

        if (eventToEdit.getLink().isPresent()) {
            MeetingLink link = eventToEdit.getMeetingLink();
            if (eventToEdit.hasRecurrence()) {
                return new Event(eventToEdit.getStatus(), description, startDate + " " + startTime,
                        endDate + " " + endTime, link, eventToEdit.getRecurrence(), updatedTags);
            } else {
                return new Event(eventToEdit.getStatus(), description,
                        startDate + " " + startTime, endDate + " " + endTime, link, updatedTags);
            }
        } else {
            if (eventToEdit.hasRecurrence()) {
                return new Event(eventToEdit.getStatus(), description, startDate + " " + startTime,
                        endDate + " " + endTime, eventToEdit.getRecurrence(), updatedTags);
            } else {
                return new Event(eventToEdit.getStatus(), description,
                        startDate + " " + startTime, endDate + " " + endTime, updatedTags);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        // state check
        EditEventCommand e = (EditEventCommand) other;
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private String description;
        private String startDate;
        private String startTime;
        private String endDate;
        private String endTime;
        private Set<Tag> tags;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventCommand.EditEventDescriptor toCopy) {
            setDescription(toCopy.description);
            setStartDate(toCopy.startDate);
            setStartTime(toCopy.startTime);
            setEndDate(toCopy.endDate);
            setEndTime(toCopy.endTime);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, startDate, startTime, endDate, endTime, tags);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Optional<String> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public Optional<String> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Optional<String> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public Optional<String> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEventCommand.EditEventDescriptor)) {
                return false;
            }

            // state check
            EditEventCommand.EditEventDescriptor e = (EditEventCommand.EditEventDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getStartDate().equals(e.getStartDate())
                    && getStartTime().equals(e.getStartTime())
                    && getEndDate().equals(e.getEndDate())
                    && getEndTime().equals(e.getEndTime());
        }
    }
}
