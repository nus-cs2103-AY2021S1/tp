package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

//@@author w-yeehong
/**
 * Edits and replaces a {@code Task} in a {code Room}.
 * The {@code Room} is identified by its index in the {@code RoomList}
 * of {@code Model}.
 * The {@code Task} is identified by its index in the {@code TaskList}
 * of {@code Room}.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a task in Covigent.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + PREFIX_TASK_NUMBER + "TASK NUMBER "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DUE_DATE + "DUE DATE]"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "1 "
            + PREFIX_TASK_NUMBER + "3 "
            + PREFIX_DUE_DATE + "20200930 1700";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Task %1$d edited from Room %2$d. \nDescription: %3$s";

    private final int roomNumber;
    private final Index taskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an EditTaskCommand to replace a {@code Task} in a {@code Room} with another
     * {@code Task} described by {@code editTaskDescriptor}.
     */
    public EditTaskCommand(int roomNumber, Index taskIndex, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(taskIndex, editTaskDescriptor);
        assert roomNumber > 0 : "Room number should be greater than 0.";
        this.roomNumber = roomNumber;
        this.taskIndex = taskIndex;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (roomNumber < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_NUMBER);
        }

        // Get the room from which the user wants to delete the task
        Optional<Room> optionalRoom = model.getRoomWithRoomNumber(roomNumber);
        Room room = optionalRoom.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_ROOM_NUMBER));
        assert room != null : "Target room should never be null.";

        // Get the task which the user wants to edit
        Optional<Task> optionalTask = model.getTaskFromRoomWithTaskIndex(taskIndex, room);
        Task taskToEdit = optionalTask.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX));
        assert taskToEdit != null : "The task to edit should never be null.";

        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        if (taskToEdit.equals(editedTask)) {
            throw new CommandException(Messages.MESSAGE_TASK_NOT_EDITED);
        }

        model.setTaskToRoom(taskToEdit, editedTask, room);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS,
                taskIndex.getOneBased(), roomNumber, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     *
     * @param taskToEdit Task that is to be edited.
     * @param editTaskDescriptor Details to edit the task with.
     * @return Task that has been edited.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(taskToEdit, editTaskDescriptor);

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        DateTimeDue updatedDueAt = editTaskDescriptor.getDateTimeDue().orElse(taskToEdit.getDueAt());

        return new Task(updatedDescription, updatedDueAt);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTaskCommand // instanceof handles nulls
                && roomNumber == (((EditTaskCommand) other).roomNumber)
                && taskIndex.equals(((EditTaskCommand) other).taskIndex)
                && editTaskDescriptor.equals(((EditTaskCommand) other).editTaskDescriptor));
    }

    /**
     * Stores the details used to edit the task. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Description description;
        private DateTimeDue dueAt;

        // Prevents instantiation
        public EditTaskDescriptor() {}

        /**
         * Constructs a EditTaskDescriptor object with the following fields.
         *
         * @param toCopy EditTaskDescriptor to copy the fields from.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setDescription(toCopy.description);
            setDateTimeDue(toCopy.dueAt);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, dueAt);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDateTimeDue(DateTimeDue dueAt) {
            this.dueAt = dueAt;
        }

        public Optional<DateTimeDue> getDateTimeDue() {
            return Optional.ofNullable(dueAt);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof EditTaskDescriptor // instanceof handles nulls
                    && getDescription().equals(((EditTaskDescriptor) other).getDescription())
                    && getDateTimeDue().equals(((EditTaskDescriptor) other).getDateTimeDue()));
        }
    }
}
