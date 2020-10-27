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
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditTodoCommand extends Command {

    public static final String COMMAND_WORD = "editTodo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n";
    /*
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_INGREDIENTS + "INGREDIENT 1, QUANTITY; INGREDIENT 2, QUANTITY] "
            + "[" + PREFIX_INSTRUCTIONS + "INSTRUCTION 1; INSTRUCTION 2] "
            + "[" + PREFIX_IMAGEFILEPATH + "PATH] "
            + "[" + PREFIX_CALORIE + "CALORIES] "
            + "[" + PREFIX_SERVING + "SERVING] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_DIFFICULTY + "DIFFICULTY] "
            + "[" + PREFIX_TAG + "TAG [TAG]...]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INGREDIENTS + "toast, 2; eggs, 1 "
            + PREFIX_INSTRUCTIONS + "put egg on toast; put bread on egg";

     */

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    //public static final String MESSAGE_DUPLICATE_TASK = "This recipe already exists in the recipe book.";

    private final Index index;
    private final EditTodoDescriptor editTodoDescriptor;
    //private final EditDeadlineDescriptor editDeadlineDescriptor;
    //private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index                of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTodoCommand(Index index, EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(index);
        requireNonNull(editTodoDescriptor);

        this.index = index;
        this.editTodoDescriptor = editTodoDescriptor;
        //this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    /**
     * @param index                of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    /*
    public EditCommand(Index index, EditDeadlineDescriptor editDeadlineDescriptor) {
        requireNonNull(index);
        requireNonNull(editDeadlineDescriptor);

        this.index = index;
        this.editDeadlineDescriptor = new EditDeadlineDescriptor(editDeadlineDescriptor);
    }
    */
    /**
     * @param index                of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    /*
    public EditCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }
    */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTodo((ToDo)taskToEdit, editTodoDescriptor);
    /*
    } else if (taskToEdit instanceof Deadline) {
            editedTask = createEditedDeadline(taskToEdit, (EditDeadlineDescriptor)editTaskDescriptor);
        } else {
            editedTask = createEditedEvent(taskToEdit, (EditEventDescriptor)editTaskDescriptor);
        }
*/
        /*
        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }


        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        if (UiManager.getViewedTask() == taskToEdit) {
            UiManager.changeTask(editedTask);
        }
        */
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Todo} with the details of {@code todoToEdit}
     * edited with {@code editTodoDescriptor}.
     */
    public static ToDo createEditedTodo(ToDo todoToEdit, EditTodoDescriptor editTodoDescriptor) {
        assert todoToEdit != null;

        Title updatedTitle = Optional.of(editTodoDescriptor.getTitle()).orElse(todoToEdit.getTitle());
        Description updatedDescription = Optional.of(editTodoDescriptor.getDescription()).orElse(todoToEdit.getDescription());
        Priority updatedPriority = Optional.of(editTodoDescriptor.getPriority()).orElse(todoToEdit.getPriority());
        Set<Tag> updatedTagList = Optional.of(editTodoDescriptor.getTagList()).orElse(todoToEdit.getTags());

        return new ToDo(updatedTitle, updatedDescription, updatedPriority, updatedTagList);
    }

    /**
     * Creates and returns a {@code Deadline} with the details of {@code deadlineToEdit}
     * edited with {@code editDeadlineDescriptor}.
     */
    public static Deadline createEditedDeadline(Deadline deadlineToEdit, EditDeadlineDescriptor editDeadlineDescriptor) {
        assert deadlineToEdit != null;

        Title updatedTitle = Optional.of(editDeadlineDescriptor.getTitle()).orElse(deadlineToEdit.getTitle());
        Description updatedDescription = Optional.of(editDeadlineDescriptor.getDescription()).orElse(deadlineToEdit.getDescription());
        Priority updatedPriority = Optional.of(editDeadlineDescriptor.getPriority()).orElse(deadlineToEdit.getPriority());
        TaskDate updatedTaskDeadline = Optional.of(editDeadlineDescriptor.getTaskDeadline()).orElse(deadlineToEdit.getDeadlineDate());
        Set<Tag> updatedTagList = Optional.of(editDeadlineDescriptor.getTagList()).orElse(deadlineToEdit.getTags());

        return new Deadline(updatedTitle, updatedDescription, updatedPriority, updatedTaskDeadline, updatedTagList);
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
        if (!(other instanceof EditTodoCommand)) {
            return false;
        }

        // state check
        EditTodoCommand e = (EditTodoCommand) other;
        return index.equals(e.index)
                && editTodoDescriptor.equals(e.editTodoDescriptor);
    }

}
