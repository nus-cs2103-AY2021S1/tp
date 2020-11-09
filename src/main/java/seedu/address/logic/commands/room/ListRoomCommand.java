package seedu.address.logic.commands.room;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "listroom";
    public static final String MESSAGE_SUCCESS = "All rooms are listed.";
    public static final String NUMBER_OF_ROOMS_UNDEFINED =
            "There is no room in the app yet. Please define the number of rooms using initRooms command.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getRooms().size() == 0) {
            throw new CommandException(NUMBER_OF_ROOMS_UNDEFINED);
        }
        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
