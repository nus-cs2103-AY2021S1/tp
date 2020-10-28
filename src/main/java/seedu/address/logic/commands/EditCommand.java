package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CALENDAR_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;

/**
 * Edits the details of an existing task in PlaNus task list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event/deadline identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATE_TIME + "DATE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE_TIME + "DATE TIME (for deadline only)] "
            + "[" + PREFIX_DATE + "EVENT DATE (for event only)] "
            + "[" + PREFIX_START_TIME + "EVENT START TIME (for event only)] "
            + "[" + PREFIX_END_TIME + "EVENT END TIME (for event only)] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE_TIME + "02-02-2020 12:00 "
            + PREFIX_DESCRIPTION + "something new";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in PlaNus.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        checkEditability(taskToEdit, editTaskDescriptor);

        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.updateFilteredCalendar(PREDICATE_SHOW_ALL_CALENDAR_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    private void checkEditability(Task task, EditTaskDescriptor editTaskDescriptor) throws CommandException {
        if ((task instanceof Event)) {
            if (((Event) task).isLesson()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_EDIT_TYPE);
            }
            if (editTaskDescriptor.hasDeadlineAttributes()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EDIT_TYPE);
            }
        } else {
            if (((Deadline) task).isDone()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_EDIT_STATUS);
            }
            if (editTaskDescriptor.hasEventAttributes()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EDIT_TYPE);
            }
        }
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit,
                                         EditTaskDescriptor editTaskDescriptor) throws CommandException {
        assert taskToEdit != null;

        Title updatedTitle = editTaskDescriptor.getTitle().orElse(taskToEdit.getTitle());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Tag updatedTag = editTaskDescriptor.getTag().orElse(taskToEdit.getTag());

        if (taskToEdit instanceof Deadline) {
            Deadline deadlineToEdit = (Deadline) taskToEdit;
            DeadlineDateTime updatedDeadlineDateTime = editTaskDescriptor.getDeadlineDateTime().orElse(
                    deadlineToEdit.getDeadlineDateTime());
            return Deadline.createDeadline(updatedTitle, updatedDeadlineDateTime,
                    updatedDescription, updatedTag);
        } else {
            Event eventToEdit = (Event) taskToEdit;
            StartDateTime updatedStartDateTime = eventToEdit.getStartDateTime();
            EndDateTime updatedEndDateTime = eventToEdit.getEndDateTime();
            if (editTaskDescriptor.getEventDate().isPresent()) {
                LocalDate updatedEventDate = editTaskDescriptor.getEventDate().get();
                updatedStartDateTime = updatedStartDateTime.editStartDate(updatedEventDate);
                updatedEndDateTime = updatedEndDateTime.editEndDate(updatedEventDate);
            }
            if (editTaskDescriptor.getStartTime().isPresent()) {
                LocalTime updatedEventStartTime = editTaskDescriptor.getStartTime().get();
                updatedStartDateTime = updatedStartDateTime.editStartTime(updatedEventStartTime);
            }
            if (editTaskDescriptor.getEndTime().isPresent()) {
                LocalTime updatedEventEndTime = editTaskDescriptor.getEndTime().get();
                updatedEndDateTime = updatedEndDateTime.editEndTime(updatedEventEndTime);
            }
            if (!updatedStartDateTime.isBeforeEndDateTime(updatedEndDateTime)) {
                throw new CommandException(Messages.MESSAGE_START_BEFORE_END);
            }
            return Event.createUserEvent(updatedTitle, updatedStartDateTime, updatedEndDateTime,
                    updatedDescription, updatedTag);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }



    /**
     * Stores the details to edit the deadline with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Title title;
        private Description description;
        private LocalDate eventDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private DeadlineDateTime deadlineDateTime;
        private Tag tag;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setEventDate(eventDate);
            setDeadlineDateTime(toCopy.deadlineDateTime);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, eventDate,
                    deadlineDateTime, startTime, endTime, tag);
        }

        public boolean hasEventAttributes() {
            return !(eventDate == null && startTime == null && endTime == null);
        }

        public boolean hasDeadlineAttributes() {
            return deadlineDateTime != null;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDeadlineDateTime(DeadlineDateTime deadlineDateTime) {
            this.deadlineDateTime = deadlineDateTime;
        }

        public Optional<DeadlineDateTime> getDeadlineDateTime() {
            return Optional.ofNullable(deadlineDateTime);
        }

        public void setEventDate(LocalDate eventDate) {
            this.eventDate = eventDate;
        }

        public Optional<LocalDate> getEventDate() {
            return Optional.ofNullable(eventDate);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Tag> getTag() {
            return (tag != null) ? Optional.of(tag) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getTag().equals(e.getTag());
        }
    }

}
