package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.room.Room;
import seedu.resireg.storage.Storage;

/**
 * Adds a room to ResiReg.
 */
public class AddRoomCommand extends Command {

    public static final String COMMAND_WORD = "add-room";

    public static final String MESSAGE_SUCCESS = "New room added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in ResiReg";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Adds a room to ResiReg.\n",
            "Parameters: "
                    + PREFIX_ROOM_FLOOR + "FLOOR "
                    + PREFIX_ROOM_NUMBER + "ROOM_NUMBER "
                    + PREFIX_ROOM_TYPE + "ROOM_TYPE");

    private final Room toAdd;

    /**
     * Creates an {@code AddRoomCommand} to add the specified {@code oom}.
     */
    public AddRoomCommand(Room room) {
        requireNonNull(room);
        toAdd = room;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasRoom(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.addRoom(toAdd);
        model.saveStateResiReg();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
    }
}
