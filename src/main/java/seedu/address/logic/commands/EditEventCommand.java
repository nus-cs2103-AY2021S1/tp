package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;
import seedu.address.ui.UiManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n";


    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    //public static final String MESSAGE_DUPLICATE_TASK = "This recipe already exists in the recipe book.";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index                of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = editEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedEvent((Event)taskToEdit, editEventDescriptor);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Deadline} with the details of {@code deadlineToEdit}
     * edited with {@code editDeadlineDescriptor}.
     */
    public static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        Title updatedTitle = Optional.of(editEventDescriptor.getTitle()).orElse(eventToEdit.getTitle());
        Description updatedDescription = Optional.of(editEventDescriptor.getDescription()).orElse(eventToEdit.getDescription());
        Priority updatedPriority = Optional.of(editEventDescriptor.getPriority()).orElse(eventToEdit.getPriority());
        TaskDate updatedEventDate = Optional.of(editEventDescriptor.getEventDate()).orElse(eventToEdit.getEventDate());
        TaskTime updatedEventTime = Optional.of(editEventDescriptor.getEventTime()).orElse(eventToEdit.getEventTime());
        Set<Tag> updatedTagList = Optional.of(editEventDescriptor.getTagList()).orElse(eventToEdit.getTags());

        return new Event(updatedTitle, updatedDescription, updatedPriority, updatedEventDate, updatedEventTime, updatedTagList);
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

}
