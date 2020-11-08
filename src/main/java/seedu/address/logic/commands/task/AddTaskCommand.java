package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.task.Task;

//@@author w-yeehong
/**
 * Adds a {@code Task} to a {@code Room}.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to Covigent. "
            + "\nParameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + "[" + PREFIX_DUE_DATE + "DUE DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Remind John to return his bedsheets "
            + PREFIX_ROOM_NUMBER + "3 "
            + PREFIX_DUE_DATE + "20200928 2359";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New Task added to Room %1$d. \nDescription: %2$s";

    private final Task taskToAdd;
    private final int roomNumber;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to a {@code Room}
     * with the {@code roomNumber}.
     */
    public AddTaskCommand(Task task, int roomNumber) {
        requireNonNull(task);
        assert roomNumber > 0 : "Room number should be greater than 0.";
        taskToAdd = task;
        this.roomNumber = roomNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (roomNumber < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_NUMBER);
        }

        Optional<Room> optionalRoom = model.getRoomWithRoomNumber(roomNumber);
        Room room = optionalRoom.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_ROOM_NUMBER));
        assert room != null : "Target room should never be null.";

        model.addTaskToRoom(taskToAdd, room);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS,
                roomNumber, taskToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskToAdd.equals(((AddTaskCommand) other).taskToAdd)
                && roomNumber == (((AddTaskCommand) other).roomNumber));
    }
}
