package seedu.address.logic.commands.room;

import java.util.PriorityQueue;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

/**
 * Finds the next empty room with the lowest room number
 */
//@@author itssodium
public class FindEmptyRoomCommand extends Command {
    public static final String COMMAND_WORD = "findemptyroom";
    public static final String MESSAGE_NO_EMPTY_ROOM = "There are unfortunately no more empty rooms!";
    public static final String NUMBER_OF_ROOMS_UNDEFINED = "Please define number of rooms";
    public static final String MESSAGE_SUCCESS = "Room Number %s is empty";

    public FindEmptyRoomCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        PriorityQueue<Room> rooms = model.getRooms();
        if (model.getNumOfRooms() == 0) {
            throw new CommandException(NUMBER_OF_ROOMS_UNDEFINED);
        }
        Room room = rooms.peek();
        assert room != null;
        if (room.isOccupied()) {
            throw new CommandException(MESSAGE_NO_EMPTY_ROOM);
        }
        Predicate<Room> predicate = givePredicate(room);
        model.updateFilteredRoomList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, room.getRoomNumber()));
    }

    private Predicate<Room> givePredicate(Room room) {
        Predicate<Room> roomPredicate = room1 -> room1.getRoomNumber() == room.getRoomNumber();
        return roomPredicate;
    }
}
