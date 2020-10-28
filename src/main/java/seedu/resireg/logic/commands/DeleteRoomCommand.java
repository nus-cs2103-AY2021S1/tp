package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.room.Room;
import seedu.resireg.storage.Storage;

/**
 * Deletes a student identified using its displayed index from ResiReg.
 */
public class DeleteRoomCommand extends Command {

    public static final String COMMAND_WORD = "delete-room";

    public static final String MESSAGE_DELETE_ROOM_SUCCESS = "Deleted Room: %1$s";
    public static final String MESSAGE_ROOM_ALLOCATION_EXISTS =
            "Room is currently allocated! Deallocate room first!";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Deletes the room identified by the index number used in the displayed room list.",
            "Parameters: INDEX (must be a positive integer)\nExample: " + COMMAND_WORD + " 1");

    private final Index targetIndex;

    public DeleteRoomCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Room toDelete = lastShownList.get(targetIndex.getZeroBased());

        // TODO: shift checking to model?
        assert !model.isAllocated(toDelete) : "Room should not be allocated while in new mode";

        if (model.isAllocated(toDelete)) {
            throw new CommandException(MESSAGE_ROOM_ALLOCATION_EXISTS);
        }

        model.deleteRoom(toDelete);
        model.saveStateResiReg();
        return new CommandResult(String.format(MESSAGE_DELETE_ROOM_SUCCESS, toDelete.toString()));
    }
}
