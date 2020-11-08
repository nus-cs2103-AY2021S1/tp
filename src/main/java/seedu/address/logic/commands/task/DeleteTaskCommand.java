package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

//@@author w-yeehong
/**
 * Deletes a {@code Task} from a {@code Room}.
 * The {@code Room} is identified by its room number.
 * The {@code Task} is identified by its one-based index in the {@code TaskList}
 * of {@code Room}.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from Covigent. "
            + "\nParameters: "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + PREFIX_TASK_NUMBER + "TASK NUMBER "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "1 "
            + PREFIX_TASK_NUMBER + "3";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task %1$d deleted from Room %2$d. \nDescription: %3$s";

    private final int roomNumber;
    private final Index taskIndex;

    /**
     * Creates a DeleteTaskCommand to remove the {@code Task} with {@code taskIndex} from
     * the {@code Room} with the {@code roomNumber}.
     */
    public DeleteTaskCommand(int roomNumber, Index taskIndex) {
        requireNonNull(taskIndex);
        assert roomNumber > 0 : "Room number should be greater than 0.";
        this.roomNumber = roomNumber;
        this.taskIndex = taskIndex;
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

        // Get the task which the user wants to delete
        Optional<Task> optionalTask = model.getTaskFromRoomWithTaskIndex(taskIndex, room);
        Task taskToDelete = optionalTask.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX));
        assert taskToDelete != null : "The task to delete should never be null.";

        model.deleteTaskFromRoom(taskToDelete, room);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                taskIndex.getOneBased(), roomNumber, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && roomNumber == (((DeleteTaskCommand) other).roomNumber)
                && taskIndex.equals((((DeleteTaskCommand) other).taskIndex)));
    }
}
