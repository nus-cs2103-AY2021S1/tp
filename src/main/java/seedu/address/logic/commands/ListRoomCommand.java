package seedu.address.logic.commands;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

public class ListRoomCommand extends Command {

    public static final String COMMAND_WORD = "listRoom";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all rooms.\n";
    public static final String MESSAGE_SUCCESS = "Successfully retrieved all rooms";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final int roomType;
    /**
     * Creates a ListRoomCommand.
     */
    public ListRoomCommand(int roomType) {
        this.roomType = roomType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("=============================[ Executing listRoom ]===========================");
        ObservableList<Room> roomList = model.getRoomBook().getRoomList();
        ObservableList<Integer> retList = FXCollections.observableArrayList(roomList.stream()
                .map(Room::getRoomID).collect(Collectors.toList()));
        String result = "";
        if (roomType == 0) {
            result = model.displayRooms(retList);
        } else if (roomType == 1) {
            result = model.displaySingleRooms(retList);
        } else if (roomType == 2) {
            result = model.displayDoubleRooms(retList);
        } else if (roomType == 3) {
            result = model.displaySuiteRooms(retList);
        }
        return new CommandResult(MESSAGE_SUCCESS + "\n" + result);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return other instanceof ListRoomCommand;
    }
}
