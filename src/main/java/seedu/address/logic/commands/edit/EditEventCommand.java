package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Edits the details of an existing todo in the Lifebook.
 */
public class EditEventCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_STARTDATE + "STARTDATE] "
            + "[" + PREFIX_STARTTIME + "STARTTIME] "
            + "[" + PREFIX_ENDDATE + "ENDDATE] "
            + "[" + PREFIX_ENDTIME + "ENDTIME] "
            + "Example: " + COMMAND_WORD + " event 1 "
            + PREFIX_DESCRIPTION + "A new description "
            + PREFIX_STARTDATE + "20-01-2020 "
            + PREFIX_STARTTIME + "2350 "
            + PREFIX_ENDDATE + "23-01-2020 "
            + PREFIX_ENDTIME + "2359";

    public static final String MESSAGE_EDIT_TODO_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This todo already exists in the Lifebook.";

    private final Index index;
    private final EditEventCommand.EditEventDescriptor editEventDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editEventDescriptor details to edit the todo with
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
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Event eventToEdit = (Event) lastShownList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasTask(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setTask(eventToEdit, editedEvent);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TODO_SUCCESS, editedEvent), "TASK");
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
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

        if (eventToEdit.getLink().isPresent()) {
            MeetingLink link = eventToEdit.getMeetingLink();
            if (eventToEdit.hasRecurrence()) {
                return new Event(eventToEdit.getStatus(), description, startDate + " " + startTime,
                        endDate + " " + endTime, link, eventToEdit.getRecurrence());
            } else {
                return new Event(eventToEdit.getStatus(), description,
                        startDate + " " + startTime, endDate + " " + endTime, link);
            }
        } else {
            if (eventToEdit.hasRecurrence()) {
                return new Event(eventToEdit.getStatus(), description,startDate + " " + startTime,
                        endDate + " " + endTime, eventToEdit.getRecurrence());
            } else {
                return new Event(eventToEdit.getStatus(), description,
                        startDate + " " + startTime, endDate + " " + endTime);
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
        if (!(other instanceof EditTodoCommand)) {
            return false;
        }

        // state check
        EditEventCommand e = (EditEventCommand) other;
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditEventDescriptor {
        private String description;
        private String startDate;
        private String startTime;
        private String endDate;
        private String endTime;

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
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, startDate, startTime, endDate, endTime);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStartDate(String startDate) {
            System.out.println("set start date called");
            this.startDate = startDate;
        }

        public Optional<String> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartTime(String startTime) {
            System.out.println("set start time called");
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
            System.out.println("set end time called");
            this.endTime = endTime;
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
            if (!(other instanceof EditTodoCommand.EditTodoDescriptor)) {
                return false;
            }

            // state check
            EditEventCommand.EditEventDescriptor e = (EditEventCommand.EditEventDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getStartDate().equals(e.getStartDate())
                    && getStartTime().equals(e.getStartTime());
        }
    }
}